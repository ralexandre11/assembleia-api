package com.ribeiro.assembleiaapi.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ribeiro.assembleiaapi.exception.ErrorResponse.ErrorResponseBuilder;

@ControllerAdvice
public class ResourceHandler {

	@ExceptionHandler(ApiException.class)
	public ResponseEntity<ErrorResponse> handlerException(ApiException apiExcepiton) {
		ErrorResponseBuilder error = ErrorResponse.builder();
		error.httpStatus(apiExcepiton.getHttpStatus().value());
		error.message(apiExcepiton.getMessage());
		error.timeStamp(System.currentTimeMillis());
		return ResponseEntity.status(apiExcepiton.getHttpStatus()).body(error.build());
	}
	
}