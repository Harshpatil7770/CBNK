package com.cli.cbnk.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cli.cbnk.model.SavingAccount;

public interface SavingAccountDao extends JpaRepository<SavingAccount, Long> {

	@Query(value = "select max(saving_account_id) from saving_account sa inner join account a where sa.saving_account_id=a.customer_saving_account_id", nativeQuery = true)
	Long findLastSavingAccountId();

}
