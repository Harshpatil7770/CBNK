package com.cli.bnk.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cli.bnk.model.Branch;

@Repository
public interface BranchDao extends JpaRepository<Branch, Long> {

	@Query(value = "select max(branch_id) from branch b inner join manager m where b.branch_id=m.manager_id",nativeQuery = true)
	Long findLastBranchId();

}
