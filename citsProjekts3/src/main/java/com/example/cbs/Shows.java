package com.example.cbs;

import java.sql.Date;

//import java.sql.Date;

public class Shows {
	public int show_id;
	public int cinema_id;
	public int movie_id;
	public int room_id;
	public String room_name;
	public String cinema_name;
	public String movie_name;
	public String movie_genre;
	
	public Date date;
	public String taken_seats;
	public int total_seats;
	public Shows() {} //constructor for testing purposes
	
	public Shows(int sid, int cid, String cinema_name, int mid, String movie_name, String movie_genre, int rid, 
			String room_name, Date date, String taken_seats, int total_seats) {
		setShow_id(sid);
		setCinema_id(cid);
		setCinema_name(cinema_name);
		setMovie_id(mid);
		setMovie_name(movie_name);
		setMovie_genre(movie_genre);
		setRoom_id(rid);
		setRoom_name(room_name);
		setDate(date);
		setTaken_seats(taken_seats);
		setTotal_seats(total_seats);
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
		this.taken_seats=taken_seats;
}

	public String getCinema_name() {
		return cinema_name;
	}

	public void setCinema_name(String cinema_name) {
		if(cinema_name == "" || cinema_name.length() > 30) {
			throw new IllegalArgumentException();
		}
		else {
			this.cinema_name = cinema_name;
		}
	}

	public int getTotal_seats() {
		return total_seats;
	}

	public void setTotal_seats(int total_seats) {
		if(total_seats <= 0) {
			throw new IllegalArgumentException();
		}else {
			this.total_seats = total_seats;
		}
	}

	public String getRoom_name() {
		return room_name;
	}

	public void setRoom_name(String room_name) {
		if(room_name == "" || room_name.length() > 20) {
			throw new IllegalArgumentException();
		}else {
			this.room_name = room_name;
		}
		
	}

	public String getMovie_name() {
		return movie_name;
	}

	public void setMovie_name(String movie_name) {
		if(movie_name == ""|| movie_name.length() > 30) {
			throw new IllegalArgumentException();
		}else {
			this.movie_name = movie_name;
		}

	}

	public String getMovie_genre() {
		return movie_genre;
	}

	public void setMovie_genre(String movie_genre) {
		if(movie_genre == ""|| movie_genre.length() > 30) {
			throw new IllegalArgumentException();
		}else {
			this.movie_genre = movie_genre;
		}
	}
}
