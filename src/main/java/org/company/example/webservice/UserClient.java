package org.company.example.webservice;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.apache.cxf.jaxrs.client.ClientConfiguration;
import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.company.example.data.UserData;
import org.company.example.exception.DuplicateUserException;
import org.company.example.exception.UserNotFoundException;
import org.company.example.exception.meta.UserResponseExceptionMapper;

public class UserClient implements UserService {

	private UserService userService;

	public static enum CLIENT_TYPE {
		REST("REST"), SOAP("SOAP");

		private String value;

		private CLIENT_TYPE(String value) {
			this.value = value;
		}

		public static CLIENT_TYPE fromString(String value) {

			if (REST.value.equalsIgnoreCase(value)) {
				return REST;
			}

			if (SOAP.value.equalsIgnoreCase(value)) {
				return SOAP;
			}

			return null;
		}
	};

	public UserClient(String applicationURI, CLIENT_TYPE clientType) {

		if (clientType == CLIENT_TYPE.REST) {
			List<Object> providers = new LinkedList<Object>();
			providers.add(new UserResponseExceptionMapper());
			userService = JAXRSClientFactory.create(applicationURI + "/cxf/rest/", UserService.class, providers,
					true);
			ClientConfiguration cfgProxy = WebClient.getConfig(userService);
			cfgProxy.getHttpConduit().getAuthorization().setPassword("restuser");
			cfgProxy.getHttpConduit().getAuthorization().setUserName("restuser");
		} else {
			JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
			factory.setServiceClass(UserService.class);
			factory.setAddress(applicationURI + "/cxf/soap/");
			factory.setUsername("restuser");
			factory.setPassword("restuser");
			userService = (UserService) factory.create();
		}

	}

	public UserData createUser(UserData userData) throws DuplicateUserException {
		return userService.createUser(userData);
	}

	public UserData readUserByNif(String nif) throws UserNotFoundException {
		return userService.readUserByNif(nif);
	}

	public Collection<UserData> readAllUsers() {
		return userService.readAllUsers();
	}

	public void deleteUser(String nif) throws UserNotFoundException {

		userService.deleteUser(nif);

	}


	
}