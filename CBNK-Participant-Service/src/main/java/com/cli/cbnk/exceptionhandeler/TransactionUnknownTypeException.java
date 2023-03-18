package com.cli.cbnk.exceptionhandeler;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionUnknownTypeException extends RuntimeException {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	private String description;

	private HttpStatus errorCode;
}
