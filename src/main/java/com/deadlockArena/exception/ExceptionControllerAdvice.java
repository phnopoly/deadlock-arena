package com.deadlockArena.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.deadlockArena.DeadlockArenaBackEnd;

@ControllerAdvice
public class ExceptionControllerAdvice {

	@ExceptionHandler(DeadlockException.class)
	@ResponseBody
	ResponseEntity<ErrorResponse> onDeadlockException(final DeadlockException dle) {
		final ErrorResponse error = new ErrorResponse(dle.getMsg());
		String details = dle.getCause() != null ? dle.getCause().getMessage() : dle.getMessage();
		error.setErrorDetails(details);
		DeadlockArenaBackEnd.LOG.error("dle msg: {}", dle.getMsg());
		DeadlockArenaBackEnd.LOG.error("dle details: {}", details);
		return new ResponseEntity<>(error, dle.getHttpStatus());
	}

}
