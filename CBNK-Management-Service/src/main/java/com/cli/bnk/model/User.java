package com.cli.bnk.model;

import java.io.Serializable;

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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "USER_INFO")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "USER_ID")
	private long userId;

	@Column(name = "USERNAME")
	private transient String userName;

	@Column(name = "PASSWORD")
	private transient String password;

	@Column(name = "SECURITY_QUEUSTION")
	private String securityQuestion;

	@Column(name = "SECURITY_ANSWER")
	private String secuirtyAnswer;

	private char role;

	@OneToOne
	@Cascade(CascadeType.ALL)
	@JoinColumn(name = "USER_PERSON_INFO")
	private PersonInfo personInfo;
}
