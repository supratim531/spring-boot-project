package com.company.recap3.models;

import java.time.LocalDateTime;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

	private LocalDateTime timestamp;
	private String method;
	private String title;
	private Integer status;
	private String path;
	private Object error;

	public static ErrorResponse getErrorResponse(Integer status, String title, HttpServletRequest request,
			Object error) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setTimestamp(LocalDateTime.now());
		errorResponse.setMethod(request.getMethod());
		errorResponse.setTitle(title);
		errorResponse.setStatus(status);
		errorResponse.setPath(request.getRequestURI());
		errorResponse.setError(error);

		return errorResponse;
	}

}
