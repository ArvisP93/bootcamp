package com.example.demo.cbs;

public class Cinema {

	private long id;
	private String name;
	private int NoOfSeats;
	private int NoOfRows;
	private double lattitude;
	private double longitude;

	Cinema() {} // constructor for testing purposes

	Cinema(long id, String name, int NoOfSeats, int NoOfRows, double lattitude, double longitude) {
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
		if (id <= 0) {
			throw new IllegalArgumentException();
		} else {
			this.id = id;
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (name == "") {
			throw new IllegalArgumentException();
		} else {
			this.name = name;
			;
		}
	}

	public int getNoOfSeats() {
		return NoOfSeats;
	}

	public void setNoOfSeats(int noOfSeats) {
		if (noOfSeats <= 0) {
			throw new IllegalArgumentException();
		} else {
			this.NoOfSeats = noOfSeats;
		}
	}

	public int getNoOfRows() {
		return NoOfSeats;
	}

	public void setNoOfRows(int noOfRows) {
		noOfRows = NoOfSeats;
	}

	public double getLattitude() {
		return lattitude;
	}

	public void setLattitude(double lattitude) {
		if (lattitude <= 0) {
			throw new IllegalArgumentException();
		} else {
			this.lattitude = lattitude;
		}
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		if (longitude <= 0) {
			throw new IllegalArgumentException();
		} else {
			this.longitude = longitude;
		}
	}

}

