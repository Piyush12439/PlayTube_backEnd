package com.play.tube.exception;

public class LoginException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LoginException() {
		
	}
	
	public LoginException(String message) {
		super(message);
	}
	
	public LoginException(String message,Throwable t ) {
		super(message,t);
	}

}
