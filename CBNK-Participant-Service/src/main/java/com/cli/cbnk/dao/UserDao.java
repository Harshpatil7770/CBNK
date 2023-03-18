package com.cli.cbnk.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cli.cbnk.model.User;

public interface UserDao extends JpaRepository<User, Long> {

}
