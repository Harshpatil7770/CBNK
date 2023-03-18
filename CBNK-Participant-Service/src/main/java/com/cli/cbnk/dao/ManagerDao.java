package com.cli.cbnk.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cli.cbnk.model.Manager;

@Repository
public interface ManagerDao extends JpaRepository<Manager, Long> {

}
