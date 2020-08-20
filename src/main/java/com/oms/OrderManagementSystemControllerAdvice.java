package com.oms;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.oms.exception.OrderManagementBusinessException;
import com.oms.exception.OrderNotFoundException;
import com.oms.model.ErrorMessage;

@ControllerAdvice
public class OrderManagementSystemControllerAdvice extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { OrderNotFoundException.class })
	protected ResponseEntity<Object> handleOrderConflict(OrderNotFoundException ex) {
		return new ResponseEntity<Object>(new ErrorMessage(HttpStatus.NOT_FOUND.value(), ex.getMessage()), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = { OrderManagementBusinessException.class })
	protected ResponseEntity<Object> handleOrderConflict(OrderManagementBusinessException ex) {
		return new ResponseEntity<Object>(new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(value = { Exception.class })
	protected ResponseEntity<Object> handleExceptionConflict(Exception ex) {
		return new ResponseEntity<Object>(new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return new ResponseEntity<Object>(errors, status);
	}
		
}