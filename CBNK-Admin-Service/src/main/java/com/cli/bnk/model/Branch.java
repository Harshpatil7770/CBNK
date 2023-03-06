package com.cli.bnk.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Component
@Table(name = "BRANCH")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Branch {

	@Id
	@GeneratedValue
	@Column(name = "BRANCH_ID")
	private long branchId;

	@Column(name = "BRANCH_NAME")
	private String branchName;

	@Column(name = "IFSC_CODE")
	private String ifscCode;

	@Column(name = "PIN_CODE")
	private String pinCode;
	
	@OneToOne
	@Cascade(CascadeType.ALL)
	private BranchAddress address;

}
