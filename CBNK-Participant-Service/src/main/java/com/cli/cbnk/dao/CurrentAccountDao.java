package com.cli.cbnk.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cli.cbnk.model.CurrentAccount;

public interface CurrentAccountDao extends JpaRepository<CurrentAccount, Long>{

	@Query(value = "select max(account_id) from currenct_account ca inner join account a where ca.account_id=a.customer_current_account_id",nativeQuery = true)
	Long findLastCurrenctAcntId();

	
}
