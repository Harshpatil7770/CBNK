package com.cli.cbnk.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonInfoDTO {

	private long personId;

	private String personName;

	private Character gender;

	private String dob;

	private String emailId;

	private long mobileNo;

	private PersonAddressDTO personAddressDTO;
}
