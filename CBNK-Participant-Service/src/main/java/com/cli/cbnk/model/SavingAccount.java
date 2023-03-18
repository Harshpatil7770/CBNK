package com.cli.cbnk.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "SAVING_ACCOUNT")
@Component
public class SavingAccount {

	@Id
	@Column(name = "SAVING_ACCOUNT_ID")
	private long savingAccountId;

	@Column(name = "INTEREST_RATE")
	private double interestRate;

	@Column(name = "MINIMUM_BALANCE")
	private double minimumBalance;

	@Column(name = "TRANSACTION_LIMIT")
	private int transactionLimit;

	@Column(name = "TRANSACTION_AMOUNT_LIMIT")
	private double transactionAmountLimit;
}
