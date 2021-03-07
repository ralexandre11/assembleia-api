package com.ribeiro.assembleiaapi.exception;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ResponseErrorController {

	private final String message;
	
	private final int httpStatus;
	
	private final Long timeStamp;
	
}