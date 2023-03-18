package com.cli.cbnk.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cli.cbnk.model.Account;

public interface AccountDao extends JpaRepository<Account, Long>{

}
