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

import com.cli.bnk.dto.ManagerDTO;
import com.cli.bnk.service.AdminService;

@RestController
@RequestMapping("/api/admin")
public class AdminResource {

	private Logger logger = LogManager.getLogger(AdminResource.class);

	@Autowired
	private AdminService adminService;

	@PostMapping("/save")
	//@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public void addNewManager(@RequestBody ManagerDTO managerVO) {
		Thread.currentThread().setName("AdminResource-" + System.currentTimeMillis() + "-Thread");
		logger.info("Recived Manager details : {}", managerVO);
		adminService.addNewManager(managerVO);
	}
}
