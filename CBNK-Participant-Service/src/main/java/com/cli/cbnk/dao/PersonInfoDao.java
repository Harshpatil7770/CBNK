package com.cli.cbnk.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cli.cbnk.model.PersonInfo;

@Repository
public interface PersonInfoDao extends JpaRepository<PersonInfo, Long> {

//	@Query(value = "select max(person_id) from customer c inner join PERSONAL_INFO pi on c.CUSTOMER_PERSON_INFO_ID=pi.PERSON_ID inner join PERSON_ADDRESS pa on pi.PERSON_ADDRESS_ID=pa.ADDRESS_ID",nativeQuery = true)
//	Long findLastPersonAddressId();

}
