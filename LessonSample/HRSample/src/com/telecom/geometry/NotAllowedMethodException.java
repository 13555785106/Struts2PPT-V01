package com.telecom.geometry;

public class NotAllowedMethodException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	public NotAllowedMethodException() {}
	public NotAllowedMethodException(String msg) {
		super(msg);
	}
}
