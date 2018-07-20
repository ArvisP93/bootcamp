package com.example.cbs;

public class Movies {
	public int movie_id;
	public String name;
	public String genre;
	
	public Movies() {} // constructor for testing purposes
	
	public Movies(int id, String name, String genre){
		setMovie_id(id);
		setName(name);
		setGenre(genre);
	}

	public int getMovie_id() {
		return movie_id;
	}

	public void setMovie_id(int movie_id) {
		if(movie_id <= 0) {
			throw new IllegalArgumentException();
		}
		else{
			this.movie_id = movie_id;
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

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		if (genre == "") {
			throw new IllegalArgumentException();
		} else {
			this.genre = genre;
		}
	}
}