package com.example.demo.cbs;

public class Cinema {
	
	long id;
	String name;
	int NoOfSeats;
	int NoOfRows;
	double lattitude;
	double longitude;
	
	Cinema(){}
	
	Cinema(long id, String name, int NoOfSeats, int NoOfRows, double lattitude, double longitude){
		setId(id);
		setName(name);
		setNoOfSeats(NoOfSeats);
		setNoOfRows(NoOfRows);
		setLattitude(lattitude);
		setLongitude(longitude);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNoOfSeats() {
		return NoOfSeats;
	}

	public void setNoOfSeats(int noOfSeats) {
		NoOfSeats = noOfSeats;
	}
	
	public int getNoOfRows() {
		return NoOfSeats;
	}

	public void setNoOfRows(int noOfRows) {
		NoOfSeats = noOfRows;
	}

	public double getLattitude() {
		return lattitude;
	}

	public void setLattitude(double lattitude) {
		this.lattitude = lattitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
}
