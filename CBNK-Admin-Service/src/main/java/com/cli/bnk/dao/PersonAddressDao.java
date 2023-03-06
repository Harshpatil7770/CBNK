package com.cli.bnk.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cli.bnk.model.PersonAddress;

@Repository
public interface PersonAddressDao extends JpaRepository<PersonAddress, Long>{

	@Query(value = "select max(address_id) from person_address pa inner join personal_info pi where pa.address_id=pi.person_id",nativeQuery = true)
	Long findLastBranchAddressId();

}
