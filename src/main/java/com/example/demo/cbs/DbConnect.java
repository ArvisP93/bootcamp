package com.example.demo.cbs;

import java.sql.*;
import java.util.ArrayList;

public class DbConnect { //Copied from sample, needs work to make usable for this
	
	private Connection connect = null;
	private Statement statement = null;
	private ResultSet resultSet = null;
	//private static final Logger log = LoggerFactory.getLogger(Application.class);

	private void Connect() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
        // Setup the connection with the DB
        connect = DriverManager.getConnection("jdbc:mysql://localhost/db_cbs?user=java&password=Pass1234");
        //CREATE USER 'javadm'@'%' IDENTIFIED BY 'Pass1234';
        //GRANT ALL PRIVILEGES ON db_cbs.* TO 'javadm'@'%';
        statement = connect.createStatement();
	}
	public ArrayList<String> GetMovies(int cinema_id, int room_id) throws Exception {
            // This will load the MySQL driver, each DB has its own driver
		 	Connect();
            // Result set get the result of the SQL query 
            resultSet = statement
                    .executeQuery("select * from Cinema_rooms where cinema_id = " + Integer.toString(cinema_id) + " and room_id = " + Integer.toString(room_id) + ";"); 
//need to CREATE VIEW Cinema_rooms AS SELECT Cinemas.*, Rooms.room_id, Rooms.seats FROM Cinemas JOIN Rooms WHERE Cinemas.cinema_id=Rooms.cinema_id;
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
            
			close(); 
			return res;
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
