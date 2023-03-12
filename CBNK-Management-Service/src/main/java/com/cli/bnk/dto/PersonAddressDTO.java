package com.cli.bnk.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonAddressDTO {

	private long addressId;

	private String city;

	private String area;

	private String state;

	private int pinCode;

	
}
