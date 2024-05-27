package com.company.recap3.exceptionhandlers;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.company.recap3.exceptions.EntityNotFoundException;
import com.company.recap3.exceptions.RequestBodyPropertyException;
import com.company.recap3.models.ErrorResponse;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleEntityNotFound(EntityNotFoundException e, HttpServletRequest request) {
		Map<String, String> error = new HashMap<>();
		error.put("code", e.getErrorCode());
		error.put("message", e.getErrorMessage());

		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setTimestamp(LocalDateTime.now());
		errorResponse.setMethod(request.getMethod());
		errorResponse.setTitle("Not Found");
		errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
		errorResponse.setPath(request.getRequestURI());
		errorResponse.setError(error);

		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(RequestBodyPropertyException.class)
	public ResponseEntity<ErrorResponse> handleRequestBodyProperty(RequestBodyPropertyException e,
			HttpServletRequest request) {
		Map<String, String> error = new HashMap<>();
		error.put("code", e.getErrorCode());
		error.put("message", e.getErrorMessage());

		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setTimestamp(LocalDateTime.now());
		errorResponse.setMethod(request.getMethod());
		errorResponse.setTitle("Bad Request");
		errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
		errorResponse.setPath(request.getRequestURI());
		errorResponse.setError(error);

		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException e,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		// Cast WebRequest to ServletWebRequest to get HttpServletRequest
		HttpServletRequest servletRequest = ((ServletWebRequest) request).getRequest();

		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setTimestamp(LocalDateTime.now());
		errorResponse.setMethod(servletRequest.getMethod());
		errorResponse.setTitle("Bad Request");
		errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
		errorResponse.setPath(servletRequest.getRequestURI());
		errorResponse.setError(e.getMessage());

		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		Map<String, String> error = new HashMap<>();
		e.getBindingResult().getAllErrors().forEach((_error) -> {
			String key = ((FieldError) _error).getField();
			String value = _error.getDefaultMessage();
			error.put(key, value);
		});

		// Cast WebRequest to ServletWebRequest to get HttpServletRequest
		HttpServletRequest servletRequest = ((ServletWebRequest) request).getRequest();

		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setTimestamp(LocalDateTime.now());
		errorResponse.setMethod(servletRequest.getMethod());
		errorResponse.setTitle("Bad Request");
		errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
		errorResponse.setPath(servletRequest.getRequestURI());
		errorResponse.setError(error);

		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

}
