package com.cli.cbnk.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Columns;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TRANSACTION")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Transaction {

	@Id
	@GeneratedValue
	@Column(name="TRANSACTION_ID")
	private long transactionId;
	
	@Column(name="TRANSACTION_AMOUNT")
	private double transactionAmount;
	
	@Column(name="TRANSACTION_DATE_TIME")
	private Date transactionDateTime;
	
	@Column(name="TRANSACTION_TYPE")
	private String transactionType;
	
	
}
