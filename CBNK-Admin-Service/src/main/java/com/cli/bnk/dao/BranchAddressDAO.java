package com.cli.bnk.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cli.bnk.model.BranchAddress;

@Repository
public interface BranchAddressDAO extends JpaRepository<BranchAddress, Long> {

	@Query(value = "select max(branch_id) from branch_address ba inner join branch b where ba.address_id=b.branch_id",nativeQuery = true)
	Long findLastBranchAddressId();

//	@Query(value = "select max(address_id) from address a inner join personal_info p where a.address_id=p.person_id", nativeQuery = true)
//	Long findLastPersonalAddressId();
//
//	@Query(value = "select max(address_id) from address a inner join branch b where a.address_id=b.branch_id", nativeQuery = true)
//	Long findLastBranchAddressId();

//	@Query(value = "select max(address_id) from address", nativeQuery = true)
//	Long findLastBranchAddressIdNumber();

}
