package com.ribeiro.assembleiaapi.exception;

import lombok.Getter;

@Getter
public class ApiException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ApiException(final String message) {
		super(message);
	}
	
	
}
