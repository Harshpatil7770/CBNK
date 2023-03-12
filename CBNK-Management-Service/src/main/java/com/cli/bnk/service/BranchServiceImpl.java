package com.cli.bnk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cli.bnk.dao.BranchAddressDAO;
import com.cli.bnk.dao.BranchDao;
import com.cli.bnk.dto.BranchDTO;
import com.cli.bnk.model.Branch;
import com.cli.bnk.model.BranchAddress;

/**
 * 
 * @author harsh
 *
 */
@Service
public class BranchServiceImpl implements BranchService {

	@Autowired
	private BranchDao branchDao;

	@Autowired
	private Branch branch;

	@Autowired
	private BranchAddress branchAddress;

	@Autowired
	private BranchAddressDAO addressDao;

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

		persistBranch(branch);
	}

	private void persistBranch(Branch branch) throws Exception {
		branchDao.save(branch);
	}

	@Override
	public Branch findBranchDetailsUsingByName(String branchName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Branch findBranchDetailsUsingBranchId(long branchId) {
		// TODO Auto-generated method stub
		return null;
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
