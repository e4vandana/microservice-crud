package io.github.vandana.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UserNotFoundException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8096752469131146597L;
	
	public UserNotFoundException(String message){
    	super(message);
    }

}
