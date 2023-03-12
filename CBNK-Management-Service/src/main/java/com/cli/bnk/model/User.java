package com.cli.bnk.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
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
@Table(name="USER_INFO")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class User {

	@Id
	@GeneratedValue
	@Column(name="USER_ID")
	private long userId;
	
	@Column(name="USERNAME")
	private String userName;
	
	@Column(name="PASSWORD")
	private String password;
	
	@Column(name="SECURITY_QUEUSTION")
	private String securityQuestion;
	
	@Column(name="SECURITY_ANSWER")
	private String secuirtyAnswer;
	
	private char role;
	
	@OneToOne
	@Cascade(CascadeType.ALL)
	private PersonInfo personInfo;
}
