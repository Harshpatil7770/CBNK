package com.cli.cbnk.service;

import com.cli.cbnk.dto.CustomerDTO;
import com.cli.cbnk.model.Customer;

public interface CustomerService {

	public Customer addNewCustomer(CustomerDTO customerDTO);
	
	public void deleteCustomerAccount(long accountId,String userName,String password);
}
