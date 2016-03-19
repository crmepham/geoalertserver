package geoalertserver.entities;

public class Location {
	private String username;
	private String latitude;
	private String longitude;
	private String lastUpdated;

	public Location() {
	}

	public Location(String username, String latitude, String longitude) {
		super();
		this.username = username;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public void setLastUpdated(String lastUpdated) {
		this.lastUpdated = lastUpdated;

	}

	public String getLastUpdated() {
		return lastUpdated;
	}

}
