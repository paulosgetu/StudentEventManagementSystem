package com.mum.eventmanagement.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ExceptionController {

	@ExceptionHandler(EventException.class)
	public String eeventExceptionHandler() {
		return "exceptionPage";
	}

}
