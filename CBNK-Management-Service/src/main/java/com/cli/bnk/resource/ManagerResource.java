package com.cli.bnk.resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cli.bnk.dto.ManagerDTO;
import com.cli.bnk.model.Manager;
import com.cli.bnk.service.ManagerService;

@RestController
@RequestMapping("/api/admin")
public class ManagerResource {

	private Logger logger = LogManager.getLogger(ManagerResource.class);

	@Autowired
	private ManagerService adminService;

	@PostMapping("/save")
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public ResponseEntity<?> addNewManager(@RequestBody ManagerDTO managerVO) throws Exception {
		Manager manager = null;
		if (managerVO != null) {
			Thread.currentThread().setName("ManagerResource-" + System.currentTimeMillis() + "-Thread");
			logger.info("Recived Manager details : {}", managerVO);
			manager = adminService.addNewManager(managerVO);
			if (manager != null) {
				return new ResponseEntity<>(manager, HttpStatus.CREATED);
			}
		}
		return new ResponseEntity<>("Unable to add New Manager Details", HttpStatus.CREATED);
	}
}
