package com.cli.cbnk.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDTO {

	private long transactionId;

	private double transactionAmount;

	private Date transactionDateTime;

	private String transactionType;
}
