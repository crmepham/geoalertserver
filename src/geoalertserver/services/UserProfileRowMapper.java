package geoalertserver.services;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class UserProfileRowMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		User user = new User();
		user.setStatus(rs.getString("status"));
		user.setGender(rs.getString("gender"));
		user.setFullName(rs.getString("fullName"));
		user.setDob(rs.getString("dob"));
		user.setBloodType(rs.getString("bloodType"));
		user.setHeight(rs.getString("height"));
		user.setWeight(rs.getString("weight"));
		user.setClothingTop(rs.getString("clothingTop"));
		user.setClothingBottom(rs.getString("clothingBottom"));
		user.setClothingShoes(rs.getString("clothingshoes"));
		user.setNextOfKinFullName(rs.getString("nextOfKinFullName"));
		user.setNextOfKinRelationship(rs.getString("nextOfKinRelationship"));
		user.setNextOfKinContactNumber(rs.getString("nextOfKinContactNumber"));
		return user;
	}

}
