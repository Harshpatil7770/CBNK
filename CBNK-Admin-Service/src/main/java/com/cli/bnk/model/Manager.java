package com.cli.bnk.model;

import java.util.ArrayList;
import java.util.List;

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
	private long managerId;

	@OneToOne
	@Cascade(CascadeType.ALL)
	private Branch branch;

	@OneToOne
	@Cascade(CascadeType.ALL)
	private User user;

}
