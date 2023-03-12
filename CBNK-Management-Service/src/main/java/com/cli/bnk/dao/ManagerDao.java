package com.cli.bnk.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cli.bnk.model.Manager;

@Repository
public interface ManagerDao extends JpaRepository<Manager, Long> {

	@Query(value = "select max(manager_id) from manager m inner join branch b where m.branch_id=b.branch_id", nativeQuery = true)
	Long findLastManagerId();

//	@Query(value = "select * from MANAGER",nativeQuery = true)
//	Manager findExistingManager();

}
