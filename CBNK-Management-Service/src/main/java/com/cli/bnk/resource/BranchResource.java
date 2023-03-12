package com.cli.bnk.resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cli.bnk.dto.BranchDTO;
import com.cli.bnk.service.BranchService;

@RestController
@RequestMapping("/api/branch")
public class BranchResource {

	private final Logger logger = LogManager.getLogger(BranchResource.class);

	@Autowired
	private BranchService branchService;

	@PostMapping("/save")
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public void addNewBranch(@RequestBody BranchDTO branchDTO) throws Exception {
		if (branchDTO != null) {

			Thread.currentThread().setName("BranchResource-" + System.currentTimeMillis() + "-Thread");
			logger.info("Recived BranchDTO details : {} ", branchDTO);
			branchService.addNewBranch(branchDTO);
			logger.info("Added New Branch Succesfully");
		}
	}
}
