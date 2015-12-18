package crm.geoalert.geoalertserver.services;

public class User {

	private String username;
	private String password;
	private String accountCreationDate;
	private String email;
	private String lang;
	private String gender;

	public User() {
	}

	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public User(String username, String password, String email, String lang, String gender) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.lang = lang;
		this.gender = gender;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getAccountCreationDate() {
		return accountCreationDate;
	}

	public String getEmail() {
		return email;
	}

	public String getLang() {
		return lang;
	}

	public String getGender() {
		return gender;
	}

}
