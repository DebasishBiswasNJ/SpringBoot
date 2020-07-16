package com.deb.rest.webservices.restfulwebservices.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.deb.rest.webservices.restfulwebservices.user.UserNotFoundException;

@ControllerAdvice
@RestController
public class CustomisedResponseEntityExceptionhandler extends ResponseEntityExceptionHandler{
	
	/**
	 * The Response structure we'll receive, try to get unknown Resources
	 * GET : http://localhost:8080/users/5454
	 * 
	 *  {
     * 		"timestamp": "2020-05-16T13:16:35.215+00:00",
     * 		"message": "id- 5454",
     * 		"details": "uri=/users/5454"
	 *	}
	 * 
	 */
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
		
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex, WebRequest request) {
		
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
	}
	
	/**
	 * Override the default implementation of ResponseEntityExceptionHandler class
	 * 
	 * This Exception Handler is for Validation purpose, wherever we used @Valid, 
	 * If the Validation Fails, this Handler will be triggered
	 * e.g. createAdvUser() method of UserResource Class
	 */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		/**
		 * getBindingResult() will bind the details --> with which Request param, what went wrong? how many error?
		 * 
		 * Default : ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), ex.getBindingResult().toString());
		 * 
		 * ex.getMessage() => instead all unnecessary details in message, we'll only send "Validation Failed" in the "message"
		 */
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), "Validation Failed", ex.getBindingResult().toString());
		
		return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
}
