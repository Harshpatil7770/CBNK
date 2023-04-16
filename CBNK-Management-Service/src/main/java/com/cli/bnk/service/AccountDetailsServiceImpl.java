package com.cli.bnk.service;

/**
 * @author patil_ha
 */

import java.util.Comparator;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cli.bnk.commonprocessor.BaseMessageProcessor;
import com.cli.bnk.dao.CurrentAccountDAO;
import com.cli.bnk.dao.SavingAccountDao;
import com.cli.bnk.model.AccountType;
import com.cli.bnk.model.CurrentAccount;
import com.cli.bnk.model.ErrorVO;
import com.cli.bnk.model.SavingAccount;
import com.cli.bnk.outboundmsgsender.AccountTypeMsgSender;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AccountDetailsServiceImpl extends BaseMessageProcessor implements AccountDetailsService {

	@Autowired
	private SavingAccountDao savingAccountDao;

	@Autowired
	private SavingAccount savingAccount;

	@Autowired
	private CurrentAccountDAO currentAccountDAO;

	@Autowired
	private CurrentAccount currentAccount;

	@Autowired
	private AccountTypeMsgSender accountTypeMsgSender;

	ObjectMapper objectMapper = new ObjectMapper();

	private static final Long INITIAL_ACCOUNT_ID = 1l;

	private String accountTypeDetails;

	// private String savingAccountTypeDetails;

	public String getAccountTypeDetails() {
		return accountTypeDetails;
	}

	public void setAccountTypeDetails(String accountTypeDetails) {
		this.accountTypeDetails = accountTypeDetails;
	}

	@Override
	public void addNewAccountDetails(String accountdDetails) throws JsonProcessingException {

		Gson gson = new Gson();
		if (accountdDetails.contains(AccountType.SAVING_ACCOUNT.getAccountType())) {
			log.info("Recieved Saving Account Type Details Message : {} ",accountdDetails);
			SavingAccount savingAccountDetails = gson.fromJson(accountdDetails, SavingAccount.class);
			List<SavingAccount> savingAccountDetailsLists = savingAccountDao.findAll();
			log.info("Fetched {} saving account details", savingAccountDetailsLists.size());

			if (savingAccountDetailsLists.size() > 1 || savingAccountDetailsLists.size() == 1) {
				long startTime = System.currentTimeMillis();
				Optional<SavingAccount> maxSavingAccountID = savingAccountDetailsLists.stream()
						.collect(Collectors.maxBy(Comparator.comparing(SavingAccount::getSavingAccountId)));
				log.info("Time required to identify max saving account id is {} ms",
						System.currentTimeMillis() - startTime);
				Long lastMaxAccountId = maxSavingAccountID.get().getSavingAccountId();
				savingAccount.setSavingAccountId(++lastMaxAccountId);
			} else {
				savingAccount.setSavingAccountId(INITIAL_ACCOUNT_ID);
			}
			savingAccount.setInterestRate(savingAccountDetails.getInterestRate());
			savingAccount.setMinimumBalance(savingAccountDetails.getMinimumBalance());
			savingAccount.setTransactionLimit(savingAccountDetails.getTransactionLimit());
			savingAccount.setTransactionAmountLimit(savingAccountDetails.getTransactionAmountLimit());
			savingAccount.setAccountType(savingAccountDetails.getAccountType());
			savingAccountDao.save(savingAccount);
			log.info("New Saving Account Details Added Succesfully");
			accountTypeDetails = objectMapper.writeValueAsString(savingAccount);
		}

		if (accountdDetails.contains(AccountType.CURRENCT_ACCOUNT.getAccountType())) {

			log.info("Recieved Current Account Details Type Message : {} ",accountdDetails);
			CurrentAccount currentAccountDetails = gson.fromJson(accountdDetails, CurrentAccount.class);
			long startTime = System.currentTimeMillis();
			List<CurrentAccount> currentAccountDetailsLists = currentAccountDAO.findAll();
			log.info("Time required to fetch lists of current account details is {} ms ",
					System.currentTimeMillis() - startTime);
			if (currentAccountDetailsLists.size() < 1) {
				currentAccount.setCurrentAccountId(INITIAL_ACCOUNT_ID);
			} else {
				long startTime1 = System.currentTimeMillis();
				Optional<CurrentAccount> lastCurrentAccountDetails = currentAccountDetailsLists.stream()
						.collect(Collectors.maxBy(Comparator.comparing(CurrentAccount::getCurrentAccountId)));
				log.info("Time required to filter the data {} ms", System.currentTimeMillis() - startTime1);
				long maxCurrentAccountId = lastCurrentAccountDetails.get().getCurrentAccountId();
				currentAccount.setCurrentAccountId(++maxCurrentAccountId);
			}
			currentAccount.setDailyTransactionsLimit(currentAccountDetails.getDailyTransactionsLimit());
			currentAccount.setTransactionAmountLimit(currentAccountDetails.getTransactionAmountLimit());
			currentAccount.setMinimumBalance(currentAccountDetails.getMinimumBalance());
			currentAccount.setTransactionAmountLimit(currentAccountDetails.getTransactionAmountLimit());
			currentAccount.setAccountType(currentAccountDetails.getAccountType());
			currentAccountDAO.save(currentAccount);
			log.info("New Current Account Details Added Succesfully.");
			accountTypeDetails = objectMapper.writeValueAsString(currentAccount);
		}
		if (!accountdDetails.contains(AccountType.SAVING_ACCOUNT.getAccountType())
				&& !accountdDetails.contains(AccountType.CURRENCT_ACCOUNT.getAccountType())) {
			log.info("Recieved neither saving or current account type details. No need to proceed further.");
			ErrorVO errorVO = constructAccountTypeJsonDataErrorVo(accountdDetails);
			log.info("ErrorVO {} ", errorVO.toString());
			return;
		}
		log.info("converting object into String {} ", accountTypeDetails);
		sendingSavingAccountDetailsToParticipantService(accountTypeDetails);
	}

	private void sendingSavingAccountDetailsToParticipantService(String accountTypeDetails) {

		try {
			accountTypeMsgSender.msgSenderFromMangToParticipant(accountTypeDetails);
		} catch (Exception e) {

		}
	}

}
