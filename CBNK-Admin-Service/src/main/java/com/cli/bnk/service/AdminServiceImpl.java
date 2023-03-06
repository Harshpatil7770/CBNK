package com.cli.bnk.service;

import java.time.LocalDate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cli.bnk.dao.BranchAddressDAO;
import com.cli.bnk.dao.BranchDao;
import com.cli.bnk.dao.ManagerDao;
import com.cli.bnk.dao.PersonAddressDao;
import com.cli.bnk.dao.PersonInfoDao;
import com.cli.bnk.dao.UserDao;
import com.cli.bnk.dto.ManagerDTO;
import com.cli.bnk.model.Branch;
import com.cli.bnk.model.BranchAddress;
import com.cli.bnk.model.Gender;
import com.cli.bnk.model.Manager;
import com.cli.bnk.model.PersonAddress;
import com.cli.bnk.model.PersonInfo;
import com.cli.bnk.model.Role;
import com.cli.bnk.model.User;

@Service
public class AdminServiceImpl implements AdminService {

	private final Logger logger = LogManager.getLogger(AdminServiceImpl.class);

	@Autowired
	private ManagerDao managerDao;

	@Autowired(required = true)
	private Branch branch;

	@Autowired(required = true)
	private User user;

	@Autowired(required = true)
	private Manager manager;

	@Autowired
	private UserDao userDao;

	@Autowired
	private BranchDao branchDao;

	@Autowired(required = true)
	private PersonInfo personInfo;

	@Autowired(required = true)
	private PersonInfoDao personInfoDao;

	@Autowired
	private BranchAddressDAO addressDao;

	@Autowired(required = true)
	private BranchAddress branchAddress;

	@Autowired(required = true)
	private PersonAddress personalAddress;

	@Autowired
	private PersonAddressDao personAddressDao;

	private static final long INITIAL_PERSON_ADDRESS_ID = 1;

	@Override
	public void addNewManager(ManagerDTO managerDTO) {

		/*
		 * set branch related info
		 */
		validateBranchInfo(managerDTO);

		/*
		 * set user related info
		 */
		validateUserDetails(managerDTO);

		Long lastManagerId = managerDao.findLastManagerId();
		if (lastManagerId != null) {
			manager.setManagerId(++lastManagerId);
		}

		persistData(manager);

	}

	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	private Manager validateBranchInfo(ManagerDTO managerDTO) {
		branch.setBranchName(managerDTO.getBranchDTO().getBranchName());
		branch.setIfscCode(managerDTO.getBranchDTO().getIfscCode());
		branch.setPinCode(managerDTO.getBranchDTO().getPinCode());
		branchAddress.setCity(managerDTO.getBranchDTO().getBranchAddressDTO().getCity());
		branchAddress.setArea(managerDTO.getBranchDTO().getBranchAddressDTO().getArea());
		branchAddress.setState(managerDTO.getBranchDTO().getBranchAddressDTO().getState());
		branchAddress.setPinCode(managerDTO.getBranchDTO().getBranchAddressDTO().getPinCode());
		Long branchAddressId = addressDao.findLastBranchAddressId();
		if (branchAddressId != null) {
			branchAddress.setAddressId(++branchAddressId);
		}
		branch.setAddress(branchAddress);
		Long lastBranchId = branchDao.findLastBranchId();
		if (lastBranchId != null) {
			branch.setBranchId(++lastBranchId);
		}
//		addressDao.saveAndFlush(branchAddress);
		manager.setBranch(branch);
		logger.info("Branch detailed added {} ", manager);
		return manager;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	private Manager validateUserDetails(ManagerDTO managerDTO) {
		user.setUserName(managerDTO.getUserDTO().getUserName());
		user.setPassword(managerDTO.getUserDTO().getPassword());
		user.setSecurityQuestion(managerDTO.getUserDTO().getSecurityQuestion());
		user.setSecuirtyAnswer(managerDTO.getUserDTO().getSecuirtyAnswer());
		Role role = Role.getAppropriateRole(managerDTO.getUserDTO().getRole());
		if (role == Role.MANAGER) {
			user.setRole(Role.MANAGER.getId());
		} else {
			logger.error("User type is not matched. No need to proceed further.");
			return null;
		}

		personInfo.setPersonName(managerDTO.getUserDTO().getPersonInfoDTO().getPersonName());
		Gender genderType = Gender.verifyGenders(managerDTO.getUserDTO().getPersonInfoDTO().getGender());
		if (genderType != null) {
			for (Gender gender : Gender.values()) {
				if (genderType.getGenderType() == gender.getGenderType()) {
					personInfo.setGender(genderType.getGenderType());
					break;
				}
			}

		} else {
			logger.error("Gender filed is mandotory. No need to proceed further");
			return null;
		}
		personInfo.setDob(LocalDate.parse(managerDTO.getUserDTO().getPersonInfoDTO().getDob()));
		personInfo.setEmailId(managerDTO.getUserDTO().getPersonInfoDTO().getEmailId());
		personInfo.setMobileNo(managerDTO.getUserDTO().getPersonInfoDTO().getMobileNo());
		Long personInfoId = personInfoDao.checkPreviousPersonInfoId();
		if (personInfoId != null) {
			personInfo.setPersonId(++personInfoId);
		}

		personalAddress.setCity(managerDTO.getUserDTO().getPersonInfoDTO().getPersonAddressDTO().getCity());
		personalAddress.setArea(managerDTO.getUserDTO().getPersonInfoDTO().getPersonAddressDTO().getArea());
		personalAddress.setState(managerDTO.getUserDTO().getPersonInfoDTO().getPersonAddressDTO().getState());
		personalAddress.setPinCode(managerDTO.getUserDTO().getPersonInfoDTO().getPersonAddressDTO().getPinCode());
		Long personAddressId = personAddressDao.findLastBranchAddressId();
		if (personAddressId != null) {
			personalAddress.setAddressId(++personAddressId);
		}
		// addressDao.save(personalAddress);
		personInfo.setAddress(personalAddress);
		user.setPersonInfo(personInfo);

		Long lastUserId = userDao.findLastUserId();
		if (lastUserId != null) {
			user.setUserId(++lastUserId);
		}
		manager.setUser(user);
		logger.info("Manager Personal Details added {} ", manager);
		return manager;
	}

	@Transactional
	private void persistData(Manager manager) {
		managerDao.save(manager);
	}

}
