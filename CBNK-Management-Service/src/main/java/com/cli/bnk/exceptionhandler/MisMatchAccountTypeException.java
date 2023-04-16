package com.cli.bnk.exceptionhandler;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MisMatchAccountTypeException extends RuntimeException{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String errorDescription;
	
	private HttpStatus httpStatus;

}
