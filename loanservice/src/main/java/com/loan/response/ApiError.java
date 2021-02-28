package com.loan.response;

import java.time.ZonedDateTime;


import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.ZonedDateTimeSerializer;


public class ApiError {
	
	
	public ZonedDateTime getTimestamp() {
		return timestamp;
	}

	public int getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

	public String getPath() {
		return path;
	}

	public String getError() {
		return error;
	}

	@JsonSerialize (using= ZonedDateTimeSerializer.class)
	private ZonedDateTime timestamp;
	
	private int status;
	
	private String message;
	
	private String path;
	
	private String error;
	
	public ApiError(HttpStatus httpStatus,String message,String path) {
		this.timestamp = ZonedDateTime.now();
		this.status = httpStatus.value();
		this.error = httpStatus.getReasonPhrase();
		this.message = message;
		this.path = path;		
	}

}
