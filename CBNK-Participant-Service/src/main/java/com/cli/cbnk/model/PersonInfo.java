package com.cli.cbnk.model;

import java.time.LocalDate;

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
@Table(name = "PERSONAL_INFO")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class PersonInfo {

	@Id
	@GeneratedValue
	@Column(name = "PERSON_ID")
	private long personId;

	@Column(name = "PERSON_NAME")
	private String personName;

	@Column(name = "GENDER")
	private char gender;

	@Column(name = "DOB")
	private LocalDate dob;

	@Column(name = "EMAIL_ID")
	private String emailId;

	@Column(name = "MOBILE_NO")
	private long mobileNo;

	@OneToOne
	@Cascade(CascadeType.ALL)
	@JoinColumn(name = "PERSON_ADDRESS_ID")
	private PersonAddress address;

//	@OneToOne(cascade = javax.persistence.CascadeType.ALL)
//	@JoinColumn(name = "person_user_id")
//	private User user;

}
