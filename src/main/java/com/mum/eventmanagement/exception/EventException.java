package com.mum.eventmanagement.exception;

public class EventException extends RuntimeException{

	public EventException(String message) {
		super(message);
	}

	private static final long serialVersionUID = 1L;

	public static EventException instanceOf(String message){
		return new EventException(message);
	}
}
