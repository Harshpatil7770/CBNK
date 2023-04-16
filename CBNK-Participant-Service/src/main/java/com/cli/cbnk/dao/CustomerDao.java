package com.cli.cbnk.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cli.cbnk.model.Customer;

@Repository
public interface CustomerDao extends JpaRepository<Customer, Long> {

	@Query(value = "select max(CUSTOMER_ACCOUNT_NUMBER) from customer c inner join user_info u on c.customer_user_id=u.user_id inner join personal_info pi on pi.person_id=u.user_person_id inner join ACCOUNT ac on ac.ACCOUNT_NUMBER=c.CUSTOMER_ACCOUNT_NUMBER",nativeQuery = true)
	Long findMaxExistingId();

	@Query(value = "select * from customer where CUSTOMER_ACCOUNT_NUMBER=?",nativeQuery = true)
	Customer findCustomerDetailsById(long accountId);
	
//	@Query(value="BEGIN;    \r\n"
//			+ "insert into customer values(?,?,?,?);\r\n"
//			+ "insert into personal_info values(?,?,?,?,?,?,?);\r\n"
//			+ "insert into person_address values(?,?,?,?,?);\r\n"
//			+ "insert into account values (?,?,?,?);\r\n"
//			+ "insert into branch values (?,?,?,?,?);\r\n"
//			+ "insert into branch_address values (?,?,?,?,?);\r\n"
//			+ "insert into transaction values (?,?,?,?);\r\n"
//			+ "COMMIT;",nativeQuery = true)
//	void saveToDB(Customer customer);
	
	
//	value
//	Query(Value)

}
