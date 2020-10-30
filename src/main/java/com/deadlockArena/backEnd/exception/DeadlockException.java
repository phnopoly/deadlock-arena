package com.deadlockArena.backEnd.exception;

import org.springframework.http.HttpStatus;

import com.deadlockArena.backEnd.service.Microservice;

import lombok.Getter;

/**
 * Generic exception thrown in {@link Microservice}.
 *
 * @author zsaordenio
 *
 */
@Getter
public class DeadlockException extends RuntimeException {

	private static final long serialVersionUID = -8982162908219709107L;

	private final String msg;
	private final HttpStatus httpStatus;

	public DeadlockException() {
		this("General Exception", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	public DeadlockException(final String msg, final HttpStatus status) {
		super(msg);
		this.msg = msg;
		this.httpStatus = status;
	}

	public DeadlockException(final String msg, final HttpStatus status, final Throwable t) {
		super(msg, t);
		this.msg = msg;
		this.httpStatus = status;
	}

}
