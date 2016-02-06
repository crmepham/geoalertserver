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

	private String status;
	private String gender;
	private String fullName;
	private String dob;
	private String bloodType;
	private String height;
	private String weight;
	private String clothingTop;
	private String clothingBottom;
	private String clothingShoes;
	private String nextOfKinFullName;
	private String nextOfKinRelationship;
	private String nextOfKinContactNumber;

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
		if(this.username == null){
			return "";
		}
		return username;
	}

	public String getPassword() {
		if(this.password == null){
			return "";
		}
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAccountCreationDate() {
		if(this.accountCreationDate == null){
			return "";
		}
		return accountCreationDate;
	}

	public String getEmail() {
		if(this.email == null){
			return "";
		}
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLang() {
		if(this.lang == null){
			return "";
		}
		return lang;
	}

	public String getContactNumber() {
		if(this.contactNumber == null){
			return "";
		}
		return contactNumber;
	}

	public String getSecurityQuestion() {
		if(this.securityQuestion == null){
			return "";
		}
		return securityQuestion;
	}

	public String getSecurityAnswer() {
		if(this.securityAnswer == null){
			return "";
		}
		return securityAnswer;
	}

	public void setSecurityQuestion(String securityQuestion) {
		this.securityQuestion = securityQuestion;
	}

	public void setSecurityAnswer(String securityAnswer) {
		this.securityAnswer = securityAnswer;
	}

	public String getStatus() {
		if(this.status == null){
			return "";
		}
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getGender() {
		if(this.gender == null){
			return "";
		}
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getFullName() {
		if(this.fullName == null){
			return "";
		}
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getDob() {
		if(this.dob == null){
			return "";
		}
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getBloodType() {
		if(this.bloodType == null){
			return "";
		}
		return bloodType;
	}

	public void setBloodType(String bloodType) {
		this.bloodType = bloodType;
	}

	public String getHeight() {
		if(this.height == null){
			return "";
		}
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getWeight() {
		if(this.weight == null){
			return "";
		}
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getClothingTop() {
		if(this.clothingTop == null){
			return "";
		}
		return clothingTop;
	}

	public void setClothingTop(String clothingTop) {
		this.clothingTop = clothingTop;
	}

	public String getClothingBottom() {
		if(this.clothingBottom == null){
			return "";
		}
		return clothingBottom;
	}

	public void setClothingBottom(String clothingBottom) {
		this.clothingBottom = clothingBottom;
	}

	public String getClothingShoes() {
		if(this.clothingShoes == null){
			return "";
		}
		return clothingShoes;
	}

	public void setClothingShoes(String clothingShoes) {
		this.clothingShoes = clothingShoes;
	}

	public String getNextOfKinFullName() {
		if(this.nextOfKinFullName == null){
			return "";
		}
		return nextOfKinFullName;
	}

	public void setNextOfKinFullName(String nextOfKinFullName) {
		this.nextOfKinFullName = nextOfKinFullName;
	}

	public String getNextOfKinRelationship() {
		if(this.nextOfKinRelationship == null){
			return "";
		}
		return nextOfKinRelationship;
	}

	public void setNextOfKinRelationship(String nextOfKinRelationship) {
		this.nextOfKinRelationship = nextOfKinRelationship;
	}

	public String getNextOfKinContactNumber() {
		if(this.nextOfKinContactNumber == null){
			return "";
		}
		return nextOfKinContactNumber;
	}

	public void setNextOfKinContactNumber(String nextOfKinContactNumber) {
		this.nextOfKinContactNumber = nextOfKinContactNumber;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setAccountCreationDate(String accountCreationDate) {
		this.accountCreationDate = accountCreationDate;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", accountCreationDate=" + accountCreationDate
				+ ", email=" + email + ", lang=" + lang + ", contactNumber=" + contactNumber + "]";
	}

}
