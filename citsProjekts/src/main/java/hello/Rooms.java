package hello;

public class Rooms {
		private int room_id;
		private int cinema_id;
		private String name;
		private int seats;
		
		public Rooms() {} //constructor for testing purposes
		
		public Rooms(int room_id, int cinema_id, String name, int seats) {
			setRoom_id(room_id);
			setCinema_id(cinema_id);
			setName(name);
			setSeats(seats);
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

		public String getName() {
			return name;
		}

		public void setName(String name) {
			if (name == "") {
				throw new IllegalArgumentException();
			} else {
				this.name = name;;
			}
		}

		public int getSeats() {
			return seats;
		}

		public void setSeats(int seats) {
			if(seats <= 0) {
				throw new IllegalArgumentException();
			}else {
				this.seats = seats;
			}
		}
	}

