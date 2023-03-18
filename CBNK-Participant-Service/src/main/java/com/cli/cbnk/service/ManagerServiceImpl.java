package com.cli.cbnk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cli.cbnk.dao.ManagerDao;
import com.cli.cbnk.model.Manager;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ManagerServiceImpl implements ManagerService {

	@Autowired
	private ManagerDao managerDao;

	@Override
	public void proceedRecivedData(Manager manager) {
		managerDao.save(manager);
		log.info("Saved manager details in participant side.");
	}

}
