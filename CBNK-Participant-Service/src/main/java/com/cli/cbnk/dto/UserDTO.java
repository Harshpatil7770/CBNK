package com.cli.cbnk.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

	private long userId;

	private String userName;

	private String password;

	private String securityQuestion;

	private String secuirtyAnswer;

	private Character role;

	private PersonInfoDTO personInfoDTO;
}
