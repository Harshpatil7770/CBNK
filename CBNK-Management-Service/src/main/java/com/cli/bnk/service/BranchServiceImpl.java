package com.cli.bnk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cli.bnk.constant.ApplicationConstant;
import com.cli.bnk.dao.BranchAddressDAO;
import com.cli.bnk.dao.BranchDao;
import com.cli.bnk.dto.BranchDTO;
import com.cli.bnk.exceptionhandler.ElementNotFoundException;
import com.cli.bnk.model.Branch;
import com.cli.bnk.model.BranchAddress;
import com.cli.bnk.outboundmsgsender.BranchMsgSender;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author harsh
 *
 */
@Service
@Slf4j
public class BranchServiceImpl implements BranchService {

	@Autowired
	private BranchDao branchDao;

	@Autowired
	private Branch branch;

	@Autowired
	private BranchAddress branchAddress;

	@Autowired
	private BranchAddressDAO addressDao;

	@Autowired
	private BranchMsgSender branchMsgSender;

	private int retryCount = 0;

	@Override
	public void addNewBranch(BranchDTO branchDTO) throws Exception {

		branch.setBranchName(branchDTO.getBranchName());
		branch.setIfscCode(branchDTO.getIfscCode());
		branch.setPinCode(branchDTO.getPinCode());
		branchAddress.setCity(branchDTO.getBranchAddressDTO().getCity());
		branchAddress.setArea(branchDTO.getBranchAddressDTO().getArea());
		branchAddress.setState(branchDTO.getBranchAddressDTO().getState());
		branchAddress.setPinCode(branchDTO.getBranchAddressDTO().getPinCode());
		Long branchAddressId = addressDao.findLastBranchAddressId();
		if (branchAddressId != null) {
			branchAddress.setAddressId(++branchAddressId);
		}
		branch.setAddress(branchAddress);
		Long lastBranchId = branchDao.findLastBranchId();
		if (lastBranchId != null) {
			branch.setBranchId(++lastBranchId);
		}

		branchDao.save(branch);
		//identifyTopicAndPublishMesssage(branch);
	}

	private void identifyTopicAndPublishMesssage(Branch branch) throws Exception {
		try {
			branchMsgSender.sendNewBranchAddedMsgToParticipantTopic(branch);
		} catch (Exception e) {

			log.error("Exception occured while publishng message into participant topic.", e);

			if (retryCount < ApplicationConstant.BNK_RETRY_COUNT) {
				retryCount++;
				Thread.sleep(retryCount * ApplicationConstant.BNK_WAIT_TIME);
				log.info("Retrying message again count {} ", retryCount);
				identifyTopicAndPublishMesssage(branch);
			}

		}
	}

	@Override
	public Branch findBranchDetailsUsingByName(String branchName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Branch findBranchDetailsUsingBranchId(long branchId) {
		Branch branch = branchDao.findExistingBranchDetailsUsingBranchId(branchId);
		if (branch == null) {
			log.error("Entered Branch Id Details Not Found in Database");
			throw new ElementNotFoundException();
		}
		log.info("Branch Details : {}", branch);
		return branch;
	}

	@Override
	public List<Branch> findAllBranchesDetails() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Branch> findAllBranchesPresentInSameArea(String area) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Branch> findAllBranchesPresentSameState(String state) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Branch> findBranchDetailsPresentInSamePinCode(int pinCode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteBranchByBranchId() {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAllBranchesRecords() {
		// TODO Auto-generated method stub

	}

}
