package com.cli.cbnk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class CbnkParticipantServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CbnkParticipantServiceApplication.class, args);
	}

}
