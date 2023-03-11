package com.cli.bnk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @SpringBootApplication - It is combinations of three annotations. It shows main class of your
 *                          SpringBootApplication
 * @EnableAutoConfiuration - It configure your application automatically based on their dependencies
 * @ComponentScan - It is scan package for a bean.
 * @Configuration - This annotations used by spring container act as source bean definitions.
 * 
 * @EnableEurekaServer - This annotations is used to make your service as a Eureka server.
 */
@SpringBootApplication
@EnableEurekaServer
public class CbnkEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CbnkEurekaServerApplication.class, args);
		System.out.println("****************************************************");
		System.out.println("****************************************************");
		System.out.println("****************************************************");
		System.out.println("************** CBNK Eureka Server Started **********");
		System.out.println("****************************************************");
		System.out.println("****************************************************");
		System.out.println("****************************************************");

		/**
		 * What is purpose of eureka server and How to create it?
		 * 
		 * To enable eureka server we need to add Enable Eureka Server dependencies. and
		 * need to use @EnableEurekaServer annotations. By default port number of eureka
		 * server is 8761. Using eureka server we can see which service is up or down.
		 */
	}

}
