package com.example.demo.cbs;

import java.sql.*;
import java.util.ArrayList;

public class DbConnect { //Copied from sample, needs work to make usable for this
	
	private Connection connect = null;
	private Statement statement = null;
	private ResultSet resultSet = null;
	//private static final Logger log = LoggerFactory.getLogger(Application.class);

	private void Connect(){
		try {
		Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			//TODO: Log: no drivers?
		}
        // Setup the connection with the DB
        try {
			connect = DriverManager.getConnection("jdbc:mysql://localhost/db_cbs?user=javadm&password=Pass1234");
	        statement = connect.createStatement();
		} catch (SQLException e) {
			//TODO: Log: failed to connect to DB.
		}
        //CREATE USER 'javadm'@'%' IDENTIFIED BY 'Pass1234';
        //GRANT ALL PRIVILEGES ON db_cbs.* TO 'javadm'@'%';
	}
	public ArrayList<String> GetCinemasRooms(int cinema_id, int room_id) throws SQLException{
            // This will load the MySQL driver, each DB has its own driver
		 	Connect();
            // Result set get the result of the SQL query 
            resultSet = statement
                    .executeQuery("select * from Cinema_rooms where cinema_id = " + Integer.toString(cinema_id) + " and room_id = " + Integer.toString(room_id) + ";"); 
//need to CREATE VIEW Cinema_rooms AS SELECT Cinemas.*, Rooms.room_id, Rooms.name as room_name, Rooms.seats FROM Cinemas JOIN Rooms WHERE Cinemas.cinema_id=Rooms.cinema_id;
//on the main sql server
            ArrayList<String> res = new ArrayList<String>();
            
            resultSet.next();
            String name = resultSet.getString("name");
            String latitude = resultSet.getString("latitude");
            String longitude = resultSet.getString("longitude");
            String seats = resultSet.getString("seats");
            res.add(Integer.toString(cinema_id));
            res.add(name);
            res.add(latitude);
            res.add(longitude);
            res.add(Integer.toString(room_id));
            res.add(seats);
            
            
            /*
            log.info("data taken from mysql");
			log.info("-------------------------------");
			log.info(cinema_id);
			log.info(name);
			log.info(latitude);
			log.info(longitude);
			log.info(room_id);
			log.info(seats);
			log.info("");
			*/
    		/*int i=1;
    		while(resultSet.next()) {
    			res.add(resultSet.getString(i));
    			i++;
    		}
    		*/
    		close();
    		return res;
    }    
	public ArrayList<String> GetUser(int user_id) throws SQLException{
		ArrayList<String> res = new ArrayList<String>();
		Connect();
		resultSet = statement
                 .executeQuery("SELECT user_id, username, password, role FROM Users where user_id = " + Integer.toString(user_id) + ";");
		/*int i=1;
		while(resultSet.next()) {
			res.add(resultSet.getString(i));
			i++;
		}
		*/
		resultSet.next();
		String username = resultSet.getString("username");
		String password = resultSet.getString("password");
		String role = resultSet.getString("role");
		res.add(Integer.toString(user_id));
		res.add(username);
		res.add(password);
		res.add(role);
		close();
		return res;
	}
	public ArrayList<String> GetUser(String username) throws SQLException{
		ArrayList<String> res = new ArrayList<String>();
		Connect();
		resultSet = statement
                 .executeQuery("SELECT user_id, username, password, role FROM Users where username = '" + username + "';");
		
		resultSet.next();
		String user_id = resultSet.getString("user_id");
		String password = resultSet.getString("password");
		String role = resultSet.getString("role");
		res.add(user_id);
		res.add(username);
		res.add(password);
		res.add(role);
		/*
		int i=1;
		while(resultSet.next()) {
			res.add(resultSet.getString(i));
			i++;
		}
		*/
		close();
		return res;
	}
	public ArrayList<String> GetMovie(int movie_id) throws SQLException{
		ArrayList<String> res = new ArrayList<String>();
		Connect();
		resultSet = statement
                 .executeQuery("SELECT movie_id, name, genre FROM Movies where movie_id = " + Integer.toString(movie_id) + ";");
		resultSet.next();
		String name = resultSet.getString("name");
		String genre = resultSet.getString("genre");
		res.add(Integer.toString(movie_id));
		res.add(name);
		res.add(genre);
		/*
		int i=1;
		while(resultSet.next()) {
			res.add(resultSet.getString(i));
			i++;
		}
		*/
		close();
		return res;
		
	}
	public ArrayList<String> GetShow(int show_id) throws SQLException{
		ArrayList<String> res = new ArrayList<String>();
		Connect();
		//CREATE VIEW Show_movies AS SELECT sh.show_id, mv.*,  sh.room_id, sh.date, sh.taken_seats FROM Movies mv JOIN Shows sh WHERE mv.movie_id = sh.movie_id;
		//CREATE VIEW Show_info AS SELECT cr.cinema_id, cr.name AS cinema_name, cr.room_name, sm.*, cr.seats AS total_seats FROM Cinema_rooms cr JOIN Show_movies sm WHERE cr.room_id = sm.room_id;
		// as well as the view in GetCinemasRooms()
		resultSet = statement
                .executeQuery("SELECT cinema_id, cinema_name, name, genre, room_id, room_name, date, taken_seats, total_seats from Show_info where show_id = " + Integer.toString(show_id) + ";");
		resultSet.next();
		String cinema_id = resultSet.getString("cinema_id");
		String cinema_name = resultSet.getString("cinema_name");
		String title = resultSet.getString("name");
		String genre = resultSet.getString("genre");
		String room_id = resultSet.getString("room_id");
		String room_name = resultSet.getString("room_name");
		String date = resultSet.getString("date");
		String taken_seats = resultSet.getString("taken_seats");
		String total_seats = resultSet.getString("total_seats");
		res.add(Integer.toString(show_id));
		res.add(cinema_id);
		res.add(cinema_name);
		res.add(title);
		res.add(genre);
		res.add(room_id);
		res.add(room_name);
		res.add(date);
		res.add(taken_seats);
		res.add(total_seats);
		
		/*
		int i=0;
		while(resultSet.next()) {
			i++;
			res.add(resultSet.getString(i));
			
		}
		*/
		close();
		return res;
	}
	public void ChangePass(int user_id, String newpass) throws SQLException {
		Connect();
		statement.executeUpdate("UPDATE Users SET password = + '"+newpass+"' where user_id = " + Integer.toString(user_id) + ";");
		close();
	}
	public void ChangePass(String username, String newpass) throws SQLException {
		Connect();
		statement.executeUpdate("UPDATE Users SET password = + '"+newpass+"' where username = '" + username + "';");
		close();
	}
	
	
	/*
	public void Connect(boolean IsAdmin) throws SQLException {
        if (IsAdmin) {
            connect = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/db_cbs?"
                            + "user=javadm&password=Pass1234;autoReconnect=true");
	
        } else {
        	connect = DriverManager
        			.getConnection("jdbc:mysql://localhost:3306/db_cbs?"
        					+ "user=java&password=Pass1234;autoReconnect=true");
        } 
	} 
	*/
	
//CREATE USER 'java'@'%' IDENTIFIED BY 'Pass1234';
//GRANT SELECT ON db_cbs.* TO 'java'@'%';
	
	
    private void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (connect != null) {
                connect.close();
            }
        } catch (Exception e) {

        }
    }
	
}
