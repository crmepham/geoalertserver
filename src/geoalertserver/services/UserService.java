package geoalertserver.services;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.Response;

import org.apache.commons.io.IOUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.google.gson.Gson;

import geoalertserver.entities.Notification;
import geoalertserver.entities.User;
import geoalertserver.utilities.DataSourceFactory;
import geoalertserver.utilities.UserRowMapper;

public class UserService extends BaseService {

	private Response response;
	private JdbcTemplate jdbcTemplate;
	private User user;
	private Notification notification;

	public UserService() {
		this(new User());
	}

	public UserService(User user) {
		jdbcTemplate = new JdbcTemplate(DataSourceFactory.getMySQLDataSource());
		this.user = user;
	}
	
	public UserService(Notification notification) {
		jdbcTemplate = new JdbcTemplate(DataSourceFactory.getMySQLDataSource());
		this.notification = notification;
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
			int rowsAffected = jdbcTemplate.queryForInt("select count(*) from user where email = ?", user.getEmail());

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
			int rowsAffected = jdbcTemplate.queryForInt(
					"select count(*) from user where email = ? and securityQuestion = ? and securityAnswer = ?",
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
			int rowsAffected = jdbcTemplate.update(
					"update user set password = ? where email = ? and securityQuestion = ? and securityAnswer = ?",
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
			String securityQuestion = (String) jdbcTemplate.queryForObject(
					"select securityQuestion from user where email = ?", new Object[] { user.getEmail() },
					String.class);
			if (!securityQuestion.isEmpty()) {
				response = Response.status(201).entity(securityQuestion).header("securityQuestin", securityQuestion)
						.build();
			} else {
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
		} else {
			try {
				int rowsAffected = jdbcTemplate.update(
						"insert into user (username, password, accountCreationDate, email, lang, contactNumber, securityQuestion, securityAnswer)"
								+ "values(?, ?, now(), ?, ?, ?, ?, ?)",
						user.getUsername(), user.getPassword(), user.getEmail(), user.getLang(),
						user.getContactNumber(), user.getSecurityQuestion(), user.getSecurityAnswer());

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

	@SuppressWarnings("unchecked")
	public Response retrieveProfileInformation() {
		try {
			User dbUser = (User) jdbcTemplate.queryForObject("select * from user where username =?",
					new Object[] { user.getUsername() }, new UserRowMapper());
			if (dbUser != null) {
				ObjectMapper mapper = new ObjectMapper();
				response = Response.status(201).entity(mapper.writeValueAsString(dbUser))
						.header("user", mapper.writeValueAsString(dbUser)).build();
			} else {
				response = Response.status(401).entity("could not retrieve user information").build();
			}

		} catch (Exception e) {
			response = Response.status(500).entity("Server error: " + e.getMessage()).build();
		}

		return response;
	}

	public Response uploadProfileImage(InputStream uploadedStream) {
		try {
			byte[] byteArray = IOUtils.toByteArray(uploadedStream);
			int rowsAffected = jdbcTemplate.update("update user set profileImage=? where username=?", byteArray,
					user.getUsername());

			if (rowsAffected > 0) {
				response = Response.status(201).entity("successfully uploaded profile image").build();
			} else {
				response = Response.status(401).entity("could not upload profile image").build();
			}
		} catch (DataAccessException e) {
			response = Response.status(500).entity("Server error: " + e.getMessage()).build();
			e.printStackTrace();
		} catch (IOException e) {
			response = Response.status(500).entity("Server error: " + e.getMessage()).build();
			e.printStackTrace();
		}

		return response;
	}

	public Response retrieveProfileImage() {
		try {
			byte[] array = (byte[]) jdbcTemplate.queryForObject("select profileImage from user where username = ?",
					new Object[] { user.getUsername() }, byte[].class);
			if (array.length > 0) {
				response = Response.status(201).entity(array).header("image", array).build();
			} else {
				response = Response.status(401).entity("could not retrieve profile image").build();
			}

		} catch (Exception e) {
			response = Response.status(401).entity("could not retrieve profile image").build();
		}

		return response;
	}
	
	public Response retrieveProfileStatus() {
		try {
			String status = (String) jdbcTemplate.queryForObject("select status from user where username = ?",
					new Object[] { user.getUsername() }, String.class);
			if (status != null) {
				response = Response.status(201).entity(status).build();
			} else {
				response = Response.status(401).entity("could not retrieve profile status").build();
			}

		} catch (Exception e) {
			response = Response.status(401).entity("could not retrieve profile status").build();
		}

		return response;
	}

	public Response updateProfileInformation() {
		try {
			int rowsAffected = jdbcTemplate.update(
					"update user set fullName = ?, gender = ?, dob = ?, bloodType = ?, height = ?, weight = ?, clothingTop = ?, clothingBottom = ?, clothingShoes = ?, "
					+ "nextOfKinFullName = ?, nextOfKinRelationship = ?, nextOfKinContactNumber = ? where username =?",
					user.getFullName(), user.getGender(), user.getDob(), user.getBloodType(), user.getHeight(), user.getWeight(), user.getClothingTop(), user.getClothingBottom(), user.getClothingShoes(),
					user.getNextOfKinFullName(), user.getNextOfKinRelationship(), user.getNextOfKinContactNumber(), user.getUsername());

			if (rowsAffected > 0) {
				response = Response.status(201).entity("successfully updated profile information").build();
			} else {
				response = Response.status(401).entity("could not update profile information").build();
			}
		} catch (DataAccessException e) {
			response = Response.status(500).entity("Server error: " + e.getMessage()).build();
			e.printStackTrace();
		}

		return response;
	}
	
	public Response updateStatus() {
		try {
			int rowsAffected = jdbcTemplate.update("update user set status = ? where username =?", user.getStatus(), user.getUsername());

			if (rowsAffected > 0) {
				response = Response.status(201).entity("successfully updated user status").build();
			} else {
				response = Response.status(401).entity("could not update user status").build();
			}
		} catch (DataAccessException e) {
			response = Response.status(500).entity("Server error: " + e.getMessage()).build();
			e.printStackTrace();
		}

		return response;
	}

	public Response retrieveUserContacts() {
		try {
			List<User> userList = new ArrayList<>();
			List<Map<String, Object>> rows = jdbcTemplate.queryForList("select u.username, u.fullName, u.status, c.userId, c.contactId from contact c "+
																		"join user u on c.contactId=u.userId "+
																		"where c.userId=(select userId from user where username=?) and accepted=1 order by u.fullName", user.getUsername());
			for(Map row : rows) {
				User user = new User();
				user.setUserId((int)row.get("contactId"));
				user.setFullName((String)row.get("fullName"));
				user.setUsername((String)row.get("username"));
				user.setStatus((String)row.get("status"));
				userList.add(user);
			}
			
			if (userList.size() > 0) {
				
				String jsonString = new Gson().toJson(userList);
				
				response = Response.status(201).entity(jsonString).build();
			} else {
				response = Response.status(401).entity("This user has no contacts").build();
			}

		} catch (Exception e) {
			response = Response.status(500).entity("Server error: " + e.getMessage()).build();
		}

		return response;
	}

	public Response deleteContact(int contactId) {
		try{
			int rowsEffected = jdbcTemplate.update("delete from contact where userId=(select userId from user where username=?) and contactId=?", new Object[]{user.getUsername(), contactId});
			rowsEffected += jdbcTemplate.update("delete from contact where userId=? and contactId=(select userId from user where username=?)", new Object[]{contactId, user.getUsername()});
			if(rowsEffected > 0) {
				response = Response.status(201).entity("This contact was deleted.").build();
			}else{
				response = Response.status(401).entity("This contact does not exist.").build();
			}
		}catch(Exception e) {
			response = Response.status(500).entity("Server error: " + e.getMessage()).build();
		}
		return response;
	}

	public Response retrievePendingContactRequests() {
		try {
			List<User> userList = new ArrayList<>();
			List<Map<String, Object>> rows = jdbcTemplate.queryForList("select u.username, u.fullName, u.status, c.userId, c.contactId from contact c " +
																		"join user u on c.userId=u.userId " +
																		"where c.contactId =(select userId from user where username=?) and accepted=0;", user.getUsername());
			for(Map row : rows) {
				User user = new User();
				user.setUserId((int)row.get("userId"));
				user.setFullName((String)row.get("fullName"));
				user.setUsername((String)row.get("username"));
				user.setStatus((String)row.get("status"));
				userList.add(user);
			}
			
			if (userList.size() > 0) {
				
				String jsonString = new Gson().toJson(userList);
				
				response = Response.status(201).entity(jsonString).build();
			} else {
				response = Response.status(401).entity("This user has no contacts").build();
			}

		} catch (Exception e) {
			response = Response.status(500).entity("Server error: " + e.getMessage()).build();
		}

		return response;
	}

	public Response acceptContactRequest(int userId) {
		try{
			int rowsEffected = jdbcTemplate.update("update contact set accepted=1 where userId=? and contactId=(select userId from user where username=?)", new Object[]{userId, user.getUsername()});
			if(rowsEffected > 0) {
				int myId = jdbcTemplate.queryForInt("select userId from user where username=?", user.getUsername());
				jdbcTemplate.update("insert into contact (userId, contactId, accepted) values(?,?,1)", new Object[]{myId, userId});
				response = Response.status(201).entity("This contact request was accepted.").build();
			}else{
				response = Response.status(401).entity("Could not find this contact request.").build();
			}
		}catch(Exception e) {
			response = Response.status(500).entity("Server error: " + e.getMessage()).build();
		}
		return response;
	}

	public Response declineContactRequest(int userId) {
		try{
			int rowsEffected = jdbcTemplate.update("delete from contact where userId=? and contactId=(select userId from user where username=?)", new Object[]{userId, user.getUsername()});
			if(rowsEffected > 0) {
				response = Response.status(201).entity("This contact request was declined.").build();
			}else{
				response = Response.status(401).entity("Could not find this contact request.").build();
			}
		}catch(Exception e) {
			response = Response.status(500).entity("Server error: " + e.getMessage()).build();
		}
		return response;
	}

	public Response addContact(String contactUsername) {
		int userId = 0;
		int contactId = 0;
		try{
			userId = jdbcTemplate.queryForInt("select userId from user where username=?", user.getUsername());
			contactId = jdbcTemplate.queryForInt("select userId from user where username=?", contactUsername);
			
			
			if(contactId != 0){
				
				
				// if you added contact before and they didnt accept
				if(jdbcTemplate.queryForInt("select count(*) from contact where userId=? and contactId=? and accepted=0", new Object[]{userId, contactId}) > 0){
					response = Response.status(402).entity("A request has already been sent to this user.").build();
				}else
				
				// if your added contact before and they already accepted
				if(jdbcTemplate.queryForInt("select count(*) from contact where userId=? and contactId=? and accepted=1", new Object[]{userId, contactId}) > 0){
					response = Response.status(403).entity("That user is already a contact.").build();
				}else
				
				// if they added you and you didnt accept
				if(jdbcTemplate.queryForInt("select count(*) from contact where userId=? and contactId=? and accepted=0", new Object[]{contactId, userId}) > 0){
					jdbcTemplate.update("update contact set accepted=1 where userId=? and contactId=?", new Object[]{contactId, userId});
					jdbcTemplate.update("insert into contact (userId, contactId, accepted) values(?,?,1)", new Object[]{userId, contactId});
					response = Response.status(201).entity("A pending contact request from this user already existed and is now accepted.").build();
				}else
				
				// if they added you and you already accepted
				if(jdbcTemplate.queryForInt("select count(*) from contact where userId=? and contactId=? and accepted=1", new Object[]{contactId, userId}) > 0){
					response = Response.status(403).entity("That user is already a contact.").build();
				}else{
					jdbcTemplate.update("insert into contact (userId, contactId) values(?,?)", new Object[]{userId, contactId});
					response = Response.status(201).entity("Contact request sent.").build();
				}
			}else{
				response = Response.status(402).entity("That user does not exist.").build();
			}
				
		}catch(Exception e) {
			response = Response.status(500).entity("Server error: " + e.getMessage()).build();
			e.printStackTrace();
		}
		return response;
	}
	
	public Response addNotification(String senderUsername) {
		int userId = 0;
		int senderId = 0;
		try{
			// Get userId and contactId
			userId = jdbcTemplate.queryForInt("select userId from user where username=?", user.getUsername());
			senderId = jdbcTemplate.queryForInt("select userId from user where username=?", senderUsername);
			
			// if a notification already exists but was deleted
			if(jdbcTemplate.queryForInt("select count(*) from notification where userId=? and senderId=? and deleted=1", new Object[]{userId, senderId}) > 0) {
				jdbcTemplate.update("update notification set deleted=0, dateCreated=now() where userId=? and senderId=?", new Object[]{userId, senderId});
				response = Response.status(201).entity("Notification created").build();
				
			// if a notification doesn't exist create a new one
			}else if(jdbcTemplate.queryForInt("select count(*) from notification where userId=? and senderId=?", new Object[]{userId, senderId}) == 0) {
				jdbcTemplate.update("insert into notification (userId, senderId, dateCreated) values(?,?,now())", new Object[]{userId, senderId});
				response = Response.status(201).entity("Notification created").build();
				
			}else{
				response = Response.status(402).entity("Notification could not be added").build();
			}
			
		} catch (Exception e) {
			response = Response.status(500).entity("Server error: " + e.getMessage()).build();
			e.printStackTrace();
		}
		
		
		
		// add a notification from this userId to contactId
		return response;
	}
	
	public Response deleteNotification(String senderUsername) {
		int userId = 0;
		int senderId = 0;
		try{
			// Get userId and contactId
			userId = jdbcTemplate.queryForInt("select userId from user where username=?", user.getUsername());
			senderId = jdbcTemplate.queryForInt("select userId from user where username=?", senderUsername);
			
			// if a notification already exists but was deleted
			if(jdbcTemplate.queryForInt("select count(*) from notification where userId=? and senderId=? and deleted=0", new Object[]{userId, senderId}) > 0) {
				jdbcTemplate.update("update notification set deleted=1 where userId=? and senderId=?", new Object[]{userId, senderId});
				response = Response.status(201).entity("Notification deleted").build();
				
			// if a notification doesn't exist create a new one
			}else{
				response = Response.status(402).entity("Notification could not be deleted").build();
			}
			
		} catch (Exception e) {
			response = Response.status(500).entity("Server error: " + e.getMessage()).build();
			e.printStackTrace();
		}
		
		
		
		// add a notification from this userId to contactId
		return response;
	}

	public Response retrieveNotifications() {
		try {
			int userId = jdbcTemplate.queryForInt("select userId from user where username=?", user.getUsername());
			List<Notification> notificationList = new ArrayList<>();
			List<Map<String, Object>> rows = jdbcTemplate.queryForList("select u.fullName, u.username, u.status, u.nextOfKinContactNumber as nokNumber "+
																		"from user u join notification n on n.userId = u.userId " +
																		"where senderId=? and n.deleted=0", userId);
			for(Map row : rows) {
				Notification notification = new Notification();
				notification.setSenderFullName((String)row.get("fullName"));
				notification.setSenderUsername((String)row.get("username"));
				notification.setNokNumber((String)row.get("nokNumber"));
				notification.setStatus((String)row.get("status"));
				notificationList.add(notification);
			}
			
			if (notificationList.size() > 0) {
				
				String jsonString = new Gson().toJson(notificationList);
				
				response = Response.status(201).entity(jsonString).build();
			} else {
				response = Response.status(401).entity("This user has no notifications").build();
			}

		} catch (Exception e) {
			response = Response.status(500).entity("Server error: " + e.getMessage()).build();
		}

		return response;
	}
	
	public Response updateMapView() {
		try {
			int rowsAffected = jdbcTemplate.update("update user set showMap = ? where username =?", user.isShowMap(), user.getUsername());

			if (rowsAffected > 0) {
				response = Response.status(201).entity("successfully updated map view preference").build();
			} else {
				response = Response.status(401).entity("could not update map view preference").build();
			}
		} catch (DataAccessException e) {
			response = Response.status(500).entity("Server error: " + e.getMessage()).build();
			e.printStackTrace();
		}

		return response;
	}
}
