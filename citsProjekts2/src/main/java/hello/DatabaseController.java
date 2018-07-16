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
		ResultSet rs = this.statement.executeQuery("SELECT * FROM Cinemas ORDER BY cinema_id;");
		
		while(rs.next()) {
			tmp.add(new Cinemas(rs.getInt("cinema_id"), rs.getString("name"), rs.getDouble("latitude"), rs.getDouble("longitude")));
		}
		
		
		return tmp;
	}
	Cinemas getCinemas(int cinema_id) throws SQLException{
		Cinemas tmp = new Cinemas();
		//this.statement.executeUpdate(sql);
		ResultSet rs = this.statement.executeQuery("SELECT * FROM Cinemas where cinema_id = " + cinema_id + ";");
		
		while(rs.next()) {
			tmp = new Cinemas(rs.getInt("cinema_id"), rs.getString("name"), rs.getDouble("latitude"), rs.getDouble("longitude"));
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
		
		
		System.out.println("showID: " + showID);
		System.out.println("seatID" + seatID);
		
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
			return true;
		}
		else {
			return false;
		}
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
		ResultSet  rs = this.statement.executeQuery("SELECT cinema_id, latitude, longitude, sqrt(pow(latitude-"+CurrLatitude+",2)+pow(longitude-"+CurrLongitude+",2)) as dist from Cinemas order by dist limit 1;");
		rs.next();
		int tmp = rs.getInt("cinema_id");
		return tmp;
	}
	boolean CheckPassword(String username, String password) throws SQLException {
		ResultSet rs = this.statement.executeQuery("SELECT username FROM Users WHERE username = '" + username + "' AND password = '" + password + "';");
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
		return this.statement.executeUpdate("UPDATE Cinemas SET name='"+cinema.getName()+"', latitude = " + cinema.getLatitude() + ", longitude = " + cinema.getLongitude() + " where cinema_id = "+ cinema.getCinema_id() + ";");
	}

	int changeRoom(Rooms room) throws SQLException {
		return this.statement.executeUpdate("UPDATE Rooms SET name='"+room.getName()+"', seats = " + room.getSeats() + ", cinema_id = " + room.getCinema_id() + " where room_id = "+ room.getRoom_id() + ";");
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
		Shows tmp = new Shows(rs.getInt("show_id"),rs.getInt("cinema_id"),rs.getString("cinema_name"),rs.getInt("movie_id"),rs.getString("name"),rs.getString("genre"),rs.getInt("room_id"),rs.getString("room_name"),rs.getDate("date"),rs.getString("taken_seats"),rs.getInt("total_seats"));
		return tmp;
	}
	int changeShow(Shows show) throws SQLException {	 
		return this.statement.executeUpdate("UPDATE Shows SET cinema_id ="+show.getCinema_id()+",movie_id = "+show.getMovie_id()+",room_id = "+show.getRoom_id()+",date = '" + show.getDate()+"', taken_seats = '"+show.getTaken_seats()+"' where show_id = "+show.getShow_id()+";"); 
	}
}
