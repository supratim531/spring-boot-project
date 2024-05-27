package com.company.recap3.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

@SuppressWarnings("serial")
public class EntityNotFoundException extends RuntimeException {

	private String errorCode;
	private String errorMessage;

}
