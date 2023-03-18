package com.cli.cbnk.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrentAccountDTO {

	private int currentAccountId;

	private int dailyTransactionsLimit;

	private double minimumBalance;

	private double transactionAmountLimit;
}
