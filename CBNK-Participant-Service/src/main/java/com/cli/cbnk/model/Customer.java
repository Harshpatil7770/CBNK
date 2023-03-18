package com.cli.cbnk.model;

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
@Table(name = "CUSTOMER")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Customer {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "CUSTOMER_ID")
	private long custmerId;

	@JoinColumn(name = "CUSTOMER_USER_ID")
	@OneToOne(cascade = CascadeType.ALL)
	private User User;

	@JoinColumn(name = "CUSTOMER_ACCOUNT_NUMBER")
	@OneToOne(cascade = CascadeType.ALL)
	private Account account;

	@Column(name = "IS_OPERATION")
	private boolean isOperation;

}
