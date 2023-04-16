package com.cli.cbnk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cli.cbnk.dao.CurrentAccountDao;
import com.cli.cbnk.dao.SavingAccountDao;
import com.cli.cbnk.model.CurrentAccount;
import com.cli.cbnk.model.SavingAccount;
import com.google.gson.Gson;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AccountTypeDetailsImpl {

	@Autowired
	private SavingAccount savingAccount;

	@Autowired
	private SavingAccountDao savingAccountDao;

	@Autowired
	private CurrentAccount currentAccount;

	@Autowired
	private CurrentAccountDao currentAccountDao;

	private String firstCharacter;

	private String lastCharacter;

	public String getLastCharacter() {
		return lastCharacter;
	}

	public String getFirstCharacter() {
		return firstCharacter;
	}

	public void setLastCharacter(String lastCharacter) {
		this.lastCharacter = lastCharacter;
	}

	public void setFirstCharacter(String firstCharacter) {
		this.firstCharacter = firstCharacter;
	}

	public void processAccountDetailsMsg(String msg) {
		String formatedString = msg.substring(1, msg.length() - 1);
		String formattedString = formatedString.replace("\\", "");
		Gson gson = new Gson();
		if (formattedString.contains("SAVING")) {
			log.info(formattedString);
			SavingAccount savingAccountDetails = gson.fromJson(formattedString, SavingAccount.class);
			savingAccount.setInterestRate(savingAccountDetails.getInterestRate());
			savingAccount.setMinimumBalance(savingAccountDetails.getMinimumBalance());
			savingAccount.setTransactionLimit(savingAccountDetails.getTransactionLimit());
			savingAccount.setTransactionAmountLimit(savingAccountDetails.getTransactionAmountLimit());
			savingAccount.setAccountType(savingAccountDetails.getAccountType());
			savingAccountDao.save(savingAccount);
			log.info("Saving Account Type details persisted into DB succesfully : {} ", savingAccount);
		} else {
			CurrentAccount currentAccountDetails = gson.fromJson(formattedString, CurrentAccount.class);
			currentAccount.setMinimumBalance(currentAccountDetails.getMinimumBalance());
			currentAccount.setInterestRate(currentAccountDetails.getInterestRate());
			currentAccount.setDailyTransactionsLimit(currentAccountDetails.getDailyTransactionsLimit());
			currentAccount.setTransactionAmountLimit(currentAccountDetails.getTransactionAmountLimit());
			currentAccount.setAccountType(currentAccountDetails.getAccountType());
			currentAccountDao.save(currentAccount);
			log.info("Current Account Type details persisted into DB succesfully : {} ", currentAccount);
		}
	}

}
