package com.cli.cbnk.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SavingAccountDTO {

	private long savingAccountId;

	private double interestRate;


	private double minimumBalance;

	private int transactionLimit;

	private double transactionAmountLimit;
}
