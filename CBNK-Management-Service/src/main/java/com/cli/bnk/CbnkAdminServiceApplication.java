package com.cli.bnk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import com.cli.bnk.model.SavingAccount;

@SpringBootApplication
@EnableDiscoveryClient
public class CbnkAdminServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CbnkAdminServiceApplication.class, args);
	}

//	@Bean
//	public SavingAccount getSavingAccountObject() {
//		return new SavingAccount();
//	}
}
