package crm.geoalert.geoalertserver.controllers;

import javax.servlet.annotation.WebServlet;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.jdbc.core.JdbcTemplate;

import crm.geoalert.geoalertserver.services.User;
import crm.geoalert.geoalertserver.services.UserService;
import crm.geoalert.geoalertserver.utilities.DataSourceFactory;

@Path("/v1/user")
@WebServlet("/v1/user/*")
public class UserController {
	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response denyGetRequest(){
        return Response.status(403).entity("unauthorised GET request").build();
    }


    /**
     * @param username username to authenticate
     * @param password encrypted password to authenticate
     * @return  HTTP error code
     */
    @Path("/authenticate")
    @POST @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response AuthenticateUser(@FormParam("username") String username, @FormParam("password") String password) {
    	User user = new User(username, password);
    	return new UserService(user).AuthenticateUser();
    }

}
