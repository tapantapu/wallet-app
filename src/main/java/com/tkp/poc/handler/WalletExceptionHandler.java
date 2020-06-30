package com.tkp.poc.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class WalletExceptionHandler {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(WalletExceptionHandler.class);

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public com.tkp.poc.model.Error validationError(MethodArgumentNotValidException argumentNotValidException) {
		LOGGER.error("Validation error",argumentNotValidException);
		//TODO Take the field name and set it to description
		return new com.tkp.poc.model.Error("APP_400",argumentNotValidException.getMessage(), argumentNotValidException.getParameter().toString());
	}

	@ExceptionHandler(RuntimeException.class)
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	public com.tkp.poc.model.Error handleRuntimeException(RuntimeException exception) {
		LOGGER.error("Runtime error",exception);
		return new com.tkp.poc.model.Error("APP_500",exception.getMessage(), exception.toString());
	}
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	public com.tkp.poc.model.Error handleException(Exception exception) {
		LOGGER.error("Exception",exception);
		return new com.tkp.poc.model.Error("APP_501",exception.getMessage(), exception.toString());
	}
}
