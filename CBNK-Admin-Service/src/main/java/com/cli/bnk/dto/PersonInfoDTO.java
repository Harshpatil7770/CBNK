package com.cli.bnk.dto;

import com.cli.bnk.model.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonInfoDTO {

	private long personId;

	private String personName;

	private char gender;

	private String dob;

	private String emailId;

	private long mobileNo;

	private User user;

	private PersonAddressDTO personAddressDTO;
}
