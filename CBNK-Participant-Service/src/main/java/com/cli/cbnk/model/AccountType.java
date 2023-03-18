package com.cli.cbnk.model;

import org.springframework.http.HttpStatus;

import com.cli.cbnk.exceptionhandeler.MisMatchAccountType;

public enum AccountType {

	SAVING_ACCOUNT("SAVING", 1), CURRENCT_ACCOUNT("CURRENT", 2);

	private String accountType;

	private int accountTypeId;

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public int getAccountTypeId() {
		return accountTypeId;
	}

	public void setAccountTypeId(int accountTypeId) {
		this.accountTypeId = accountTypeId;
	}

	private AccountType(String accountType, int accountTypeId) {
		this.accountType = accountType;
		this.accountTypeId = accountTypeId;
	}

	public static String selectAccountType(String accountType) {
		for (AccountType existingAccountType : AccountType.values()) {
			if (existingAccountType.getAccountType().equals(accountType)) {
				return existingAccountType.getAccountType();
			}
		}
		throw new MisMatchAccountType("Please enter correct account type",HttpStatus.BAD_REQUEST);
	}

}
