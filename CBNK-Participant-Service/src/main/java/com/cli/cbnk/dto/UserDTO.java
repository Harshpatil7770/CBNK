package com.cli.cbnk.dto;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.cli.cbnk.model.PersonInfo;

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
