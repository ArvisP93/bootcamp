package com.example.cbs;

import java.sql.SQLException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SendMail {
    
	public static DatabaseController db;
	public static final String username="springuser",password="parole",database="db_cbs";
	
	public static void sendMail(String email, String show_id, String seat_id) throws NumberFormatException, SQLException, ClassNotFoundException {
	ApplicationContext context = 
            new ClassPathXmlApplicationContext("Spring-Mail.xml");
	
	db=new DatabaseController(database, username, password);
	
	String message = "Hello! You have booked a seat with number: " + seat_id 
					+ " in the cinema: " + db.getShowsByID(Integer.parseInt(show_id)).getCinema_name() +
					" for a movie: " + db.getShowsByID(Integer.parseInt(show_id)).getMovie_name() + 
					" at: " + db.getShowsByID(Integer.parseInt(show_id)).getDate();
	
	
   	MailMail mm = (MailMail) context.getBean("mailMail");//from, to, subject, text.
       mm.sendMail("booking@cbs.com",
   		   email,
   		   "You've booked a ticket!", 
   		   message);
	}
}
