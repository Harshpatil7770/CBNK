package com.cli.bnk.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service
public interface AccountTypeService {

	public void addAccountTypeDetails(String t);
}
