package com.cli.cbnk.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cli.cbnk.model.Customer;

@Repository
public interface CustomerDao extends JpaRepository<Customer, Long> {

	@Query(value = "select max(CUSTOMER_ID) from customer c inner join PERSONAL_INFO pi on c.CUSTOMER_PERSON_INFO_ID=pi.PERSON_ID inner join PERSON_ADDRESS pa on pi.PERSON_ADDRESS_ID=pa.ADDRESS_ID inner join ACCOUNT ac on ac.ACCOUNT_NUMBER=c.CUSTOMER_ACCOUNT_NUMBER",nativeQuery = true)
	Long findMaxExistingId();
	
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
