package geoalertserver.controllers;

import javax.servlet.annotation.WebServlet;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import geoalertserver.entities.Location;
import geoalertserver.services.LocationService;

@Path("/v1/location")
@WebServlet("/v1/location/*")
public class LocationController {

	
	/**
	 * 
	 * @param username
	 * @param latitude
	 * @param longitude
	 * @return HTTP response code
	 */
	@Path("/update")
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Response registerUser(@FormParam("username") String username, @FormParam("latitude") String latitude,
			@FormParam("longitude") String longitude) {

		Location location = new Location(username, latitude, longitude);
		return new LocationService(location).updateLocation();

	}
	
	/**
	 * 
	 * @param username
	 * @return JSON location object
	 */
	@Path("/retreive")
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Response registerUser(@FormParam("username") String username) {

		Location location = new Location();
		location.setUsername(username);
		return new LocationService(location).retreiveLocation();

	}
}
