package org.company.example.webservice;

import java.util.Collection;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import org.apache.cxf.jaxrs.model.wadl.Description;
import org.apache.cxf.jaxrs.model.wadl.Descriptions;
import org.apache.cxf.jaxrs.model.wadl.DocTarget;
import org.company.example.data.UserData;
import org.company.example.exception.DuplicateUserException;
import org.company.example.exception.UserNotFoundException;

@Path("/user/")
@WebService
public interface UserService {

	@WebMethod
	@GET
	@Path("{nif}")
	@Descriptions({
		@Description(value = "returns a user data ", target = DocTarget.METHOD),
		@Description(value = "the user data", target = DocTarget.RETURN)
	})
    public UserData readUserByNif(@Description(value = "the user's nif") @PathParam("nif") @NotNull @Size(max=9, min=9) String nif) throws UserNotFoundException;
	
	@WebMethod
	@GET
	@Path("*")
	@Descriptions({
		@Description(value = "returns all users", target = DocTarget.METHOD),
		@Description(value = "the users data", target = DocTarget.RETURN)
	})
    public Collection<UserData> readAllUsers();
	
	@WebMethod
	@POST
	@Descriptions({
		@Description(value = "stores a new user data", target = DocTarget.METHOD),
		@Description(value = "the newly created user data", target = DocTarget.RETURN)
	})
	public UserData createUser(@Valid UserData userData) throws DuplicateUserException;
	
	@WebMethod
	@DELETE
	@Path("{nif}")
	@Descriptions({
		@Description(value = "deletes a user data", target = DocTarget.METHOD),
		@Description(value = "the user data", target = DocTarget.RETURN)
	})
    public void deleteUser(@Description(value = "the user's nif") @PathParam("nif") @NotNull @Size(max=9, min=9) String nif) throws UserNotFoundException;
	
}
