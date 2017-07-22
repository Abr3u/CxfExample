package org.company.example.exception;

import java.util.Map;

import javax.ws.rs.core.Response.Status;

import org.company.example.exception.meta.UserBaseException;


public class ValidationException extends UserBaseException {

	private static final long serialVersionUID = -6353144184095941148L;

	public ValidationException(Map<String, String> data) {
		super(Status.BAD_REQUEST, "Validation failed", data);
	}

}
