package com.cli.cbnk.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ACCOUNT")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Component
public class Account {

	@Id
	@Column(name = "ACCOUNT_NUMBER")
	private long accountNumber;

	@Column(name = "ACCOUNT_TYPE")
	private String accountType;

	@Column(name = "CUSTOMER_SAVING_ACCOUNT_ID")
	private Long savingAccountId;

	@Column(name = "CUSTOMER_CURRENT_ACCOUNT_ID")
	private Long currentAccountId;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ACCOUNT_TRANSACTION_ID")
	private Transaction transaction;

	@Column(name = "ACCOUNT_BRANCH_ID")
	private Long branchId;

	@Column(name = "ACCOUNT_OPENING_DT")
	private Date accountOpeningDate;

}
