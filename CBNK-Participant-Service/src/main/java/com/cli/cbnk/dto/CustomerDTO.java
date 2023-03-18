package com.cli.cbnk.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {

	private long custmerId;

	private UserDTO userDTO;

	private AccountDTO accountDTO ;
	
	private boolean isOperation;

}
