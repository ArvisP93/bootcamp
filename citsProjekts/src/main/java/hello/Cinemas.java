package hello;

public class Cinemas {
	private int cinema_id;
	private String name;
	private double latitude;
	private double longitude;
	
	public Cinemas(){} //constructor for testing purposes
	
	public Cinemas(int id, String name, double latitude, double longitude){
		setCinema_id(id);
		setName(name);
		setLatitude(latitude);
		setLongitude(longitude);
	}

	public int getCinema_id() {
		return cinema_id;
	}

	public void setCinema_id(int cinema_id) {
		if(cinema_id <= 0) {
			throw new IllegalArgumentException();
		}
		else {
			this.cinema_id = cinema_id;
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if(name == "") {
			throw new IllegalArgumentException();
		}
		else {
			this.name = name;
		}
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		if(latitude <=0) {
			throw new IllegalArgumentException();
		}
		else {
			this.latitude = latitude;
		}
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		if(longitude <=0) {
			throw new IllegalArgumentException();
		}
		else {
			this.longitude = longitude;
		}
	}
}

