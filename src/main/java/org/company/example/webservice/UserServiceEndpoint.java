package org.company.example.webservice;

import org.company.example.data.UserData;
import org.company.example.exception.DuplicateUserException;
import org.company.example.exception.UserNotFoundException;
import org.company.example.exception.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.Validator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service("userService")
public class UserServiceEndpoint implements UserService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	private Map<String, UserData> users = new HashMap<String, UserData>();

	@Autowired
	private Validator validator;

	public UserData readUserByNif(String nif) throws UserNotFoundException {

		
		for(Map.Entry<String, UserData> entry : users.entrySet()) {
			System.out.println("KEY "+entry.getKey());
			System.out.println("DATA "+entry.getValue());
		}
		
		System.out.println("SEARCHING for nif == "+nif);
		
		UserData userData = users.get(nif);

		if (userData == null) {
			throw new UserNotFoundException();
		}

		return userData;

	}
	
	public UserData createUser(UserData userData) throws DuplicateUserException {

		if (users.get(userData.getNif()) != null) {
			throw new DuplicateUserException();
		}

		BeanPropertyBindingResult br = new BeanPropertyBindingResult(userData, "userData");
		validator.validate(userData, br);
		if (br.hasErrors()) {
			Map<String, String> errors = new HashMap<String, String>();
			for (FieldError e : br.getFieldErrors()) {
				logger.debug(e.getDefaultMessage());
				errors.put(e.getField(), e.getDefaultMessage());
			}

			throw new ValidationException(errors);
		}
		
		storeUser(userData);

		return userData;
	}

	private void storeUser(UserData userData) {

		users.put(userData.getNif(), userData);

	}


	public Collection<UserData> readAllUsers() {
		return users.values();
	}


	public void deleteUser(String nif) throws UserNotFoundException {
		UserData userData = users.get(nif);

		if (userData == null) {
			throw new UserNotFoundException();
		}

		users.remove(nif);

	}

}