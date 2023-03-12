package com.cli.bnk.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BranchAddressDTO {

	private long addressId;

	private String city;

	private String area;

	private String state;

	private int pinCode;

}
