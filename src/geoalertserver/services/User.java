package geoalertserver.services;

public class User {

	private String username;
	private String password;
	private String accountCreationDate;
	private String email;
	private String lang;
	private String contactNumber;
	private String securityQuestion;
	private String securityAnswer;

	public User() {
	}

	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public User(String username, String password, String email, String lang, String contactNumber,
			String securityQuestion, String securityAnswer) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.lang = lang;
		this.contactNumber = contactNumber;
		this.securityQuestion = securityQuestion;
		this.securityAnswer = securityAnswer;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAccountCreationDate() {
		return accountCreationDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLang() {
		return lang;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public String getSecurityQuestion() {
		return securityQuestion;
	}

	public String getSecurityAnswer() {
		return securityAnswer;
	}

	public void setSecurityQuestion(String securityQuestion) {
		this.securityQuestion = securityQuestion;
	}

	public void setSecurityAnswer(String securityAnswer) {
		this.securityAnswer = securityAnswer;
	}

	public boolean isIncompleteUser() {
		if (this.username.isEmpty() || this.password.isEmpty() || this.email.isEmpty() || this.lang.isEmpty()
				|| this.contactNumber.isEmpty() || this.securityQuestion.isEmpty() || this.securityAnswer.isEmpty()) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", accountCreationDate=" + accountCreationDate
				+ ", email=" + email + ", lang=" + lang + ", contactNumber=" + contactNumber + "]";
	}

}
