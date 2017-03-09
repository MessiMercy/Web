package com.web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "---404 not found---")
public class PostNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2619643070611940996L;

}
