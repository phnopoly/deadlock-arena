package com.deadlockarena.backend.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Response object returned when there are any types of errors while processing
 * REST-API method
 *
 * @author zsaordenio
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

	private String errorMsg;
	private String errorDetails;

	public ErrorResponse(final String errorMsg) {
		super();
		this.errorMsg = errorMsg;
	}
}
