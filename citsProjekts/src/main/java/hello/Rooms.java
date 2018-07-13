package hello;

public class Rooms {
	public int room_id;
	public int cinema_id;
	public String name;
	public int seats;
	
	public Rooms(int rid, int cid, String name, int seats) {
		this.room_id=rid;
		this.cinema_id=cid;
		this.name=name;
		this.seats=seats;
	}

	public int getRoom_id() {
		return room_id;
	}

	public void setRoom_id(int room_id) {
		this.room_id = room_id;
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

	public int getSeats() {
		return seats;
	}

	public void setSeats(int seats) {
		this.seats = seats;
	}
}
