package com.cli.cbnk.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "BRANCH_ADDRESS")
@Getter
@Setter
@Component
public class BranchAddress {

	@Id
	@GeneratedValue
	@Column(name = "ADDRESS_ID")
	private long addressId;

	@Column(name = "CITY")
	private String city;

	@Column(name = "AREA")
	private String area;

	@Column(name = "STATE")
	private String state;

	@Column(name = "PINCODE")
	private int pinCode;
	
}
