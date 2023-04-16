package com.cli.cbnk.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {

	// private long accountNumber;

//	private String accountType;
//	
//	private String transactionType;
//
//	private long branchId;
//
//	private double initialDepositeMoney;
//
//	private TransactionDTO transactionDTO;

	// private long accountNumber;

	private String accountType;

//	@OneToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "ACCOUNT_TRANSACTION_ID")
	private TransactionDTO transactionDTO;

//	@OneToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "BRANCH_BRANCH_ID")
	private long branchId;

	private long savingAccountId;

	private long currentAccountId;
}
