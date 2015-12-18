package crm.geoalert.geoalertserver.services;

import java.sql.ResultSet;

import javax.ws.rs.core.Response;

import org.springframework.jdbc.core.JdbcTemplate;

import crm.geoalert.geoalertserver.utilities.DataSourceFactory;

public class UserService {
	private Response response;
	private JdbcTemplate jdbcTemplate;
	private User user;

	public UserService(User user) {
		jdbcTemplate = new JdbcTemplate(DataSourceFactory.getMySQLDataSource());
		this.user = user;
	}

	public Response AuthenticateUser() {
		ResultSet rs = null;

		try {
			int rowsAffected = jdbcTemplate.queryForInt("select count(*) from user where username = ? and password = ?", user.getUsername(), user.getPassword());
			
			if(rowsAffected > 0) {
				response = Response.status(200).entity("authorised").build();
			} else {
				response = Response.status(401).entity("unauthorised").build();
			}
		} catch (Exception e) {
			response = Response.status(500).entity("server error: " + e.getMessage()).build();
		}

		return response;
	}
}
