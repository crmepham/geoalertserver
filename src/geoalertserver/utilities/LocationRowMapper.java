package geoalertserver.utilities;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import geoalertserver.entities.Location;
import geoalertserver.entities.User;

public class LocationRowMapper implements RowMapper{

	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		Location location = new Location();
		location.setUsername(rs.getString("username"));
		location.setLatitude(rs.getString("latitude"));
		location.setLongitude(rs.getString("longitude"));
		location.setLastUpdated(rs.getString("lastUpdated"));
		return location;
	}

}