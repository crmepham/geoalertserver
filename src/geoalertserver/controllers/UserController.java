package geoalertserver.controllers;

import javax.servlet.annotation.WebServlet;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import geoalertserver.services.User;
import geoalertserver.services.UserService;

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
     * @return  HTTP response code
     */
    @Path("/authenticate")
    @POST @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response AuthenticateUser(@FormParam("username") String username, @FormParam("password") String password) {
    	
    	User user = new User(username, password);
    	return new UserService(user).authenticateUser();
    }
    
    /**
    *
    * @param username username
    * @param password password
    * @param email contact email
    * @param lang users preferred language
    * @return HTTP response code
    */
   @Path("/register")
   @POST @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
   @Produces(MediaType.APPLICATION_JSON)
   public Response registerUser(@FormParam("username") String username, @FormParam("password") String password, @FormParam("contactNumber") String contactNumber,
                                @FormParam("email") String email, @FormParam("lang") String lang){

	   	User user = new User(username, password, email, lang, contactNumber);
   		return new UserService(user).registerUser();

   }

   /**
    * 
    * @param email email of user who has forgotten their password
    * @return HTTP response code
    */
  @Path("/account/recover")
  @POST @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  @Produces(MediaType.APPLICATION_JSON)
  public Response recoverAccount(@FormParam("email") String email){
  		return new UserService().recoverAccount(email);
  }
   
}
