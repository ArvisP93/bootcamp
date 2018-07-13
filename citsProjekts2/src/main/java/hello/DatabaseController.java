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
	
}
