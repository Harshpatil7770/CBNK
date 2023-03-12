package com.cli.cbnk.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.stereotype.Component;

import lombok.Getter;

@Entity
@Component
@Table(name = "BRANCH")
@Getter
public class Branch {

	@Id
	@GeneratedValue
	@Column(name = "BRANCH_ID")
	private Long branchId;

	@Column(name = "BRANCH_NAME")
	private String branchName;

	@Column(name = "IFSC_CODE")
	private String ifscCode;

	@Column(name = "PIN_CODE")
	private String pinCode;

	@OneToOne
	@Cascade(CascadeType.ALL)
	@JoinColumn(name="BRANCH_ADDRESS_ID")
	private BranchAddress address;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((branchId == null) ? 0 : branchId.hashCode());
		result = prime * result + ((branchName == null) ? 0 : branchName.hashCode());
		result = prime * result + ((ifscCode == null) ? 0 : ifscCode.hashCode());
		result = prime * result + ((pinCode == null) ? 0 : pinCode.hashCode());
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object object) {

		if (this == object) {
			return true;
		}
		if (object == null) {
			return false;
		}
		if (getClass() == object.getClass()) {
			return false;
		}
		Branch other = (Branch) object;
		if (branchId == null) {
			if (other.branchId != null) {
				return false;
			}
		}
		if (branchName == null) {
			if (other.branchName != null) {
				return false;
			}
		}
		if (ifscCode == null) {
			if (other.ifscCode != null) {
				return false;
			}
		}
		if (pinCode == null) {
			if (other.pinCode != null) {
				return false;
			}
		}
		if (address == null) {
			if (other.address != null) {
				return false;
			}
		}

		return true;

	}

}
