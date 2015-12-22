package geoalertserver.services;

import java.sql.ResultSet;
import java.util.List;

import javax.ws.rs.core.Response;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import geoalertserver.utilities.DataSourceFactory;

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
	
	public Response confirmEmail() {

		try {
			int rowsAffected = jdbcTemplate.queryForInt("select count(*) from user where email = ?",
					user.getEmail());

			if (rowsAffected > 0) {
				response = Response.status(201).entity("email exists").build();
			} else {
				response = Response.status(401).entity("email does not exist").build();
			}
		} catch (Exception e) {
			response = Response.status(500).entity("Server error: " + e.getMessage()).build();
		}

		return response;
	}
	
	public Response confirmSecurityAnswer() {

		try {
			int rowsAffected = jdbcTemplate.queryForInt("select count(*) from user where email = ? and securityQuestion = ? and securityAnswer = ?",
					user.getEmail(), user.getSecurityQuestion(), user.getSecurityAnswer());

			if (rowsAffected > 0) {
				response = Response.status(201).entity("valid security answer").build();
			} else {
				response = Response.status(401).entity("invalid security answer").build();
			}
		} catch (Exception e) {
			response = Response.status(500).entity("Server error: " + e.getMessage()).build();
		}

		return response;
	}
	
	public Response saveNewPassword() {

		try {
			int rowsAffected = jdbcTemplate.update("update user set password = ? where email = ? and securityQuestion = ? and securityAnswer = ?",
					user.getPassword(), user.getEmail(), user.getSecurityQuestion(), user.getSecurityAnswer());

			if (rowsAffected > 0) {
				response = Response.status(201).entity("successfully updated password").build();
			} else {
				response = Response.status(401).entity("could not update password").build();
			}
		} catch (DataAccessException e) {
			response = Response.status(500).entity("Server error: " + e.getMessage()).build();
			e.printStackTrace();
		}

		return response;
	}
	
	public Response retrieveSecurityQuestion() {

		try {
			String securityQuestion = (String)jdbcTemplate.queryForObject("select securityQuestion from user where email = ?", new Object[]{user.getEmail()}, String.class);
			if(!securityQuestion.isEmpty()){
				response = Response.status(201).entity(securityQuestion).header("securityQuestin", securityQuestion) .build();
			}else{
				response = Response.status(401).entity("could not retrieve security question").build();
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
						"insert into user (username, password, accountCreationDate, email, lang, contactNumber, securityQuestion, securityAnswer)"
								+ "values(?, ?, now(), ?, ?, ?, ?, ?)",
						user.getUsername(), user.getPassword(), user.getEmail(), user.getLang(), user.getContactNumber(), user.getSecurityQuestion(), user.getSecurityAnswer());

				if (rowsAffected > 0) {
					response = Response.status(201).entity("User registered successfully").build();
				} else {
					response = Response.status(401).entity("User could not be registered").build();
				}

			} catch (Exception e) {
				response = Response.status(500).entity("Server error: " + e.getMessage()).build();
			}
		}
		return response;
	}
}
