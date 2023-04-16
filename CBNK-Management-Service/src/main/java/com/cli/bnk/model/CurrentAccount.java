package com.cli.bnk.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="CURRENCT_ACCOUNT")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class CurrentAccount {

	@Id
	@Column(name="ACCOUNT_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long currentAccountId;
	
	@Column(name="DAILY_TRANSACTION_LIMIT")
	private int dailyTransactionsLimit;
	
	@Column(name="MINIMUM_BALANCE")
	private double minimumBalance;
	
	@Column(name="TRANSACTION_AMOUNT_LIMIT")
	private double transactionAmountLimit;
	
	@Column(name="ACCOUNT_TYPE")
	private String accountType;
}
