package org.company.example.exception.meta;

import java.io.InputStream;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.cxf.jaxrs.client.ResponseExceptionMapper;
import org.company.example.exception.DuplicateUserException;
import org.company.example.exception.UserNotFoundException;
import org.company.example.exception.ValidationException;


public class UserResponseExceptionMapper implements ResponseExceptionMapper<UserBaseException> {

	public UserBaseException fromResponse(Response r) {
		if (r.getStatus() == Status.NOT_FOUND.getStatusCode()) {
			return new UserNotFoundException();
		} 

		if (r.getStatus() == Status.CONFLICT.getStatusCode()) {
			return new DuplicateUserException();
		}

		if (r.getStatus() == Status.BAD_REQUEST.getStatusCode()) {

			JAXBContext context;
			try {
				context = JAXBContext.newInstance(ExceptionData.class);
				Unmarshaller um = context.createUnmarshaller();
				ExceptionData ed = (ExceptionData) um.unmarshal((InputStream)r.getEntity());
				return new ValidationException(ed.getData());
			} catch (JAXBException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
			
			
		}
		
		return null;
	}
	
}
