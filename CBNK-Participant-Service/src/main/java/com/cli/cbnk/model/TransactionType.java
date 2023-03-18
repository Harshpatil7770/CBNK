package com.cli.cbnk.model;

import com.cli.cbnk.exceptionhandeler.TransactionUnknownTypeException;

public enum TransactionType {

	DEPOSITE("DEPOSITE", 1), WITHDRAWAL("WITHDRAWAL", 2), FUND_TRANSFER("FUND_TRANSFER", 3);

	private String transactionType;

	private int transactionId;

	private TransactionType(String transactionType, int transactionId) {
		this.transactionType = transactionType;
		this.transactionId = transactionId;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public int getTransactionId() {
		return transactionId;
	}

	public static String selectAppropriateTransactionType(String userEnteredTransType) {
		for (TransactionType availableTypes : TransactionType.values()) {
			if (availableTypes.getTransactionType().equals(userEnteredTransType)) {
				return availableTypes.getTransactionType();
			}
		}
		throw new TransactionUnknownTypeException();
	}

}
