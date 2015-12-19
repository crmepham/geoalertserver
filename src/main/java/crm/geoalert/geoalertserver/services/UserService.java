package crm.geoalert.geoalertserver.services;

import java.sql.ResultSet;
import java.util.List;

import javax.ws.rs.core.Response;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import crm.geoalert.geoalertserver.utilities.DataSourceFactory;

public class UserService extends BaseService{

	private Response response;
	private JdbcTemplate jdbcTemplate;
	private User user;

	public UserService() {
		this(new User());
	}
	
	public UserService(User user) {
		jdbcTemplate = new JdbcTemplate(DataSourceFactory.getMySQLDataSource());
		this.user = user;
	}

	public Response authenticateUser() {
		ResultSet rs = null;

		try {
			int rowsAffected = jdbcTemplate.queryForInt("select count(*) from user where username = ? and password = ?",
					user.getUsername(), user.getPassword());

			if (rowsAffected > 0) {
				response = Response.status(200).entity("Authorised").build();
			} else {
				response = Response.status(401).entity("Unauthorised").build();
			}
		} catch (Exception e) {
			response = Response.status(500).entity("Server error: " + e.getMessage()).build();
		}

		return response;
	}

	public Response registerUser() {

		if (this.authenticateUser().getStatus() == 200) {
			response = Response.status(401).entity("User already exists").build();
		} else if(user.isIncompleteUser()) { 
			response = Response.status(401).entity("User must supply more information").build();
		} else {
			try {
				int rowsAffected = jdbcTemplate.update(
						"insert into user (username, password, accountCreationDate, email, lang, contactNumber)"
								+ "values(?, ?, now(), ?, ?, ?)",
						user.getUsername(), user.getPassword(), user.getEmail(), user.getLang(), user.getContactNumber());

				if (rowsAffected > 0) {
					response = Response.status(201).entity("User registered successfully").build();
				} else {
					response = Response.status(500).entity("User could not be registered").build();
				}

			} catch (Exception e) {
				response = Response.status(500).entity("Server error: " + e.getMessage()).build();
			}
		}
		return response;
	}
	
	public Response recoverAccount(String email) {
		// validate email in app
		String password = "";
		
		try{
			List<String> list = jdbcTemplate.queryForList("select password from user where email = ?", new Object[]{email}, String.class);
			if(list.size() > 0){
				password = list.get(0);
				new EmailService().sendEmail(email, email, "Your password is: " + password + ".");
				response = Response.status(200).entity("Password successfully sent").build();
			}else{
				response = Response.status(404).entity("Could not find user email").build();
			}
		} catch(DataAccessException e) {
			e.printStackTrace();
			response = Response.status(500).entity("Server error: " + e.getMessage()).build();
		}
		return response;
	}
}
