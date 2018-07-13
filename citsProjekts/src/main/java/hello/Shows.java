package hello;

import java.sql.Date;

public class Shows {
	private int show_id;
	private int cinema_id;
	private int movie_id;
	private int room_id;
	private Date date;
	private String taken_seats;
	
	public Shows() {} //constructor for testing purposes

	public Shows(int show_id, int cinema_id, int movie_id, int room_id, Date date, String taken_seats) {
		setShow_id(show_id);
		setCinema_id(cinema_id);
		setMovie_id(movie_id);
		setRoom_id(room_id);
		setDate(date);
	    setTaken_seats(taken_seats);
	}

	public int getShow_id() {
		return show_id;
	}

	public void setShow_id(int show_id) {
		if(show_id <= 0) {
			throw new IllegalArgumentException();
		}else {
			this.show_id = show_id;
		}
	}

	public int getCinema_id() {
		return cinema_id;
	}

	public void setCinema_id(int cinema_id) {
		if(cinema_id <= 0) {
			throw new IllegalArgumentException();
		}else {
			this.cinema_id = cinema_id;
		}
	}

	public int getMovie_id() {
		return movie_id;
	}

	public void setMovie_id(int movie_id) {
		if(movie_id <= 0) {
			throw new IllegalArgumentException();
		}else {
			this.movie_id = movie_id;
		}
	}

	public int getRoom_id() {
		return room_id;
	}

	public void setRoom_id(int room_id) {
		if(room_id <= 0) {
			throw new IllegalArgumentException();
		}else {
			this.room_id = room_id;
		}
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getTaken_seats() {
		return taken_seats;
	}

	public void setTaken_seats(String taken_seats) {
		if(taken_seats == "") {
			throw new IllegalArgumentException();
		}else {
			this.room_id = room_id;
		}
	}
}

