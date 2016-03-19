package geoalertserver.services;

import javax.ws.rs.core.Response;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import geoalertserver.entities.Location;
import geoalertserver.entities.User;
import geoalertserver.utilities.DataSourceFactory;
import geoalertserver.utilities.LocationRowMapper;
import geoalertserver.utilities.UserRowMapper;

public class LocationService extends BaseService {
	
	private Response response;
	private JdbcTemplate jdbcTemplate;
	private Location location;
	
	public LocationService(){
		this(new Location());
	}
	
	public LocationService(Location location) {
		this.location = location;
		this.jdbcTemplate = new JdbcTemplate(DataSourceFactory.getMySQLDataSource());
	}

	public Response updateLocation() {
		try {
			int userId = jdbcTemplate.queryForInt("select userId from user where username=?", location.getUsername());
			
			if(jdbcTemplate.queryForInt("select count(*) from location where userId=?", new Object[]{userId}) > 0) {
				jdbcTemplate.update("update location set latitude=?, longitude=?, lastUpdated=now() where userId=?", new Object[]{location.getLatitude(), location.getLongitude(), userId});
				response = Response.status(201).entity("Successfully update existing users location.").build();
			}else{
				jdbcTemplate.update("insert into location (userId, latitude, longitude, lastUpdated) values (?, ?, ?, now())", new Object[]{userId, location.getLatitude(), location.getLongitude()});
				response = Response.status(201).entity("Successfully inserted new users location.").build();
			}
		} catch (DataAccessException e) {
			response = Response.status(500).entity("Server error: " + e.getMessage()).build();
			e.printStackTrace();
		}

		return response;
	}

	@SuppressWarnings("unchecked")
	public Response retreiveLocation() {
		try {
			Location dbLocation = (Location) jdbcTemplate.queryForObject("select u.username, l.latitude, l.longitude, l.lastUpdated from location l join user u on l.userId=u.userId where l.userId = (select userId from user where username=?) ",
					new Object[] { location.getUsername() }, new LocationRowMapper());
			if (dbLocation != null) {
				ObjectMapper mapper = new ObjectMapper();
				response = Response.status(201).entity(mapper.writeValueAsString(dbLocation))
						.header("location", mapper.writeValueAsString(dbLocation)).build();
			} else {
				response = Response.status(401).entity("could not retrieve users location").build();
			}

		} catch (Exception e) {
			response = Response.status(500).entity("Server error: " + e.getMessage()).build();
		}

		return response;
	}
	
	
}
