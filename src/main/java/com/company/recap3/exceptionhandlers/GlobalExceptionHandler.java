package com.company.recap3.exceptionhandlers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.mapping.PropertyReferenceException;
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
import com.company.recap3.models.ErrorResponse;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleEntityNotFound(EntityNotFoundException e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		Map<String, String> error = new HashMap<>();
		error.put("code", e.getErrorCode());
		error.put("message", e.getErrorMessage());
		ErrorResponse errorResponse = ErrorResponse.getErrorResponse(status.value(), "Not Found", request, error);

		return new ResponseEntity<>(errorResponse, status);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ErrorResponse> handleIllegalArgument(IllegalArgumentException e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		ErrorResponse errorResponse = ErrorResponse.getErrorResponse(status.value(), "Bad Request", request,
				e.getMessage());

		return new ResponseEntity<>(errorResponse, status);
	}

	@ExceptionHandler(PropertyReferenceException.class)
	public ResponseEntity<ErrorResponse> handlePropertyReference(PropertyReferenceException e,
			HttpServletRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		ErrorResponse errorResponse = ErrorResponse.getErrorResponse(status.value(), "Bad Request", request,
				e.getMessage());

		return new ResponseEntity<>(errorResponse, status);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException e,
			HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;

		// Cast WebRequest to ServletWebRequest to get HttpServletRequest
		HttpServletRequest servletRequest = ((ServletWebRequest) request).getRequest();

		ErrorResponse errorResponse = ErrorResponse.getErrorResponse(status.value(), "Bad Request", servletRequest,
				e.getMessage());

		return new ResponseEntity<>(errorResponse, status);

	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e,
			HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		Map<String, String> error = new HashMap<>();
		e.getBindingResult().getAllErrors().forEach((_error) -> {
			String key = ((FieldError) _error).getField();
			String value = _error.getDefaultMessage();
			error.put(key, value);
		});

		// Cast WebRequest to ServletWebRequest to get HttpServletRequest
		HttpServletRequest servletRequest = ((ServletWebRequest) request).getRequest();

		ErrorResponse errorResponse = ErrorResponse.getErrorResponse(status.value(), "Bad Request", servletRequest,
				error);

		return new ResponseEntity<>(errorResponse, status);
	}

}
