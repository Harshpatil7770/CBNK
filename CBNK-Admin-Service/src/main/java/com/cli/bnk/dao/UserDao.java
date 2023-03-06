package com.cli.bnk.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cli.bnk.model.User;

@Repository
public interface UserDao extends JpaRepository<User, Long> {

	@Query(value = "select max(user_id) from user u inner join manager m where u.user_id=m.manager_id", nativeQuery = true)
	Long findLastUserId();

}
