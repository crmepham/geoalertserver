package crm.geoalert.geoalertserver.services;

import java.sql.ResultSet;

import javax.ws.rs.core.Response;

import org.springframework.jdbc.core.JdbcTemplate;

import crm.geoalert.geoalertserver.utilities.DataSourceFactory;

public class UserService extends BaseService{
	private Response response;
	private JdbcTemplate jdbcTemplate;
	private User user;

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
		} else {
			try {
				int rowsAffected = jdbcTemplate.update(
						"insert into user (username, password, accountCreationDate, email, lang, gender)"
								+ "values(?, ?, now(), ?, ?, ?)",
						user.getUsername(), user.getPassword(), user.getEmail(), user.getLang(), user.getGender());

				if (rowsAffected > 0) {
					response = Response.status(200).entity("User registered successfully").build();
				} else {
					response = Response.status(500).entity("User could not be registered").build();
				}

			} catch (Exception e) {
				response = Response.status(500).entity("Server error: " + e.getMessage()).build();
			}
		}
		return response;
	}
}
