package com.cli.cbnk.dto;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class CustomerCustomDTO {

	private Long accountNumber;

	private String userName;

	private String password;
}
