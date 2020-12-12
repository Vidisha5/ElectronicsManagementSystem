package com.example.demo.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerDemo extends ResponseEntityExceptionHandler{

	
	@ExceptionHandler(RecordNotFoundException.class)
	public ResponseEntity<Object> exception(RecordNotFoundException ex,WebRequest request) {
		ErrorDetails details=new ErrorDetails(LocalDateTime.now(),ex.getMessage(),request.getDescription(false));
		return new ResponseEntity<Object>(details, HttpStatus.NOT_FOUND);
	}
}
