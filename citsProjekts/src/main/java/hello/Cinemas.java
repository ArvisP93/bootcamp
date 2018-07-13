package hello;

public class Cinemas {
	public int cinema_id;
	public String name;
	public double latitude;
	public double longitude;
	
	public Cinemas(int id, String name, double latitude, double longitude){
		this.cinema_id=id;
		this.name=name;
		this.latitude=latitude;
		this.longitude=longitude;
	}

	public int getCinema_id() {
		return cinema_id;
	}

	public void setCinema_id(int cinema_id) {
		this.cinema_id = cinema_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
}
