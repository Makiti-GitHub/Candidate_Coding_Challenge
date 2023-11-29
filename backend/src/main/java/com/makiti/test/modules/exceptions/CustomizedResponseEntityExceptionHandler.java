package com.makiti.test.modules.exceptions;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Date;

import org.hibernate.exception.DataException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler
	extends ResponseEntityExceptionHandler {

	@ExceptionHandler(AppException.class)
	public final ResponseEntity<ExceptionResponse> handleNotFoundException(
		AppException ex,
		WebRequest request
	) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(
			new Date(),
			ex.getMessage(),
			request.getDescription(false),
			HttpStatus.BAD_REQUEST.getReasonPhrase()
		);
		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(BadRequestException.class)
	public final ResponseEntity<ExceptionResponse> handleNotFoundException(
		BadRequestException ex,
		WebRequest request
	) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(
			new Date(),
			ex.getMessage(),
			request.getDescription(false),
			HttpStatus.BAD_REQUEST.getReasonPhrase()
		);
		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ResourceAlreadyExistException.class)
	public final ResponseEntity<ExceptionResponse> handleNotFoundException(
		ResourceAlreadyExistException ex,
		WebRequest request
	) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(
			new Date(),
			ex.getMessage(),
			request.getDescription(false),
			HttpStatus.NOT_ACCEPTABLE.getReasonPhrase()
		);
		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	public final ResponseEntity<ExceptionResponse> handleNotFoundException(
		ResourceNotFoundException ex,
		WebRequest request
	) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(
			new Date(),
			ex.getMessage(),
			request.getDescription(false),
			HttpStatus.NOT_FOUND.getReasonPhrase()
		);
		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	public final ResponseEntity<ExceptionResponse> handleSqlIntegrityViolation(
		SQLIntegrityConstraintViolationException ex,
		WebRequest request
	) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(
			new Date(),
			ex.getMessage(),
			request.getDescription(false),
			HttpStatus.NOT_FOUND.getReasonPhrase()
		);
		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public final ResponseEntity<ExceptionResponse> handleSqlIntegrityViolation(
		DataIntegrityViolationException ex,
		WebRequest request
	) {
		DataException dataException = (DataException) ex.getCause();
		ExceptionResponse exceptionResponse = new ExceptionResponse(
			new Date(),
			String.format("DB ERROR, SQL_STATE: %s, ERROR_CODE: %s ",
				dataException.getSQLException().getSQLState(),
				dataException.getSQLException().getErrorCode() + ""
			),
			request.getDescription(false),
			HttpStatus.NOT_FOUND.getReasonPhrase()
		);
		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ExceptionResponse> handleGlobalException(
		Exception ex,
		WebRequest request
	) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(
			new Date(),
			ex.getMessage(),
			request.getDescription(false),
			HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()
		);
		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
}
