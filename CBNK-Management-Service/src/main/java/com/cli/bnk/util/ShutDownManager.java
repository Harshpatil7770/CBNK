package com.cli.bnk.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class ShutDownManager {

	@Autowired
	private ApplicationContext applicationContext;

	public void initiateShutDown(int errorCode) {
		try {
			SpringApplication.exit(applicationContext, () -> errorCode);
		} catch (Exception e) {

		}
	}

}
