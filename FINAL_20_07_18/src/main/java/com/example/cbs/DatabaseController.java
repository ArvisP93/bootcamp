package com.example.cbs;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class DatabaseController {
	//private static final Logger logger = LogManager.getLogger(DatabaseController.class);
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
		ResultSet rs = this.statement.executeQuery("SELECT * FROM Cinemas ORDER BY cinema_id;");
		
		while(rs.next()) {
			tmp.add(new Cinemas(rs.getInt("cinema_id"), rs.getString("name"), rs.getDouble("latitude"), rs.getDouble("longitude")));
		}
		
		
		return tmp;
	}
	

	ArrayList<Shows> getShows(String cinemaID) throws SQLException{
		ArrayList<Shows> tmp = new ArrayList<Shows>();
		//this.statement.executeUpdate(sql);
		ResultSet rs;
		if (!cinemaID.equals("-1")) {
			rs = this.statement.executeQuery("SELECT * FROM Show_info WHERE cinema_id = " + cinemaID + ";");
		} else {
			rs = this.statement.executeQuery("SELECT * FROM Show_info;");
		}
		
		while(rs.next()) {
			tmp.add(new Shows(rs.getInt("show_id"), rs.getInt("cinema_id"), rs.getString("cinema_name"), rs.getInt("movie_id"), rs.getString("name"), rs.getString("genre"), rs.getInt("room_id"), rs.getString("room_name"), rs.getString("date"), rs.getString("taken_seats"), rs.getInt("total_seats")));
		}
		return tmp;
	}
	
	ArrayList<Seat> getShowSeats(String showID) throws NumberFormatException, SQLException{
		ArrayList<Seat> tmp = new ArrayList<Seat>();
		boolean takenSeat;
		//this.statement.executeUpdate(sql);
		ResultSet rs = this.statement.executeQuery("SELECT * FROM Show_info WHERE show_id = " + showID + ";");
		if(rs.next()) {
			
			String[] taken_seats;
			if(!rs.getString("taken_seats").equals(""))
				taken_seats = rs.getString("taken_seats").split(",");
			else
				taken_seats = new String[0];
			
			for(int i=1; i<=rs.getInt("total_seats"); i++) {
				takenSeat=false;
				
				for(String s : taken_seats) {
					if(i==Integer.parseInt(s)) {
						takenSeat=true;
						break;
					}
				}
				
				tmp.add(new Seat(i,takenSeat));
			}
			

		}
		return tmp;
	}
	boolean reserveSeat(String showID, String seatID) throws SQLException {
		
		ResultSet rs = this.statement.executeQuery("SELECT taken_seats FROM Shows WHERE show_id = " + showID + ";");
		
		
		String taken_seats;
		if(rs.next()) {
			taken_seats=rs.getString("taken_seats");
			
			if(taken_seats.equals("")) {
				taken_seats+=seatID;
			}
			else {
				taken_seats+=","+seatID;
			}
			this.statement.executeUpdate("UPDATE Shows SET taken_seats = '" + taken_seats + "' WHERE show_id = " + showID + ";");
			Application.logger.info("Seat ID:" + seatID + " reserved for show ID:" + showID);
			return true;
		}
		else {
			Application.logger.error("Seat ID:" + seatID + " failed to be reserved for show:ID " + showID + " - show_id not found");

			return false;
		}
	}
	
	boolean UserExists(String username) throws SQLException{
		ResultSet rs = this.statement.executeQuery("SELECT username FROM Users WHERE username = " + username + ";");
		return rs.next();
	}
	boolean AddUser(String username, String password, String email, String role) throws SQLException { 
		boolean tmp = this.statement.execute("INSERT INTO Users (username, password, email, role) VALUES ('"+username+"', '"+password+"','"+email+"','"+role+"');");
		if(tmp)
			Application.logger.info("User '" + username + "' succesfully added");
		else
			Application.logger.error("Failed to add user '" + username);
		return !tmp;
}
	
	boolean AddCinema(String name, double latitude, double longitude) throws SQLException {//returns true if successfully added
		boolean tmp = this.statement.execute("INSERT INTO Cinemas (name, latitude, longitude) VALUES ('" + name + "', "+latitude+","+longitude+");");
		if(tmp)
			Application.logger.info("Cinema '" + name + "' (latitude:" + latitude + ", longitude:"+ longitude +") added successfuly");
		else
			Application.logger.error("Failed to add cinema '" + name + "' (latitude:" + latitude + ", longitude:"+ longitude +")");
		return !tmp;
	}
	boolean AddMovie(String title, String genre) throws SQLException {//returns true if successfully added
		boolean tmp = this.statement.execute("INSERT INTO Movies (name, genre) VALUES ('"+title+"', '"+genre+"');");
		if(tmp)
			Application.logger.info("Movie '" + title + "' (genre: " +genre+ ") added successfully");
		else
			Application.logger.error("Failed to add movie '" + title + "' (genre: " +genre+ ")");
		return !tmp;
	}
	boolean AddRoom(int cinema_id, String name, int seats) throws SQLException {
		boolean tmp = this.statement.execute("INSERT INTO Rooms (cinema_id, name, seats) VALUES (" + cinema_id + ", '" + name + "', " + seats + ");");
		if(tmp)
			Application.logger.info("Room '" + name + "' (ID: " +cinema_id+ ", seats:"+seats+") added successfully");
		else
			Application.logger.error("Failed to add room '" + name + "' (ID: " +cinema_id+ ", seats:"+seats+")");
		return !tmp;
	}
	boolean AddShow(int cinema_id, int movie_id, int room_id, String date, String taken_seats) throws SQLException {//returns true if successfully added
		boolean tmp = this.statement.execute("INSERT INTO Shows (cinema_id, movie_id, room_id, date, taken_seats) VALUES ('"+cinema_id+"', '"+movie_id+"', '"+room_id+"', '"+date+"', '"+taken_seats+"');");
		if(tmp)
			Application.logger.info("Show (cinemaID: " +cinema_id+ ", movieID:"+movie_id+", roomID:"+room_id+", date:"+date+", taken_seats:"+taken_seats+") added successfully");
		else
			Application.logger.error("Failed to add show (cinemaID: " +cinema_id+ ", movieID:"+movie_id+", roomID:"+room_id+", date:"+date+", takenSeats:"+taken_seats+")");
		return tmp;
	}

	boolean DeleteCinema(int id) throws SQLException {//returns true if successfully deleted
		boolean tmp = this.statement.execute("DELETE FROM Cinemas WHERE cinema_id = "+id+";");
		boolean tmprooms = this.statement.execute("DELETE FROM Rooms WHERE cinema_id = "+id+";");
		boolean tmpshows = this.statement.execute("DELETE FROM Shows WHERE cinema_id = "+id+";");
		if(tmp)
			Application.logger.info("Cinema ID:" + id + " removed successfully");
		else
			Application.logger.error("Failed to remove cinema ID:" + id + " (not found)");
		return tmp;
	}
	//updated
	boolean DeleteMovie(int id) throws SQLException {//returns true if successfully deleted
		boolean tmp = this.statement.execute("DELETE FROM Movies WHERE movie_id = "+id+";");
		boolean tmpshows = this.statement.execute("DELETE FROM Shows WHERE movie_id = "+id+";");
		if(tmp)
			Application.logger.info("Movie ID:" + id + " removed successfully");
		else
			Application.logger.error("Failed to remove movie ID:" + id + " (not found)");

		return tmp;
	}
	int FindClosest(double CurrLatitude, double CurrLongitude) throws SQLException { //returns the ID of the closest cinema, calculated by Pythagorean formula with latitudes and longitudes
		ResultSet  rs = this.statement.executeQuery("SELECT cinema_id, latitude, longitude, sqrt(pow(latitude-"+CurrLatitude+",2)+pow(longitude-"+CurrLongitude+",2)) as dist from Cinemas order by dist limit 1;");
		rs.next();
		int tmp = rs.getInt("cinema_id");
		return tmp;
	}
	boolean CheckPassword(String username, String password) throws SQLException {
		ResultSet rs = this.statement.executeQuery("SELECT username FROM Users WHERE username = '" + username + "' AND password = '" + password + "';");
		Application.logger.info("Parbauda paroli lietotajam '"+username+"'");
		return rs.next();
	}
	ArrayList<Rooms> getRooms(int cinema_id) throws SQLException{
		ResultSet rs;
		if (cinema_id>0) {
			rs = this.statement.executeQuery("SELECT room_id, cinema_id, room_name, seats FROM Cinema_rooms WHERE cinema_id = "+ cinema_id + " ORDER BY room_name;");
		} else {
			rs = this.statement.executeQuery("SELECT room_id, cinema_id, room_name, seats FROM Cinema_rooms ORDER BY room_name;");
		}
		ArrayList<Rooms> tmp = new ArrayList<Rooms>();
		
		while (rs.next()) {
			tmp.add(new Rooms(rs.getInt("room_id"), rs.getInt("cinema_id"), rs.getString("room_name"),rs.getInt("seats")));
		}
		return tmp;
	}
	
	
	int changeCinema(Cinemas cinema) throws SQLException{
		int tmp = this.statement.executeUpdate("UPDATE Cinemas SET name='"+cinema.getName()+"', latitude = " + cinema.getLatitude() + ", longitude = " + cinema.getLongitude() + " where cinema_id = "+ cinema.getCinema_id() + ";");
		if(tmp==1)
			Application.logger.info("Cinema ID:" + cinema.getCinema_id() + "updated successfully");
		else
			Application.logger.error("Failed to update cinema ID:" + cinema.getCinema_id() + "(not found)");
		return tmp;
	}

	int changeRoom(Rooms room) throws SQLException {
		int tmp = this.statement.executeUpdate("UPDATE Rooms SET name='"+room.getName()+"', seats = " + room.getSeats() + ", cinema_id = " + room.getCinema_id() + " where room_id = "+ room.getRoom_id() + ";");
		if(tmp==1)
			Application.logger.info("Room ID:" + room.getRoom_id() + "updated successfully");
		else
			Application.logger.error("Failed to update room ID:" + room.getRoom_id()+ "(not found)");
		return tmp;	
	}

	Rooms getRoom(int room_id) throws SQLException {
		ResultSet rs = this.statement.executeQuery("SELECT * FROM Rooms WHERE room_id = "+ room_id +";");
		rs.next();
		Rooms tmp = new Rooms(rs.getInt("room_id"),rs.getInt("cinema_id"),rs.getString("name"),rs.getInt("seats"));
		return tmp;
	}

	Shows getShow(int show_id) throws SQLException {
		ResultSet rs = this.statement.executeQuery("SELECT * FROM Show_info WHERE show_id = "+ show_id+";");
		rs.next();
		Shows tmp = new Shows(rs.getInt("show_id"),rs.getInt("cinema_id"),rs.getString("cinema_name"),rs.getInt("movie_id"),rs.getString("name"),rs.getString("genre"),rs.getInt("room_id"),rs.getString("room_name"),rs.getString("date"),rs.getString("taken_seats"),rs.getInt("total_seats"));
		return tmp;
	}
	int changeShow(Shows show) throws SQLException {	 
		int tmp=this.statement.executeUpdate("UPDATE Shows SET cinema_id ="+show.getCinema_id()+",movie_id = "+show.getMovie_id()+",room_id = "+show.getRoom_id()+",date = '" + show.getDate()+"', taken_seats = '"+show.getTaken_seats()+"' where show_id = "+show.getShow_id()+";"); 
		if(tmp==1)
			Application.logger.info("Show ID:" + show.getShow_id() + "updated successfully");
		else
			Application.logger.error("Failed to update show ID:" + show.getShow_id() + "(not found)");
		return tmp;	
	}
	//updated
	boolean DeleteRoom(int id) throws SQLException {//returns true if successfully deleted
		boolean tmp = this.statement.execute("DELETE FROM Rooms WHERE room_id = "+id+";");
		boolean tmproom = this.statement.execute("DELETE FROM Shows WHERE room_id = "+id+";");
		if(tmp)
			Application.logger.info("Room ID:" + id + "removed successfully");
		else
			Application.logger.error("Failed to remove room ID:" + id + "(not found)");
		return tmp;
	}
	Cinemas getCinema(int cinema_id) throws SQLException {
		ResultSet rs = this.statement.executeQuery("SELECT * FROM Cinemas WHERE cinema_id = "+ cinema_id +";");
		rs.next();
		Cinemas tmp = new Cinemas(rs.getInt("cinema_id"),rs.getString("name"),rs.getDouble("latitude"),rs.getDouble("longitude"));
		return tmp;
	}
	Movies getMovie(int movie_id) throws SQLException {
		ResultSet rs = this.statement.executeQuery("SELECT * FROM Movies WHERE movie_id = "+ movie_id+";");
		rs.next();
		Movies tmp = new Movies(rs.getInt("movie_id"),rs.getString("name"),rs.getString("genre"));
		return tmp;
	}
	int changeMovie(Movies movie) throws SQLException {	 
		int tmp = this.statement.executeUpdate("UPDATE Movies SET name ='"+movie.getName()+"',genre = '"+movie.getGenre()+"'where movie_id = "+movie.getMovie_id()+";");
		if(tmp==1)
			Application.logger.info("Movie ID:" + movie.getMovie_id() + "updated successfully");
		else
			Application.logger.error("Failed to update movie ID:" + movie.getMovie_id() + "(not found)");
		return tmp;
	}
	boolean DeleteShow(int id) throws SQLException {//returns true if successfully deleted
		boolean tmp = this.statement.execute("DELETE FROM Shows WHERE show_id = "+id+";");
		if(tmp)
			Application.logger.info("Show ID:" + id + "removed successfully");
		else
			Application.logger.error("Failed to remove show ID:" + id + "(not found)");
		return tmp;
	}
	
	Shows getShowsByID(int show_id) throws SQLException{
		ResultSet rs = this.statement.executeQuery("SELECT * FROM Show_info WHERE show_id = " + show_id + ";");
		rs.next();
		Shows tmp = new Shows(rs.getInt("show_id"), rs.getInt("cinema_id"), rs.getString("cinema_name"), rs.getInt("movie_id"), rs.getString("name"), rs.getString("genre"), rs.getInt("room_id"), rs.getString("room_name"), rs.getString("date"), rs.getString("taken_seats"), rs.getInt("total_seats"));
		return tmp;
	}
	
	ArrayList<String> getUsernames() throws SQLException {//written by TM
		ArrayList<String> tmp = new ArrayList<String>();
		ResultSet rs = this.statement.executeQuery("SELECT username FROM Users;");
		while (rs.next()) {
			tmp.add(rs.getString("username"));
		}
		return tmp;
	}
	ArrayList<Users> getUsers() throws SQLException { //written by TM
		ResultSet rs = this.statement.executeQuery("SELECT * FROM Users;");
		ArrayList<Users> tmp = new ArrayList<Users>();
		while (rs.next()) {
			tmp.add(new Users(rs.getInt("user_id"),rs.getString("username"),rs.getString("password"),rs.getString("email"),rs.getString("role")));
			//System.out.println(rs.getString("username"));
		}
		return tmp;
		
	}

	Users getUser(String username) throws SQLException {//written by TM
		ResultSet rs = this.statement.executeQuery("SELECT * FROM Users where username = '" + username + "';");
		if (rs.next()) {
			return new Users(rs.getInt("user_id"),rs.getString("username"),rs.getString("password"),rs.getString("email"),rs.getString("role"));
		} else
			return null;
	}
	Users getUser(int user_id) throws SQLException {//written by TM
		ResultSet rs = this.statement.executeQuery("SELECT * FROM Users where user_id = '" + user_id + "';");
		if (rs.next()) {
			return new Users(rs.getInt("user_id"),rs.getString("username"),rs.getString("password"),rs.getString("email"),rs.getString("role"));
		} else
			return null;
	}
	boolean deleteUser(int user_id) throws SQLException {
		boolean tmp = this.statement.execute("DELETE FROM Users WHERE user_id = "+user_id+";");
		if(tmp)
			Application.logger.info("User ID:" + user_id + "removed successfully");
		else
			Application.logger.error("Failed to remove user ID:" + user_id + "(not found)");
		return tmp;
	}
	
	public boolean isAdmin(String username) throws SQLException {
		ResultSet rs = this.statement.executeQuery("SELECT username FROM Users WHERE username = '" + username + "' AND role = 'admin';");
		if (rs.next()) {
			return true;
		} else
			return false;
	}
	int changeUser(Users user) throws SQLException { //NEW ONE FROM TM 20.07
		int tmp= this.statement.executeUpdate("UPDATE Users SET username = '"+user.getUsername()+"',email = '"+user.getEmail()+"',role='"+user.getRole()+"' WHERE user_id = "+user.getUser_id()+";");
		if(tmp==1)
			Application.logger.info("User ID:" + user.getUser_id() + "updated successfully");
		else
			Application.logger.error("Failed to update user ID:" + user.getUser_id() + "(not found)");
		return tmp;
	}
	
	
	

	
}
