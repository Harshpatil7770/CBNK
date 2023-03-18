package com.cli.bnk.info;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class EurekaServerService {

	@EventListener(ApplicationReadyEvent.class)
	public void bootStrapService() {

		System.out.println("****************************************************");
		System.out.println("****************************************************");
		System.out.println("****************************************************");
		System.out.println("************** CBNK Eureka Server Started **********");
		System.out.println("****************************************************");
		System.out.println("****************************************************");
		System.out.println("****************************************************");
	}
}
