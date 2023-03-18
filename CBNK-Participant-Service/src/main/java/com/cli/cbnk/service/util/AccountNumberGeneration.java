package com.cli.cbnk.service.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.cli.cbnk.dao.CustomerDao;
import com.cli.cbnk.exceptionhandeler.IllegalArgumentException;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AccountNumberGeneration {

	@Autowired
	private CustomerDao customerDao;

	private static final String FIRST_STARTS_WITH = "6000";
	//private static final String SECOND_STARTS_WITH = "7000";

	private static final String INTITIAL_ACCOUNT_GENERATION_NUMBER = "100";

	@SuppressWarnings("unused")
	public String generateUniqueAccountNumber() {

		Long existingPresentMaxId = customerDao.findMaxExistingId();
		if (existingPresentMaxId == null) {
			// 6000100
			return FIRST_STARTS_WITH.concat(INTITIAL_ACCOUNT_GENERATION_NUMBER);
		} else if (existingPresentMaxId < 999999999) {
			return (String.valueOf(++existingPresentMaxId));
		} else {
			log.error("New Series required to add for customer account number");
			throw new IllegalArgumentException("New Series required to add for customer account number",
					HttpStatus.BAD_REQUEST);
		}
	}
}
