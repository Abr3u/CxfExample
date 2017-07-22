package org.company.example.web;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.company.example.data.UserData;
import org.company.example.exception.DuplicateUserException;
import org.company.example.exception.UserNotFoundException;
import org.company.example.exception.ValidationException;
import org.company.example.webservice.UserClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;


@Controller
@RequestMapping("/")
@SessionAttributes("userData")
public class UserClientController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private String applicationURI;

	private UserClient userClientSOAP;
	private UserClient userClientREST;
	
	private UserClient getUserClient(HttpServletRequest request, UserClient.CLIENT_TYPE clientType) {
		
		if (clientType.equals(UserClient.CLIENT_TYPE.SOAP)) {
			if (userClientSOAP == null) {
				userClientSOAP = new UserClient(getApplicationURI(request), UserClient.CLIENT_TYPE.SOAP);
			}
			
			return userClientSOAP;
		}
		
		if (clientType.equals(UserClient.CLIENT_TYPE.REST)) {
			if (userClientREST == null) {
				userClientREST = new UserClient(getApplicationURI(request), UserClient.CLIENT_TYPE.REST);
			}
			
			return userClientREST;
		}
		
		
		return null;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String showForm(Model model) {

		model.addAttribute("userData", new UserData());
		
		return "userClient";
		
	}
	
	/**
	 * returns the URI of the application 
	 * @param req
	 * @return
	 */
	private String getApplicationURI(HttpServletRequest req) {
		if (applicationURI == null) {
			String port;
			
		    if ("http".equalsIgnoreCase(req.getScheme()) && req.getServerPort() != 80 ||
		            "https".equalsIgnoreCase(req.getScheme()) && req.getServerPort() != 443 ) {
		        port = ":" + req.getServerPort();
		    } else {
		        port = "";
		    }
		    
	        applicationURI = req.getScheme() + "://" + req.getServerName() +
	                port + req.getContextPath();
	        
	        logger.debug("Application URL was set to " + applicationURI );
		}
		
		return applicationURI;
	}	

	@RequestMapping(value="/read", method = RequestMethod.GET)
	public String readUserByNif(@RequestParam String protocol, @RequestParam("nif") String nif, Model model, HttpServletRequest request) throws UserNotFoundException {
		
		logger.debug("------------------------ user nif is " + nif);
		
		UserData userDataResp = getUserClient(request, UserClient.CLIENT_TYPE.fromString(protocol)).readUserByNif(nif);

		logger.debug("user result is " +  userDataResp);

		List<UserData> userDataList = new LinkedList<UserData>();
		userDataList.add(userDataResp);
		model.addAttribute("userDataList", userDataList);
		
		return "userClient";
		
	}
	
	@RequestMapping(value="/create", method = RequestMethod.POST)
	public String createUser(@RequestParam String protocol, UserData userData, Model model, HttpServletRequest request, BindingResult bindingResult) throws DuplicateUserException {

		logger.debug("user data is " + userData);
		
		try {
			UserData userDataResp = getUserClient(request, UserClient.CLIENT_TYPE.fromString(protocol)).createUser(userData);
			List<UserData> userDataList = new LinkedList<UserData>();
			userDataList.add(userDataResp);
			model.addAttribute("userDataList", userDataList);
			logger.debug("user result is " +  userDataResp);
		} catch (ValidationException e) {
			for (String field : e.getExceptionData().getData().keySet()) {
				bindingResult.rejectValue(field, null, e.getExceptionData().getData().get(field));
			}
		}
		
		return "userClient";
		
	}

	@RequestMapping(value="/readall", method = RequestMethod.GET)
	public String readAllUsers(@RequestParam String protocol, Model model, HttpServletRequest request) throws DuplicateUserException {
		Collection<UserData> userDataList = getUserClient(request, UserClient.CLIENT_TYPE.fromString(protocol)).readAllUsers();

		model.addAttribute("userDataList", userDataList);
		
		return "userClient";
	}

	@RequestMapping(value="/delete", method = RequestMethod.GET)
	public String deleteUser(@RequestParam String protocol, @RequestParam String nif, Model model, HttpServletRequest request) throws DuplicateUserException, UserNotFoundException {
		getUserClient(request, UserClient.CLIENT_TYPE.fromString(protocol)).deleteUser(nif);

		return "userClient";
	}	
	
}
