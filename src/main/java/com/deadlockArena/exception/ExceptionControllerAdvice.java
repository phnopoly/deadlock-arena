package com.deadlockArena.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.deadlockArena.DeadlockArenaApplication;

@ControllerAdvice
public class ExceptionControllerAdvice {

	@ExceptionHandler(DeadlockException.class)
	@ResponseBody
	ResponseEntity<ErrorResponse> onDeadlockException(final DeadlockException dle) {
		final ErrorResponse error = new ErrorResponse(dle.getMsg());
		String details = dle.getCause() != null ? dle.getCause().getMessage() : dle.getMessage();
		error.setErrorDetails(details);
		DeadlockArenaApplication.LOG.error("dle msg: {}", dle.getMsg());
		DeadlockArenaApplication.LOG.error("dle details: {}", details);
		return new ResponseEntity<>(error, dle.getHttpStatus());
	}

}
