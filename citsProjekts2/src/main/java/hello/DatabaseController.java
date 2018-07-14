package hello;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DatabaseController {

	Statement statement;
	Connection connection;
	
	public DatabaseController (String database, String user, String pass) throws ClassNotFoundException, SQLException{
		
		Class.forName("com.mysql.jdbc.Driver");
		this.connection=DriverManager.getConnection("jdbc:mysql://localhost/" + database + "?user=" + user + "&password=" + pass);
		this.statement=this.connection.createStatement();
		//GRANT ALL PRIVILEGES ON *.* TO 'springuser'@'localhost' IDENTIFIED BY 'parole' WITH GRANT OPTION;
	}
	
	ArrayList<Movies> getFilmas() throws SQLException{
		ArrayList<Movies> tmp = new ArrayList<Movies>();
		//this.statement.executeUpdate(sql);
		ResultSet rs = this.statement.executeQuery("SELECT * FROM Movies;");
		
		while(rs.next()) {
			tmp.add(new Movies(rs.getInt("movie_id"), rs.getString("name"), rs.getString("genre")));
		}
		return tmp;
	}
	
	ArrayList<Cinemas> getCinemas() throws SQLException{
		ArrayList<Cinemas> tmp = new ArrayList<Cinemas>();
		//this.statement.executeUpdate(sql);
		ResultSet rs = this.statement.executeQuery("SELECT * FROM Cinemas;");
		
		while(rs.next()) {
			tmp.add(new Cinemas(rs.getInt("cinema_id"), rs.getString("name"), rs.getDouble("latitude"), rs.getDouble("longitude")));
		}
		
		
		return tmp;
	}
	
	ArrayList<Shows> getShows(String cinemaID) throws SQLException{
		ArrayList<Shows> tmp = new ArrayList<Shows>();
		//this.statement.executeUpdate(sql);
		ResultSet rs = this.statement.executeQuery("SELECT * FROM Show_info WHERE cinema_id = " + cinemaID + ";");
		
		while(rs.next()) {
			tmp.add(new Shows(rs.getInt("show_id"), rs.getInt("cinema_id"), rs.getString("cinema_name"), rs.getInt("movie_id"), rs.getString("name"), rs.getString("genre"), rs.getInt("room_id"), rs.getString("room_name"), rs.getDate("date"), rs.getString("taken_seats"), rs.getInt("total_seats")));
		}
		
		
		return tmp;
	}
	Shows getShow(String showID) throws SQLException{
		Shows tmp;
		//this.statement.executeUpdate(sql);
		ResultSet rs = this.statement.executeQuery("SELECT * FROM Show_info WHERE show_id = " + showID + ";");
		tmp=new Shows(rs.getInt("show_id"), rs.getInt("cinema_id"), rs.getString("cinema_name"), rs.getInt("movie_id"), rs.getString("name"), rs.getString("genre"), rs.getInt("room_id"), rs.getString("room_name"), rs.getDate("date"), rs.getString("taken_seats"), rs.getInt("total_seats"));
	
		
		
		return tmp;
	}
	
	boolean UserExists(String username) throws SQLException{
		ResultSet rs = this.statement.executeQuery("SELECT username FROM Users WHERE username = " + username + ";");
		return rs.next();
	}
	boolean AddUser(String username, String password) throws SQLException { //returns true if successfully added
		boolean tmp = this.statement.execute("INSERT INTO Users (username, password, role) VALUES ('"+username+"', '"+password+"','user');");
		return !tmp;
	}
	
	boolean AddCinema(String name, double latitude, double longitude) throws SQLException {//returns true if successfully added
		boolean tmp = this.statement.execute("INSERT INTO Cinemas (name, latitude, longitude) VALUES ('" + name + "', "+latitude+","+longitude+");");
		return !tmp;
	}
	boolean AddMovie(String title, String genre) throws SQLException {//returns true if successfully added
		boolean tmp = this.statement.execute("INSERT INTO Movies (name, genre) VALUES ('"+title+"', '"+genre+"');");
		return !tmp;
	}
	boolean AddRoom(int cinema_id, String name, int seats) throws SQLException {
		boolean tmp = this.statement.execute("INSERT INTO Rooms (cinema_id, name, seats) VALUES (" + cinema_id + ", '" + name + "', " + seats + ");");
		return !tmp;
	}
	
	int FindClosest(double CurrLatitude, double CurrLongitude) throws SQLException { //returns the ID of the closest cinema, calculated by Pythagorean formula with latitudes and longitudes
		ResultSet  rs = this.statement.executeQuery("SELECT cinema_id, latitude, longitude, sqrt(pow(latitude-"+CurrLatitude+")+pow(longitude-"+CurrLongitude+")) as dist from Cinemas order by dist limit 1;");
		rs.next();
		Integer tmp = rs.getInt("cinema_id");
		return tmp;
	}
	boolean CheckPassword(String username, String password) throws SQLException {
		ResultSet rs = this.statement.executeQuery("SELECT username FROM Users WHERE username = '" + username + "' AND password = '" + password + "';");
		return rs.next();
	}
	
}
