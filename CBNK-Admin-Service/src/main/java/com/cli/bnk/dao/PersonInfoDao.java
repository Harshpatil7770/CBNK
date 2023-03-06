package com.cli.bnk.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cli.bnk.model.PersonInfo;

@Repository
public interface PersonInfoDao extends JpaRepository<PersonInfo, Long> {

	@Query(value = "select max(person_id) from personal_info p inner join user u where p.person_id=u.user_id", nativeQuery = true)
	Long checkPreviousPersonInfoId();

}
