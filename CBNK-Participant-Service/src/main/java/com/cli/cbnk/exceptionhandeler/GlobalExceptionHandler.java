package com.cli.cbnk.exceptionhandeler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(MisMatchAccountType.class)
	public ResponseEntity<String> misMatchAccountTypeExceptionHandeler() {
		return new ResponseEntity<String>("Unable to identied the mentioed account type", HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(TransactionUnknownTypeException.class)
	public ResponseEntity<String> transactionUnknownTypeExceptionHandeler() {
		return new ResponseEntity<String>("Transaction Type is incorrect", HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<String> illegalArgumentExpectionHandeler(){
		return new ResponseEntity<String>("Please check the entered details again",HttpStatus.BAD_REQUEST);
	}
}
