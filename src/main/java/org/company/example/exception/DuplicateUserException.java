package org.company.example.exception;

import javax.ws.rs.core.Response.Status;

import org.company.example.exception.meta.UserBaseException;


/**
 * Indicates if a user is already created
 * @author pszanto
 */
public class DuplicateUserException extends UserBaseException {

	private static final long serialVersionUID = -8212991366777389573L;

	public DuplicateUserException() {

		super(Status.CONFLICT, "user is already stored");
		
	}
	
}
