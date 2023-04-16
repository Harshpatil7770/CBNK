package com.cli.bnk.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cli.bnk.model.SavingAccount;

public interface SavingAccountDao extends JpaRepository<SavingAccount, Long>{

//	@Query(value = "select max(saving_account_id) from SAVING_ACCOUNT",nativeQuery = true)
//	long findLastSavingAccountId();

}
