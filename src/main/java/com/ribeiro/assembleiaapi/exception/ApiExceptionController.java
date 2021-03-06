package com.ribeiro.assembleiaapi.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class ApiExceptionController extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final HttpStatus httpStatus;

	public ApiExceptionController(final String message, HttpStatus httpStatus) {
		super(message);
		this.httpStatus = httpStatus;
	}
	
	
}
