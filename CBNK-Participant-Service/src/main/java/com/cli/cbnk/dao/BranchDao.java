package com.cli.cbnk.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cli.cbnk.model.Branch;

public interface BranchDao extends JpaRepository<Branch, Long> {

//	@Query(value="select max(branch_id) from account a inner join branch b on a.BRANCH_BRANCH_ID=b.BRANCH_ID inner join\r\n"
//			+ "branch_address ba where b.branch_id=ba.address_id",nativeQuery = true)
//	Long findLastBranchId();

}
