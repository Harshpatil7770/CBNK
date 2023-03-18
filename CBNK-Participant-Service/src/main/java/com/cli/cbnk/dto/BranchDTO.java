package com.cli.cbnk.dto;

import com.cli.cbnk.model.BranchAddress;

import lombok.Data;
import lombok.Setter;

@Data
@Setter
public class BranchDTO {

	private Long branchId;

	private String branchName;

	private String ifscCode;

	private String pinCode;

	private BranchAddress address;
}
