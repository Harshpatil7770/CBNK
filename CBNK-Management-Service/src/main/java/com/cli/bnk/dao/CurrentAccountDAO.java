package com.cli.bnk.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cli.bnk.model.CurrentAccount;

public interface CurrentAccountDAO extends JpaRepository<CurrentAccount, Long> {

}
