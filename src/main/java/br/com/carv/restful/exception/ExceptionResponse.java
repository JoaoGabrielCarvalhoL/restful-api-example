package br.com.carv.restful.exception;

import java.io.Serializable;
import java.time.LocalDate;

public class ExceptionResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private LocalDate timestamp;
	private String message;
	private String details;

	public ExceptionResponse(LocalDate timestamp, String message, String details) {
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
	}

	public ExceptionResponse() {
	}

	public LocalDate getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDate timestamp) {
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

}
