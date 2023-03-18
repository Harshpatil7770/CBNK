package com.cli.cbnk.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cli.cbnk.model.BranchAddress;

public interface BranchAddressDao extends JpaRepository<BranchAddress, Long>{

}
