package com.cli.bnk.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ManagerDTO {

	private long managerId;

	private long branchId;
	
	private boolean operationDetails;

	private UserDTO userDTO;
}
