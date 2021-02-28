package com.ribeiro.assembleiaapi.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class ApiException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4133214265195809213L;


	private final HttpStatus httpStatus;

	public ApiException(final String message, final HttpStatus httpStatus) {
		super(message);
		this.httpStatus = httpStatus;
	}
	
	
}
