package org.company.example.exception;

import javax.ws.rs.core.Response.Status;

import org.company.example.exception.meta.UserBaseException;


public class UserNotFoundException extends UserBaseException  {

	private static final long serialVersionUID = 1154886234595592271L;
	
	public UserNotFoundException() {
		
		super(Status.NOT_FOUND, "User not found");
		
	}
	

}
