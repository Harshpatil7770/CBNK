package com.cli.bnk.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cli.bnk.service.AccountDetailsServiceImpl;
import com.cli.bnk.service.AccountTypeService;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
@RequestMapping("/api/accountType")
public class AccountTypeResource {

	@Autowired(required = true)
	private AccountDetailsServiceImpl accountDetailsServiceImpl;

	@PostMapping("/save")
	public void addAccountTypeDetails(@RequestBody String accountTypeDetails) throws JsonProcessingException {

		if (accountTypeDetails != null) {
			Thread.currentThread().setName("AccountTypeResource-" + System.currentTimeMillis() + "-Thread");
			accountDetailsServiceImpl.addNewAccountDetails(accountTypeDetails);
		}

	}
}
