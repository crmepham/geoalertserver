package geoalertserver.entities;

public class Notification {
	private String senderFullName;
	private String senderUsername;
	private String status;
	private String nokNumber;
	
	public String getSenderName() {
		return senderFullName;
	}
	public void setSenderName(String senderName) {
		this.senderFullName = senderName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSenderFullName() {
		return senderFullName;
	}
	public void setSenderFullName(String senderFullName) {
		this.senderFullName = senderFullName;
	}
	public String getSenderUsername() {
		return senderUsername;
	}
	public void setSenderUsername(String senderUsername) {
		this.senderUsername = senderUsername;
	}
	public String getNokNumber() {
		return nokNumber;
	}
	public void setNokNumber(String nokNumber) {
		this.nokNumber = nokNumber;
	}
}
