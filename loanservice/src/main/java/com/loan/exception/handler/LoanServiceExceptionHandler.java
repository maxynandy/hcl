package com.loan.exception.handler;

import org.apache.catalina.connector.ClientAbortException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import com.loan.constants.ErrorConstants;
import com.loan.response.ApiError;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
@Component
public class LoanServiceExceptionHandler {
	
   @ExceptionHandler(value = {HttpMessageNotReadableException.class})
   @ResponseStatus(value= HttpStatus.BAD_REQUEST)
	public ResponseEntity<ApiError> handle(HttpMessageConversionException exception,WebRequest request){
	   String specificCause = exception.getMostSpecificCause().getLocalizedMessage();
	   String errorMessage = specificCause !=null ? specificCause : exception.getLocalizedMessage(); 
	   return buildResponse(HttpStatus.BAD_REQUEST, ErrorConstants.ERR_PREFIX_MALFORMED_REQUEST+errorMessage, getRequestURI(request));	  
	}
   
   @ExceptionHandler(value = {ConstraintViolationException.class})
   @ResponseStatus( value = HttpStatus.BAD_REQUEST)
   public ResponseEntity<ApiError> handle(ConstraintViolationException exception,WebRequest request){
	 String errorMessage=exception.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.joining(","));
	 return buildResponse(HttpStatus.BAD_REQUEST, ErrorConstants.ERR_PREFIX_INVALID_REQUEST +errorMessage, getRequestURI(request));
   }
   
   @ExceptionHandler(value = {IOException.class})
   @ResponseStatus( value = HttpStatus.INTERNAL_SERVER_ERROR)
   public ResponseEntity<ApiError> handle(IOException exception,WebRequest request){
	   if(exception instanceof ClientAbortException) {
		  //can print logger
		   return null;
	   }
	 return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, ErrorConstants.ERR_APP_EXCEPTION , getRequestURI(request));
   }
   
   @ResponseStatus(HttpStatus.BAD_REQUEST)
   @ExceptionHandler(MethodArgumentNotValidException.class)
   public Map<String, String> handleValidationExceptions(
     MethodArgumentNotValidException ex) {
       Map<String, String> errors = new HashMap<>();
       ex.getBindingResult().getAllErrors().forEach((error) -> {
           String fieldName = ((FieldError) error).getField();
           String errorMessage = error.getDefaultMessage();
           errors.put(fieldName, errorMessage);
       });
       return errors;
   }
   
   
   //Fall Back handler for all other Exception
   @ExceptionHandler(value = {Exception.class})
   @ResponseStatus( value = HttpStatus.INTERNAL_SERVER_ERROR)
   public ResponseEntity<ApiError> handle(Exception exception,WebRequest request){	  
	 return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, ErrorConstants.ERR_APP_EXCEPTION , getRequestURI(request));
   }
   
   private ResponseEntity<ApiError> buildResponse(HttpStatus httpStatus, String errorMessage,String requestURI) {
	   ApiError response = new ApiError(httpStatus, errorMessage, requestURI);
	   return new ResponseEntity<>(response,httpStatus);
	   
   }
   
   private String getRequestURI(WebRequest request) {
	   return ((ServletWebRequest) request).getRequest().getRequestURI();
   }
   
}
