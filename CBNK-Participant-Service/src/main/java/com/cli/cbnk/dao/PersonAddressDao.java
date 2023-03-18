package com.cli.cbnk.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonAddressDao extends JpaRepository<com.cli.cbnk.model.PersonAddress, Long> {

//	@Query(value = "select max(ADDRESS_ID) from PERSON_ADDRESS", nativeQuery = true)
//	Long findLastPersonAddressId();

}
