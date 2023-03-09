package com.cli.bnk.service;

import java.util.List;

import com.cli.bnk.dto.ManagerDTO;
import com.cli.bnk.model.Branch;
import com.cli.bnk.model.Manager;

public interface ManagerService {

	public void addNewManager(ManagerDTO managerDTO);

	public void updateExistingManagerDetails(ManagerDTO managerDTO);

	public Manager findManagerDetailsByManagerId(long managerId);

	public List<Manager> findAllManagerRecords();

	public Manager findManagerDetailsByName(String personName);

	public Manager findBranchManagerDetailsByManagerName(String branchName);

	public Manager findBranchManagerDetailsByManagerId(long managerId);

	public List<Manager> findAllMaleOrFemaleEmployeeLists(char enterFirstLetter);

	public void deleteAllManagerRecords();

	public void deleteManagerByManagerId(Long managerId);

}
