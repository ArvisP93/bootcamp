package hello;

public class Movies {
	public int movie_id;
	public String name;
	public String genre;
	
	public Movies(int id, String name, String genre){
		this.movie_id=id;
		this.name=name;
		this.genre=genre;
	}

	public int getMovie_id() {
		return movie_id;
	}

	public void setMovie_id(int movie_id) {
		this.movie_id = movie_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}
}
