package com.example.cbs;

import java.sql.Date;
import java.sql.SQLException;
//import java.util.ArrayList;
import java.util.ArrayList;
import java.util.regex.Pattern;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PageController {
	
	public static DatabaseController db;
	public static final String username="springuser",password="parole",database="db_cbs";
	
	//**************************** USER INTERACTION ****************************//  
	
    @GetMapping("/index1")
    public String index1(Model model) throws ClassNotFoundException, SQLException {
    	
    	db=new DatabaseController(database, username, password);
    	
        model.addAttribute("cinemas", db.getCinemas());
        return "index1";
    }
    
    @GetMapping("/cinemaList")
    public String cinemaList(Model model) throws ClassNotFoundException, SQLException{
    	db=new DatabaseController(database, username, password);
    	model.addAttribute("cinemas", db.getCinemas());
    	return "cinemaList";
    }
    
    @GetMapping("/getMovies")
    public String getMovies(Model model) throws ClassNotFoundException, SQLException {
    	
    	db=new DatabaseController(database, username, password);
    	
        model.addAttribute("filmas", db.getFilmas());
        return "getMovies";
    }
    
    @GetMapping("/getShows")
    public String getShows(@RequestParam(name="longitude", required=false, defaultValue="none") String longitude,@RequestParam(name="latitude", required=false, defaultValue="none") String latitude,@RequestParam(name="cinema", required=false, defaultValue="-1") String cinema, Model model) throws ClassNotFoundException, SQLException{
    	db=new DatabaseController(database, username, password);
    	if(!latitude.equals("none") && !longitude.equals("none"))
    	{
    		cinema=String.valueOf(db.FindClosest(Double.parseDouble(latitude), Double.parseDouble(longitude)));
    	}
    	model.addAttribute("shows", db.getShows(cinema));
    	model.addAttribute("cinema", cinema);
    	return "getShows";
    }
    @GetMapping("/getShow")
    public String getShow(@RequestParam(name="show_id", required=true, defaultValue="1") String show_id, Model model) throws ClassNotFoundException, SQLException{
    	db=new DatabaseController(database, username, password);
    	model.addAttribute("showSeats", db.getShowSeats(show_id));
    	return "getShow";
    }
    @GetMapping("/reserveSeat")
    public String reserveSeat(@RequestParam(name="show_id", required=true, defaultValue="1") String show_id, @RequestParam(name="seat_id", required=true, defaultValue="1") String seat_id, Model model) throws ClassNotFoundException, SQLException{
    	db=new DatabaseController(database, username, password);
    	System.out.println("SHOWID:" + show_id + " SEATID:" + seat_id);
    	model.addAttribute("reserved", db.reserveSeat(show_id, seat_id));
    	model.addAttribute("show_id", show_id);
    	model.addAttribute("seat_id", seat_id);
    	return "reserveSeat";
    }
    
    @PostMapping("/reserveSeat")
    public String mailtest(@ModelAttribute("email") String email, @ModelAttribute("seat_id") String seat_id, @ModelAttribute("show_id") String show_id) throws ClassNotFoundException, SQLException {
    	System.out.println("e: " + email);
    	System.out.println("show_id: " + show_id);
    	SendMail.sendMail(email, show_id, seat_id);
    	return "reserveSeat";
    }
    

    @GetMapping("/getRooms")
    public String getRooms(@RequestParam(name="cinemaID", required=true, defaultValue="1") String cinemaID, Model model) throws ClassNotFoundException, SQLException{
    	db=new DatabaseController(database, username, password);
    	model.addAttribute("cinemas", db.getCinemas());
    	model.addAttribute("rooms", db.getRooms(Integer.parseInt(cinemaID)));
    	return "getRooms";
    }

    @PostMapping(value = "/getRooms")
    public String getAdminRooms(@RequestParam(name="cinemaID", required=true, defaultValue="1") String cinemaID, Model model) throws ClassNotFoundException, SQLException{
    	db=new DatabaseController(database, username, password);
       	model.addAttribute("cinemas", db.getCinemas());
    	model.addAttribute("rooms", db.getRooms(Integer.parseInt(cinemaID)));
     	return "/getRooms";
    }
    
    @GetMapping("/showRooms")
    public String showRooms(@RequestParam(name="cinema_id", required=false, defaultValue="-1") int cinema_id, Model model) throws ClassNotFoundException, SQLException {
    	db=new DatabaseController(database,username,password);
    	model.addAttribute("rooms",db.getRooms(cinema_id));
    	model.addAttribute("cinemas",db.getCinemas());
        return "showRooms";
    }
	//**************************** END ****************************//   
    
    
	//**************************** USERS ****************************//   
    @GetMapping("/getUsers")
    public String getUsers(Model model) throws ClassNotFoundException, SQLException {//written by TM
    	db=new DatabaseController(database,username,password);
    	model.addAttribute("users",db.getUsers());
        return "getUsers";
    }
    @GetMapping("/registration")
    public String Registration(@RequestParam(name="error", required=false, defaultValue="") String error, Model model) throws ClassNotFoundException, SQLException {//written by TM
    	db=new DatabaseController(database,username,password);
    	model.addAttribute("user",new Users());
    	model.addAttribute("error",error);
        return "registration";
    }
    @PostMapping("/registration")
    public String Registration(Users user)throws ClassNotFoundException, SQLException {//written by TM
    	db=new DatabaseController(database,username,password);
    	final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[\\w._%+-]+@[\\w.-]+\\.[\\D]{2,6}$", Pattern.CASE_INSENSITIVE);
    	final Pattern VALID_USERNAME_REGEX = Pattern.compile("^[\\w._%+-]", Pattern.CASE_INSENSITIVE);
    	String error = "";
    	if (db.getUsernames().contains(user.getUsername())) {
    		error +="username";
    	} else if (!VALID_USERNAME_REGEX.matcher(user.getUsername()).find()){
    		error +="username";
    	}
    	if (!VALID_EMAIL_ADDRESS_REGEX.matcher(user.getEmail()).find()) {
    		error +="email";
    	}
    	
    	if (error == "") {
	    	db.AddUser(user.getUsername(),user.getPassword(),user.getEmail(),"user");
	    	return "redirect:userPage?user_id="+db.getUser(user.getUsername()).getUser_id();
    	} else {
    		return "redirect:registration?error="+error;
    	}
    }
    @GetMapping("/userPage")
    public String userPage(@RequestParam(name="user_id",required=true) int user_id, Model model)throws ClassNotFoundException, SQLException {//written by TM
    	db=new DatabaseController(database,username,password);
    	model.addAttribute("user",db.getUser(user_id));
    	return "userPage";
    }
	
    @GetMapping("/login")
    public String login(@RequestParam(name="error", required=false, defaultValue="") String error, Model model) throws ClassNotFoundException, SQLException {
    	db=new DatabaseController(database,username,password);
    	model.addAttribute("user",new Users());
    	model.addAttribute("error",error);
        return "login";
    }
    
    @PostMapping("/login")
    public String login(Users user)throws ClassNotFoundException, SQLException {
    	db=new DatabaseController(database,username,password);
    	String error = "";
    	if(db.getUsernames().contains(user.getUsername())!= true) {
    		error+="User does not exist";
    	}
    	if(error == "" && db.CheckPassword(user.getUsername(), user.getPassword())) {
    		if(db.isAdmin(user.getUsername())) {
    			return "redirect:admin";
    		}
    		else if(db.isAdmin(user.getUsername()) != true){
    			return "redirect:userPage?user_id="+db.getUser(user.getUsername()).getUser_id();
    		}
    		else
    			return "redirect:login?error="+error;
    	}
    	else {
    		return "redirect:login?error="+error;
    	}
    }
	//**************************** END ****************************//
    
  //Useless templates///
  //adminAddData;
  //changeCinema
  //greeting
  //showRoom
  //showShow   
    
}

