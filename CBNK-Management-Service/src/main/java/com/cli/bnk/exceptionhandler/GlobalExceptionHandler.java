package com.cli.bnk.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ElementNotFoundException.class)
	public ResponseEntity<?> elementNotFoundExceptionHandeler() {
		return new ResponseEntity<String>("Element Is Not Found", HttpStatus.BAD_REQUEST);
	}
}
