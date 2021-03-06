package com.ribeiro.assembleiaapi.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ribeiro.assembleiaapi.exception.ResponseErrorController.ResponseErrorControllerBuilder;

@ControllerAdvice
public class ApiExceptionHandler {

	@ExceptionHandler(ApiExceptionController.class)
	public ResponseEntity<ResponseErrorController> handlerException(ApiExceptionController apiExcepiton) {
		ResponseErrorControllerBuilder responseError = ResponseErrorController.builder();
		responseError.message(apiExcepiton.getMessage());
		responseError.httpStatus(apiExcepiton.getHttpStatus().value());
		responseError.timeStamp(System.currentTimeMillis());
		return ResponseEntity.status(apiExcepiton.getHttpStatus()).body(error.build());
	}
	
}