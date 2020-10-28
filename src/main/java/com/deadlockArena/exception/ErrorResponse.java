package com.deadlockArena.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
