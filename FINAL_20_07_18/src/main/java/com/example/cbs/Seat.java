package com.example.cbs;

public class Seat {
	public int id;
	public boolean taken;
	
	public Seat() {}
	
	public Seat(int id, boolean taken) {
		setId(id);
		setTaken(taken);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		if(id>0)
			this.id = id;
		else
			this.id = 0;
	}

	public boolean isTaken() {
		return taken;
	}

	public void setTaken(boolean taken) {
		this.taken = taken;
	}


}
