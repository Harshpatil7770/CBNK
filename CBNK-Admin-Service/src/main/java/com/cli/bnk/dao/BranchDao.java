package com.cli.bnk.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cli.bnk.model.Branch;

@Repository
public interface BranchDao extends JpaRepository<Branch, Long> {

	@Query(value = "select max(branch_id) from branch b inner join BRANCH_ADDRESS ba where b.branch_id=ba.address_id;", nativeQuery = true)
	Long findLastBranchId();

	@Query(value = "select * from branch where branch_id=?", nativeQuery = true)
	Branch isBranchAvailable(long branchId);

}
