package hello;

import java.sql.Date;

public class Shows {
	public int show_id;
	public int cinema_id;
	public int movie_id;
	public int room_id;
	public Date date;
	public String taken_seats;
	
	public Shows(int sid, int cid, int mid, int rid, Date date, String taken_seats) {
		this.show_id=sid;
		this.cinema_id=cid;
		this.movie_id=mid;
		this.room_id=rid;
		this.date=date;
		this.taken_seats=taken_seats;
	}

	public int getShow_id() {
		return show_id;
	}

	public void setShow_id(int show_id) {
		this.show_id = show_id;
	}

	public int getCinema_id() {
		return cinema_id;
	}

	public void setCinema_id(int cinema_id) {
		this.cinema_id = cinema_id;
	}

	public int getMovie_id() {
		return movie_id;
	}

	public void setMovie_id(int movie_id) {
		this.movie_id = movie_id;
	}

	public int getRoom_id() {
		return room_id;
	}

	public void setRoom_id(int room_id) {
		this.room_id = room_id;
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
		this.taken_seats = taken_seats;
	}
}
