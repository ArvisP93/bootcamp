package com.example.demo.cbs;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.ArrayList;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    @Autowired
	static DataSource dataSource = new DataSource() 
    
    {
		
		@Override
		public <T> T unwrap(Class<T> iface) throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public boolean isWrapperFor(Class<?> iface) throws SQLException {
			// TODO Auto-generated method stub
			return false;
		}
		
		@Override
		public void setLoginTimeout(int seconds) throws SQLException {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void setLogWriter(PrintWriter out) throws SQLException {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public Logger getParentLogger() throws SQLFeatureNotSupportedException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public int getLoginTimeout() throws SQLException {
			// TODO Auto-generated method stub
			return 0;
		}
		
		@Override
		public PrintWriter getLogWriter() throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public Connection getConnection(String username, String password) throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public Connection getConnection() throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}
	};

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        DbConnect cdb = new DbConnect();
        ArrayList<String> stuff = new ArrayList<String>();
        try {
        	stuff = cdb.GetCinemasRooms(1,1); //TODO: need to get removed, just for testing
        	System.out.println("Rooms in Cinemas by cinema and room id" + stuff);
        	cdb.ChangePass("Tsunami1","geese");
        	stuff = cdb.GetUser(1);
			System.out.println("User by their ID"+stuff);
        	stuff = cdb.GetMovie(1);
        	System.out.println("Movie by its ID"+stuff);
        	cdb.ChangePass(1,"ducks");
        	stuff = cdb.GetUser("Tsunami1");
        	System.out.println("User by their nickname"+stuff);
        	stuff = cdb.GetShow(1);
        	System.out.println("Show by its id"+stuff);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
    }

}