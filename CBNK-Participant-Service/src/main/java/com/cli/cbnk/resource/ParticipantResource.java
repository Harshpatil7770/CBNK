package com.cli.cbnk.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cli.cbnk.dto.CustomerDTO;
import com.cli.cbnk.model.Customer;
import com.cli.cbnk.service.CustomerService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/customer")
@Slf4j
public class ParticipantResource {

	@Autowired
	private CustomerService customerService;

	@PostMapping("/save")
	public ResponseEntity<?> addNewCustomer(@RequestBody CustomerDTO customerDTO) {
		if (customerDTO != null) {
			Thread.currentThread().setName("ParticipantResource-" + System.currentTimeMillis() + "-Thread");
			log.info("Recieved Message : {} ", customerDTO);
			Customer customer = customerService.addNewCustomer(customerDTO);
			if (customer != null) {
				return new ResponseEntity<>("Added New Customer Details Succesfully", HttpStatus.CREATED);
			}

		}
		return new ResponseEntity<String>("Unable To add new Customer", HttpStatus.BAD_REQUEST);
	}
}
