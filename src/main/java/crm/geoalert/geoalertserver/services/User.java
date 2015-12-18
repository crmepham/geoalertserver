package crm.geoalert.geoalertserver.services;

public class User {

	private String username;
	private String password;
	private String accountCreationDate;
	private String email;
	private String lang;
	private String key;

	public User() {
	}

	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public User(String username, String password, String email, String lang, String securityAnswer,
			String securityQuestion, String key) {
		this.username = username;
		this.password = password;
		this.key = key;
		this.email = email;
		this.lang = lang;
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

	public String getKey() {
		return key;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", accountCreationDate=" + accountCreationDate
				+ ", email=" + email + ", lang=" + lang + ", key=" + key + "]";
	}

}
