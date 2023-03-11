package com.cli.bnk.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Columns;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "MANAGER")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Manager {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="MANAGER_ID")
	private Long managerId;

	@Column(name="BRANCH_ID")
	private Long branchId;

	@OneToOne
	@Cascade(CascadeType.ALL)
	@JoinColumn(name="MANAGER_USER_ID")
	private User user;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((managerId == null) ? 0 : managerId.hashCode());
		result = prime * result + ((branchId == null) ? 0 : branchId.hashCode());
		result = prime * result + ((user == null) ? 0 : branchId.hashCode());
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
		Manager other = (Manager) object;
		if (managerId == null) {
			if (other.managerId != null) {
				return false;
			}
		} else if (!managerId.equals(other.managerId)) {
			return false;
		}
		if (branchId == null) {
			if (other.branchId != null) {
				return false;
			}
		} else if (!branchId.equals(other.branchId)) {
			return false;
		}
		if (user == null) {
			if (other.user != null) {
				return false;
			}
		} else if (!user.equals(other.user)) {
			return false;
		}
		return true;

	}

}
