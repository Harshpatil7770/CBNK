package com.cli.bnk.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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

/**
 * 
 * @author patil_ha
 *
 */
@Service
public class ManagerServiceImpl implements ManagerService {

	private final Logger logger = LogManager.getLogger(ManagerServiceImpl.class);

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

	/**
	 * addNewManager details and assign him branch
	 */
	@Override
	public void addNewManager(ManagerDTO managerDTO) {

		/**
		 * validate the entered branch id is available or not
		 */
		validateBrancheDetails(managerDTO);

		if (!managerDTO.isOperationDetails()) {
			logger.error("Entered branch id not available. No Need to procced further.");
			return;
		}

		/**
		 * set manager related info
		 */
		validateUserDetails(managerDTO);

		Long lastManagerId = managerDao.findLastManagerId();
		if (lastManagerId != null) {
			manager.setManagerId(++lastManagerId);
		}

		persistData(manager);

	}

	/**
	 * checking the entered branch id available or not in database
	 * 
	 * @param managerDTO
	 * @return
	 */
	private void validateBrancheDetails(ManagerDTO managerDTO) {
		Branch branch = branchDao.isBranchAvailable(managerDTO.getBranchId());
		if (branch == null) {
			managerDTO.setOperationDetails(false);
			return;
		}
		managerDTO.setOperationDetails(true);
		manager.setBranchId(managerDTO.getBranchId());
		logger.info("Branch detailed added {} ", manager);
		// return managerDTO;
	}

	/**
	 * started adding of manager basic details
	 * 
	 * @param managerDTO
	 */
	private void validateUserDetails(ManagerDTO managerDTO) {
		user.setUserName(managerDTO.getUserDTO().getUserName());
		user.setPassword(managerDTO.getUserDTO().getPassword());
		user.setSecurityQuestion(managerDTO.getUserDTO().getSecurityQuestion());
		user.setSecuirtyAnswer(managerDTO.getUserDTO().getSecuirtyAnswer());
		Role role = Role.getAppropriateRole(managerDTO.getUserDTO().getRole());
		if (role == Role.MANAGER) {
			user.setRole(Role.MANAGER.getId());
		} else {
			logger.error("User type is not matched. No need to proceed further.");
			return;
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
			return;
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
		personInfo.setAddress(personalAddress);
		user.setPersonInfo(personInfo);

		Long lastUserId = userDao.findLastUserId();
		if (lastUserId != null) {
			user.setUserId(++lastUserId);
		}
		manager.setUser(user);
	}

	private void persistData(Manager manager) {
		managerDao.save(manager);
		logger.info("New Manager Added Succesfully.");
	}

	@Override
	public void updateExistingManagerDetails(ManagerDTO managerDTO) {
		// TODO Auto-generated method stub

	}

	@Override
	public Manager findManagerDetailsByManagerId(long managerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Manager> findAllManagerRecords() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Manager findManagerDetailsByName(String personName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Manager findBranchManagerDetailsByManagerName(String branchName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Manager findBranchManagerDetailsByManagerId(long managerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Manager> findAllMaleOrFemaleEmployeeLists(char enterFirstLetter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteAllManagerRecords() {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteManagerByManagerId(Long managerId) {
		// TODO Auto-generated method stub

	}
}
