package com.cli.cbnk.service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.BlockingQueue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.cli.cbnk.dao.BranchDao;
import com.cli.cbnk.dao.CurrentAccountDao;
import com.cli.cbnk.dao.CustomerDao;
import com.cli.cbnk.dao.PersonAddressDao;
import com.cli.cbnk.dao.PersonInfoDao;
import com.cli.cbnk.dao.SavingAccountDao;
import com.cli.cbnk.dao.TransactionDao;
import com.cli.cbnk.dto.CustomerDTO;
import com.cli.cbnk.exceptionhandeler.IllegalArgumentException;
import com.cli.cbnk.listener.BranchTopicMsgListener;
import com.cli.cbnk.model.Account;
import com.cli.cbnk.model.AccountType;
import com.cli.cbnk.model.Branch;
import com.cli.cbnk.model.BranchAddress;
import com.cli.cbnk.model.CurrentAccount;
import com.cli.cbnk.model.Customer;
import com.cli.cbnk.model.Gender;
import com.cli.cbnk.model.PersonAddress;
import com.cli.cbnk.model.PersonInfo;
import com.cli.cbnk.model.Role;
import com.cli.cbnk.model.SavingAccount;
import com.cli.cbnk.model.Transaction;
import com.cli.cbnk.model.TransactionType;
import com.cli.cbnk.model.User;
import com.cli.cbnk.producer.PartcipantMsgProducer;
import com.cli.cbnk.service.util.AccountNumberGeneration;
import com.cli.cbnk.service.util.BNKMessageProcessingObject;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CustomerServiceImpl extends BranchTopicMsgListener implements CustomerService {

	@Autowired
	private Customer customer;

	@Autowired
	private PersonInfo personInfo;

	@Autowired
	private PersonAddress personAddress;

	@Autowired
	private User user;

	@Autowired
	private AccountNumberGeneration accountNumberGeneration;

	@Autowired
	private PartcipantMsgProducer partcipantMsgProducer;

	@Autowired
	private BranchTopicMsgListener branchTopicMsgListener;

	@Autowired
	private BNKMessageProcessingObject bnkMessageProcessingObject;

	@Value("${INITIAL_DEPOSITE_VALUE}")
	private long initialDepositeMoney;

	@Autowired
	private CustomerDao customerDao;

	@Autowired
	private Transaction transaction;

	@Autowired
	private Account account;

	@Autowired
	private PersonInfoDao personInfoDao;

	@Autowired
	private PersonAddressDao personAddressDao;

	@Autowired
	private TransactionDao transactionDao;

	@Autowired
	private BranchDao branchDao;

	@Autowired
	private Branch newBranch;

	@Autowired
	private BranchAddress newBranchAddress;

	@Autowired
	private BranchAddress branchAddress;

	@Autowired
	private SavingAccount savingAccount;

	@Autowired
	private CurrentAccount currentAccount;

	private static final Long INITIAL_ID = 1l;

	private static final String SAVING_ACCOUNT = "SAVING";

	private static final String CURRENT_ACCOUNT = "CURRENT";

	@Autowired
	private SavingAccountDao savingAccountDao;

	@Autowired
	private CurrentAccountDao currentAccountDao;

	@Override
	public Customer addNewCustomer(CustomerDTO customerDTO) {

		try {
			/**
			 * add customer personal user details, address
			 */
			addCustomerPersonalDetails(customerDTO);

			/**
			 * checking customer personal details added successfully or not
			 */
			isUserEnteredDetailsCorrect(customer.isOperation());

			/**
			 * assign customer new account number, branch details ,account opening date,
			 * account type
			 */
			addNewAccountDetails(customerDTO);
			/**
			 * checking customer personal details added successfully or not
			 */
			isUserEnteredDetailsCorrect(customer.isOperation());

			/**
			 * checking last customer id
			 */
			Long existingCustomerId = customerDao.findMaxExistingId();
			if (existingCustomerId == null) {
				customer.setCustmerId(INITIAL_ID);
			} else {
				customer.setCustmerId(++existingCustomerId);
			}
			customerDao.save(customer);
			return customer;
		} catch (InterruptedException e) {
			throw new RuntimeException();
		}
	}

	private boolean isUserEnteredDetailsCorrect(boolean isOperations) {
		if (isOperations == true) {
			return true;
		}
		throw new IllegalArgumentException("Please check user entered details", HttpStatus.BAD_REQUEST);
	}

	@SuppressWarnings("unused")
	private void addCustomerPersonalDetails(CustomerDTO customerDTO) {

		/**
		 * Started Adding Customer Personal Details
		 */

		user.setUserName(customerDTO.getUserDTO().getUserName());
		user.setPassword(customerDTO.getUserDTO().getPassword());
		if (customerDTO.getUserDTO().getRole() == null) {
			log.error("User type is mandotory. No need to proceed further");
			customer.setOperation(false);
			return;
		}
		Character userRoleType = Role.getAppropriateRole(customerDTO.getUserDTO().getRole());
		if (userRoleType != null) {
			user.setRole(userRoleType);
		}
		user.setSecurityQuestion(customerDTO.getUserDTO().getSecurityQuestion());
		user.setSecuirtyAnswer(customerDTO.getUserDTO().getSecuirtyAnswer());
		log.info("Started Adding Customer Person Details ...");

		personInfo.setPersonName(customerDTO.getUserDTO().getPersonInfoDTO().getPersonName());
		if (customerDTO.getUserDTO().getPersonInfoDTO().getGender() == null) {
			log.error("Gender filed manadotory. No Need to proceed further");
			customer.setOperation(false);
			return;
		}
		Character genderType = Gender.verifyGenders(customerDTO.getUserDTO().getPersonInfoDTO().getGender());
		if (genderType == null) {
			log.error("Unknown gender type. No Need to proceed further");
			customer.setOperation(false);
			return;
		}
		personInfo.setGender(genderType);
		personInfo.setDob(LocalDate.parse(customerDTO.getUserDTO().getPersonInfoDTO().getDob()));
		personInfo.setEmailId(customerDTO.getUserDTO().getPersonInfoDTO().getEmailId());
		personInfo.setMobileNo(customerDTO.getUserDTO().getPersonInfoDTO().getMobileNo());

		/**
		 * Started Adding Customer Personal Address Details
		 */

		personAddress.setCity(customerDTO.getUserDTO().getPersonInfoDTO().getPersonAddressDTO().getCity());
		personAddress.setArea(customerDTO.getUserDTO().getPersonInfoDTO().getPersonAddressDTO().getArea());
		personAddress.setState(customerDTO.getUserDTO().getPersonInfoDTO().getPersonAddressDTO().getState());
		personAddress.setPinCode(customerDTO.getUserDTO().getPersonInfoDTO().getPersonAddressDTO().getPinCode());

		personInfo.setAddress(personAddress);
		user.setPersonInfo(personInfo);
		customer.setUser(user);
		
		customer.setOperation(true);
	}

	private void addNewAccountDetails(CustomerDTO customerDTO) throws InterruptedException {

		log.info("Started Assiging customer account number and branch details.");
		/**
		 * generate unique account number
		 */
		account.setAccountNumber(Long.parseLong(accountNumberGeneration.generateUniqueAccountNumber()));

		/**
		 * check customer entered account type details is correct or not
		 */
		if (customerDTO.getAccountDTO().getAccountType() != null) {
			String enteredAccountType = AccountType.selectAccountType(customerDTO.getAccountDTO().getAccountType());
			if (enteredAccountType != null) {
				account.setAccountType(enteredAccountType);
			}
			if (enteredAccountType.equals(SAVING_ACCOUNT)) {
				Long lastSavingAccId = savingAccountDao.findLastSavingAccountId();
				if (lastSavingAccId == null) {
					savingAccount.setSavingAccountId(INITIAL_ID);
				} else {
					savingAccount.setSavingAccountId(++lastSavingAccId);
				}
				savingAccount.setMinimumBalance(initialDepositeMoney);
				savingAccount.setInterestRate(customerDTO.getAccountDTO().getSavingAccountDTO().getInterestRate());
				savingAccount.setTransactionAmountLimit(
						customerDTO.getAccountDTO().getSavingAccountDTO().getTransactionAmountLimit());
				savingAccount
						.setTransactionLimit(customerDTO.getAccountDTO().getSavingAccountDTO().getTransactionLimit());
				account.setSavingAccount(savingAccount);
			}
			if (enteredAccountType.equals(CURRENT_ACCOUNT)) {
				Long lastCurrActId = currentAccountDao.findLastCurrenctAcntId();
				if (lastCurrActId == null) {
					currentAccount.setCurrentAccountId(INITIAL_ID);
				} else {
					currentAccount.setCurrentAccountId(++lastCurrActId);
				}
				currentAccount.setMinimumBalance(initialDepositeMoney);
				currentAccount.setDailyTransactionsLimit(
						customerDTO.getAccountDTO().getCurrentAccountDTO().getDailyTransactionsLimit());
				currentAccount.setTransactionAmountLimit(
						customerDTO.getAccountDTO().getCurrentAccountDTO().getTransactionAmountLimit());
				account.setCurrentAccount(currentAccount);
			}
		}
		if (customerDTO.getAccountDTO().getAccountType() == null) {
			log.error("Account type is manadatory. No Need to proceed further.");
			customer.setOperation(false);
			return;
		}

		if (customerDTO.getAccountDTO().getTransactionDTO().getTransactionAmount() != initialDepositeMoney) {
			log.error("Initial Account Opening Money Is Not Matched. No Need to proceed further.");
			customer.setOperation(false);
			return;
		}

		transaction.setTransactionAmount(initialDepositeMoney);
		transaction.setTransactionDateTime(new Date());
		if (TransactionType.selectAppropriateTransactionType(customerDTO.getAccountDTO().getTransactionDTO()
				.getTransactionType()) != TransactionType.DEPOSITE.toString()) {
			log.error("Initial transaction type should be deposite only. No need to proceed further.");
			customer.setOperation(false);
			return;

		}

		transaction.setTransactionType(TransactionType.DEPOSITE.toString());

		account.setTransaction(transaction);

		/**
		 * checking customer entered branch id present or not in management
		 */
		partcipantMsgProducer.sendingMsgToManagementTopic(customerDTO.getAccountDTO().getBranchId());

		/**
		 * Sleeping Thread here because we want to start listener thread
		 */
		Thread.sleep(1000);

		/**
		 * assign branch details to customer
		 */
		assingBranchDetails();

		account.setAccountOpeningDate(new Date());

		customer.setAccount(account);

		customer.setOperation(true);
	}

	private void assingBranchDetails() {

		/**
		 * polling listened branch details
		 */
		BlockingQueue<Branch> branchDetails = bnkMessageProcessingObject.getBlockingQueue();
		Branch branch = branchDetails.poll();
		if (branch == null) {
			log.error("Branch details is null. No Need to proceed further.");
			customer.setOperation(false);
			return;
		}
		/**
		 * checking if branch details already present in proxy db or not 1) If yes, then
		 * not going add branch details, use existing branch details ,2) If No , then
		 * add new branch details
		 */
		List<Branch> existingBranchDetails = branchDao.findAll();
		for (Branch availableBranchDetails : existingBranchDetails) {
			if (availableBranchDetails.getBranchId() == branch.getBranchId()
					&& availableBranchDetails.getBranchName().equals(branch.getBranchName())
					&& availableBranchDetails.getPinCode().equals(branch.getPinCode())
					&& availableBranchDetails.getIfscCode().equals(branch.getIfscCode())
					&& availableBranchDetails.getAddress().getAddressId() == branch.getAddress().getAddressId()
					&& availableBranchDetails.getAddress().getArea().equals(branch.getAddress().getArea())
					&& availableBranchDetails.getAddress().getCity().equals(branch.getAddress().getCity())
					&& availableBranchDetails.getAddress().getState().equals(branch.getAddress().getState())
					&& availableBranchDetails.getAddress().getPinCode() == branch.getAddress().getPinCode()) {

				account.setBranchId(branch.getBranchId());
				//Optional<Long> newAccount=Optional.of(account.getBranchId());
				log.info("Branch details alreday present.");
				break;
			}

		}

		Optional<Long> newAccount=Optional.of(account.getBranchId());
		if (newAccount.isEmpty()) {
			newBranch.setBranchId(branch.getBranchId());
			newBranch.setBranchName(branch.getBranchName());
			newBranch.setPinCode(branch.getPinCode());
			newBranch.setIfscCode(branch.getIfscCode());
			newBranchAddress.setAddressId(branch.getAddress().getAddressId());
			newBranchAddress.setArea(branch.getAddress().getArea());
			newBranchAddress.setCity(branch.getAddress().getCity());
			newBranchAddress.setPinCode(branch.getAddress().getPinCode());
			newBranchAddress.setState(branch.getAddress().getState());
			newBranch.setAddress(newBranchAddress);
			branchDao.save(newBranch);
			log.info("New Branch details added.");
			customer.setOperation(true);
			account.setBranchId(branch.getBranchId());
		}
	}

}
