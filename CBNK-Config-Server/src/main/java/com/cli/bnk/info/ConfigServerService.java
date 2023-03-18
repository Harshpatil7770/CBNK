package com.cli.bnk.info;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ConfigServerService {

	@EventListener(ApplicationReadyEvent.class)
	public void bootStrapService() {
		System.out.println("****************************************************");
		System.out.println("****************************************************");
		System.out.println("****************************************************");
		System.out.println("************* Welcome To CBNK Appplication *********");
		System.out.println("****************************************************");
		System.out.println("****************************************************");
		System.out.println("****************************************************");
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			System.err.println(e.getStackTrace());
		}
		System.out.println("****************************************************");
		System.out.println("****************************************************");
		System.out.println("****************************************************");
		System.out.println("************** CBNK Config Server Started **********");
		System.out.println("****************************************************");
		System.out.println("****************************************************");
		System.out.println("****************************************************");
	}
}
