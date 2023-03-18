package com.cli.cbnk.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cli.cbnk.model.Transaction;

@Repository
public interface TransactionDao extends JpaRepository<Transaction, Long> {

//	@Query(value = "select max(TRANSACTION_ID) from Transaction", nativeQuery = true)
//	Long findExistingTransactionId();

}
