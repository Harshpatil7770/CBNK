package com.cli.bnk.service;

import java.util.List;

import com.cli.bnk.dto.BranchDTO;
import com.cli.bnk.model.Branch;

public interface BranchService {

	public void addNewBranch(BranchDTO branchDTO) throws Exception;

	public Branch findBranchDetailsUsingByName(String branchName);

	public Branch findBranchDetailsUsingBranchId(long branchId);

	public List<Branch> findAllBranchesDetails();

	public List<Branch> findAllBranchesPresentInSameArea(String area);

	public List<Branch> findAllBranchesPresentSameState(String state);

	public List<Branch> findBranchDetailsPresentInSamePinCode(int pinCode);

	public void deleteBranchByBranchId();

	public void deleteAllBranchesRecords();
}
