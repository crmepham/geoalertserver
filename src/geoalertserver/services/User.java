package geoalertserver.services;

public class User {

	private String username;
	private String password;
	private String accountCreationDate;
	private String email;
	private String lang;
	private String contactNumber;

	public User() {
	}

	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public User(String username, String password, String email, String lang, String contactNumber) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.lang = lang;
		this.contactNumber = contactNumber;
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

	public String getContactNumber() {
		return contactNumber;
	}

	public boolean isIncompleteUser() {
		if (this.username.isEmpty() || this.password.isEmpty() || this.email.isEmpty() || this.lang.isEmpty()
				|| this.contactNumber.isEmpty()) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", accountCreationDate=" + accountCreationDate
				+ ", email=" + email + ", lang=" + lang + ", contactNumber=" + contactNumber
				+ "]";
	}

}
