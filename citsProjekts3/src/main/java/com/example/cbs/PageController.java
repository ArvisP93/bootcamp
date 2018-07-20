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
	
    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) throws ClassNotFoundException, SQLException {
        model.addAttribute("name", name);        
        return "greeting";
    }
    @GetMapping("/admin")
    public String greeting(Model model) throws ClassNotFoundException, SQLException {      
        return "admin";
    }
    
    @GetMapping("/getMovies")
    public String getMovies(Model model) throws ClassNotFoundException, SQLException {
    	
    	db=new DatabaseController(database, username, password);
    	
        model.addAttribute("filmas", db.getFilmas());
        return "getMovies";
    }
    @GetMapping("/index1")
    public String index1(Model model) throws ClassNotFoundException, SQLException {
    	
    	db=new DatabaseController(database, username, password);
    	
        model.addAttribute("cinemas", db.getCinemas());
        //model.addAttribute("filmas", db.getShows());
        return "index1";
    }
    
    @GetMapping("/cinemaList")
    public String cinemaList(Model model) throws ClassNotFoundException, SQLException{
    	db=new DatabaseController(database, username, password);
    	model.addAttribute("cinemas", db.getCinemas());
    	return "cinemaList";
    }
    
    @GetMapping("/getShows")
    public String getShows(@RequestParam(name="longitude", required=false, defaultValue="none") String longitude,@RequestParam(name="latitude", required=false, defaultValue="none") String latitude,@RequestParam(name="cinema", required=false, defaultValue="1") String cinema, Model model) throws ClassNotFoundException, SQLException{
    	db=new DatabaseController(database, username, password);
    	if(!latitude.equals("none") && !longitude.equals("none"))
    	{
    		cinema=String.valueOf(db.FindClosest(Double.parseDouble(latitude), Double.parseDouble(longitude)));
    	}
    	model.addAttribute("shows", db.getShows(cinema));
    	model.addAttribute("cinema", cinema);
    	//model.addAttribute("tuvakaisKino", db.FindClosest(1.1, 1.1));
    	return "getShows";
    }
    @GetMapping("/getShow")
    public String getShow(@RequestParam(name="show_id", required=true, defaultValue="1") String show_id, Model model) throws ClassNotFoundException, SQLException{
    	db=new DatabaseController(database, username, password);
    	model.addAttribute("showSeats", db.getShowSeats(show_id));
    	return "getShow";
    }
    /*@GetMapping("/reserveSeat")
    public String reserveSeat(@RequestParam(name="show_id", required=true, defaultValue="1") String show_id, @RequestParam(name="seat_id", required=true, defaultValue="1") String seat_id, Model model) throws ClassNotFoundException, SQLException{
    	db=new DatabaseController(database, username, password);
    	System.out.println("SHOWID:" + show_id + " SEATID:" + seat_id);
    	model.addAttribute("reserved", db.reserveSeat(show_id, seat_id));
    	return "reserveSeat";
    }*/
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
    
    /*@GetMapping("/addMovie")
    public String addMovie(Model model) {
    	model.addAttribute("movie", new Movies());
    	return "addMovie";
    }
    */
    /*@PostMapping("/addMovie")
    public String adminAddMovie(@ModelAttribute Movies movie) throws ClassNotFoundException, SQLException {
    	System.out.println(movie.getName());
    	
    	//TODO Add in DB
     	db=new DatabaseController(database, username, password);
    	db.AddMovie(movie.getName(), movie.getGenre());
    	return "redirect:/getMovies";
    }*/
    

    @GetMapping("/getRooms")
    public String getRooms(@RequestParam(name="cinemaID", required=true, defaultValue="1") String cinemaID, Model model) throws ClassNotFoundException, SQLException{
    	db=new DatabaseController(database,username,password);
    	model.addAttribute("cinemas", db.getCinemas());
    	model.addAttribute("rooms", db.getRooms(Integer.parseInt(cinemaID)));
    	return "getRooms";
    }

    @PostMapping(value = "/getRooms")
    public String getAdminRooms(@RequestParam(name="cinemaID", required=true, defaultValue="1") String cinemaID, Model model) throws ClassNotFoundException, SQLException{
    	db=new DatabaseController(database,username,password);
       	model.addAttribute("cinemas", db.getCinemas());
    	model.addAttribute("rooms", db.getRooms(Integer.parseInt(cinemaID)));
     	return "/getRooms";
    }
    
    
    
    @GetMapping("/addMovie")
    public String addMovie(Model model) {
    	model.addAttribute("movie", new Movies());
    	return "addMovie";
    }
    
    @PostMapping("/addMovie")
    public String adminAddMovie(@ModelAttribute Movies movie) throws ClassNotFoundException, SQLException {
    	db=new DatabaseController(database,username,password);
    	db.AddMovie(movie.getName(), movie.getGenre());
    	return "redirect:/getMovies";
    }
    
    @GetMapping("/addCinema")
    public String addCinema(Model model) {
    	model.addAttribute("cinema", new Cinemas());
    	return "addCinema";
    }
    
    @PostMapping(value = "/addCinema")
    public String adminAddCinema(@ModelAttribute Cinemas cinema) throws ClassNotFoundException, SQLException{
     	
    	db=new DatabaseController(database,username,password);
    	db.AddCinema(cinema.getName(), cinema.getLatitude(), cinema.getLatitude());
     	
     	return "redirect:/cinemaList";
    }

    @GetMapping("/addRoom")
    public String addRoom(Model model) throws ClassNotFoundException, SQLException {
    	db=new DatabaseController(database,username,password);
    	model.addAttribute("room", new Rooms());
        model.addAttribute("cinemas", db.getCinemas());
    	return "addRoom";
    }
 
    @PostMapping(value = "/addRoom")
    public String adminAddRoom(@ModelAttribute Rooms room) throws ClassNotFoundException, SQLException{
    	//System.out.println(selectedCinema.getCinema_id());
    	db=new DatabaseController(database,username,password);
    	db.AddRoom(room.getCinema_id(), room.getName(), room.getSeats());
     	return "redirect:/addRoom";
    }
    @GetMapping("/showRooms")
    public String showRooms(@RequestParam(name="cinema_id", required=false, defaultValue="-1") int cinema_id, Model model) throws ClassNotFoundException, SQLException {
    	db=new DatabaseController(database,username,password);
    	model.addAttribute("rooms",db.getRooms(cinema_id));
    	model.addAttribute("cinemas",db.getCinemas());
        return "showRooms";
    }
    @GetMapping("/deleteCinema")
    public String deleteCinema(Model model) throws ClassNotFoundException, SQLException {
    	db=new DatabaseController(database,username,password);
        model.addAttribute("cinemas", db.getCinemas());
    	return "deleteCinema";
    }
 
    @PostMapping(value = "/deleteCinema")
    public String adminDeleteCinema(@ModelAttribute Cinemas cinema, String selectCinema) throws ClassNotFoundException, SQLException{
    	System.out.println(Integer.parseInt(selectCinema));
    	db=new DatabaseController(database,username,password);
    	db.DeleteCinema(Integer.parseInt(selectCinema));
     	return "redirect:/cinemaList";
    }
    
    @GetMapping("/deleteMovie")
    public String deleteMovie(Model model) throws ClassNotFoundException, SQLException {
    	db=new DatabaseController(database,username,password);
        model.addAttribute("movies", db.getFilmas());
    	return "deleteMovie";
    }
 
    @PostMapping(value = "/deleteMovie")
    public String adminDeleteMovie(@ModelAttribute Cinemas cinema, String selectMovie) throws ClassNotFoundException, SQLException{
    	System.out.println(Integer.parseInt(selectMovie));
    	db=new DatabaseController(database,username,password);
    	db.DeleteMovie(Integer.parseInt(selectMovie));
     	return "redirect:/getMovies";
    }


    
	//Made by TM starts here - 16.07
    @GetMapping("/editCinema")
    public String showCinema(@RequestParam(name="cinema_id",required=true) int cinema_id, Model model) throws SQLException, ClassNotFoundException {
    	db=new DatabaseController(database,username,password);
    	model.addAttribute("cinema",db.getCinema(cinema_id));
    	return "editCinema";
    }
    @PostMapping("/changeCinema")
    public String changeCinema(Cinemas cinema) throws ClassNotFoundException, SQLException {
    	
    	db=new DatabaseController(database,username,password);
    	db.changeCinema(cinema);	
    	return "redirect:/editCinema?cinema_id="+cinema.getCinema_id();
    }
    @GetMapping("/editRoom")
    public String showRoom(@RequestParam(name="room_id",required=true) int room_id, Model model) throws SQLException, ClassNotFoundException {
    	db=new DatabaseController(database,username,password);
    	model.addAttribute("room",db.getRoom(room_id));
    	return "editRoom";
    }
    @PostMapping("/changeRoom")
    public String changeRoom(Rooms room) throws ClassNotFoundException, SQLException {
    	
    	db=new DatabaseController(database,username,password);
    	db.changeRoom(room);
    	return "redirect:/editRoom?room_id="+room.getRoom_id();
}
    @GetMapping("/editShow")
    public String showShow(@RequestParam(name="show_id",required=true) int show_id, Model model) throws SQLException, ClassNotFoundException {
    	db=new DatabaseController(database,username,password);
    	model.addAttribute("show",db.getShow(show_id));
    	return "editShow";
}
    @PostMapping("/changeShow")
    public String changeShow(Shows show) throws ClassNotFoundException, SQLException {
    	
    	db=new DatabaseController(database,username,password);
    	db.changeShow(show);	
    	return "redirect:/editShow?show_id="+show.getShow_id();
    }
    
    
    
    @GetMapping("/editMovie")
    public String showMovie(@RequestParam(name="movie_id",required=true) int movie_id, Model model) throws SQLException, ClassNotFoundException {
    	db=new DatabaseController(database,username,password);
    	model.addAttribute("movie",db.getMovie(movie_id));
    	return "editMovie";
    }
    @PostMapping("/changeMovie")
    public String changeMovie(Movies movie) throws ClassNotFoundException, SQLException {
    	
    	db=new DatabaseController(database,username,password);
    	db.changeMovie(movie);	
    	return "redirect:/editMovie?movie_id="+movie.getMovie_id();
    }
    
//Made by TM ends here - 16.07

    @GetMapping("/deleteRoom")
    public String deleteRoom(@RequestParam(name="cinemaID", required=true, defaultValue="1") String cinemaID, Cinemas cinema, Model model) throws ClassNotFoundException, SQLException {
    	db=new DatabaseController(database,username,password);
      	model.addAttribute("cinemas", db.getCinemas());
        model.addAttribute("rooms", db.getRooms(Integer.parseInt(cinemaID)));
    	return "deleteRoom";
    }
    @PostMapping(value = "/deleteRoom")
    public String adminDeleteRoom(@RequestParam(name="cinemaID", required=true, defaultValue="1") String cinemaID, @RequestParam(name="deleteRoom", required=true, defaultValue="0") String deleteRoom, Cinemas cinema, Model model) throws ClassNotFoundException, SQLException{  	
    		db=new DatabaseController(database,username,password);
        	model.addAttribute("cinemas", db.getCinemas());
        	model.addAttribute("rooms", db.getRooms(Integer.parseInt(cinemaID)));
    	if (Integer.parseInt(deleteRoom) != 0) {
    		db.DeleteRoom(Integer.parseInt(deleteRoom));
    		return "redirect:/deleteRoom";
    		
    	}
     	return "/deleteRoom";
    	
    }
    @GetMapping("/AddShowTM")
    public String AddShowTM(Model model) throws ClassNotFoundException, SQLException {
    	db=new DatabaseController(database,username,password);
    	model.addAttribute("show", new Shows());
        model.addAttribute("rooms", db.getRooms(-1));
    	return "AddShowTM";
    }
    @PostMapping("/AddShowTM")
    public String AddShowTM(Shows show) throws ClassNotFoundException, SQLException {
    	db=new DatabaseController(database,username,password);
    	db.AddNewShow(show.getMovie_id(), show.getRoom_id(),show.getDate());
    	return "redirect:/admShowShows";
    }
    //***AP 18.07.18***
    @GetMapping("/addShow")
    public String addShow(Model model) throws ClassNotFoundException, SQLException {
    	db=new DatabaseController(database,username,password);
        model.addAttribute("cinemas", db.getCinemas());
    	return "addShow";
    }
    
   
    @PostMapping(value = "/addShow")
    public String adminAddShow(@ModelAttribute("selectCinema") String selectCinema, Model model) throws ClassNotFoundException, SQLException{
    	db=new DatabaseController(database,username,password);
    	model.addAttribute("rooms", db.getRooms(Integer.parseInt(selectCinema)));
    	
    	model.addAttribute("cinema", db.getCinema(Integer.parseInt(selectCinema)));
    	
    	model.addAttribute("movies", db.getFilmas());
     	return "addShow";
    }
    
    @PostMapping(value = "/addShowFinal")
    public String addShowFinal(@ModelAttribute("selectCinema") String selectCinema,
    		@ModelAttribute("selectHour") String selectHour, 
    		@ModelAttribute("selectMinute") String selectMinute, 
    		@ModelAttribute("selectDate") Date selectDate, 
    		@ModelAttribute("selectMovie") String selectMovie, 
    		@ModelAttribute("selectRoom") String selectRoom, Model model) throws ClassNotFoundException, SQLException{
    	db=new DatabaseController(database,username,password);
    	//model.addAttribute("rooms", db.getRooms(Integer.parseInt(selectCinema)));
    	
    	//model.addAttribute("cinema", db.getCinema(Integer.parseInt(selectCinema)));
    	System.out.println("DATE: " + selectDate.toString());
    	System.out.println("selectCinema: " + selectCinema);
    	String showDate = selectDate.toString() + " " + selectHour + ":" + selectMinute + ":00";
    	db.AddShowString(Integer.parseInt(selectCinema), Integer.parseInt(selectMovie), Integer.parseInt(selectRoom), showDate, "");
    	//model.addAttribute("movies", db.getFilmas());
     	return "redirect:/getShows?cinema=" + selectCinema;
    }
  //***AP 18.07.18 END***
    
    @GetMapping("/deleteShow")
    public String deleteShow(@RequestParam(name="cinema_id", required=true, defaultValue="1") String cinema_id, Cinemas cinema, Model model) throws ClassNotFoundException, SQLException {
    	db=new DatabaseController(database,username,password);
      	model.addAttribute("cinemas", db.getCinemas());
        model.addAttribute("shows", db.getShows((cinema_id)));

        model.addAttribute("cinema_id", cinema_id);
    	return "deleteShow";
    }
    @PostMapping("/deleteShow")
    public String adminDeleteShow( @ModelAttribute("cinemaID") String cinemaID, @RequestParam(name="deleteShow", required=true, defaultValue="0") String deleteShow, Cinemas cinema, Model model) throws ClassNotFoundException, SQLException{  	
    	db=new DatabaseController(database,username,password);
          	model.addAttribute("cinemas", db.getCinemas());
            model.addAttribute("shows", db.getShows((cinemaID)));

    	if (Integer.parseInt(deleteShow) != 0) {
    		db.DeleteShow(Integer.parseInt(deleteShow));
    		return "redirect:/deleteShow?cinema_id="+cinemaID;
    		
    	}
    	return "redirect:/deleteShow?cinema_id="+cinemaID;
    	
    }
    
    //ADM edit links
    @GetMapping("/admShowRooms")
    public String AdmShowRooms(@RequestParam(name="cinema_id", required=false, defaultValue="-1") int cinema_id, Model model) throws ClassNotFoundException, SQLException {//written by TM
    	db=new DatabaseController(database,username,password);
    	model.addAttribute("rooms",db.getRooms(cinema_id));
    	model.addAttribute("cinemas",db.getCinemas());
        return "admShowRooms";
    }
    @GetMapping("/admShowMovies")
    public String AdmShowMovies(Model model) throws ClassNotFoundException, SQLException {//written by TM
    	db=new DatabaseController(database,username,password);
    	model.addAttribute("movies",db.getFilmas());
        return "admShowMovies";
    }
    @GetMapping("/admShowCinemas")
    public String AdmShowCinemas(Model model) throws ClassNotFoundException, SQLException {//written by TM
    	db=new DatabaseController(database,username,password);
    	model.addAttribute("cinemas",db.getCinemas());
        return "admShowCinemas";
    }
    @GetMapping("/admShowShows")
    public String AdmShowShows(Model model) throws ClassNotFoundException, SQLException {//written by TM
    	db=new DatabaseController(database,username,password);
    	model.addAttribute("shows",db.getShows("-1"));
        return "admShowShows";
    }
    @GetMapping("/admShowUsers")
    public String AdmShowUsers(Model model) throws ClassNotFoundException, SQLException {//written by TM
    	db=new DatabaseController(database,username,password);
    	model.addAttribute("users",db.getUsers());
        return "admShowUsers";
    }
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
    	//model.addAttribute("usernames", db.getUsernames());
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
    
    @GetMapping("/deleteUser")
    public String deleteUser(Model model) throws ClassNotFoundException, SQLException {
    	db=new DatabaseController(database,username,password);
        model.addAttribute("users", db.getUsers());
    	return "deleteUser";
    }
 
}
