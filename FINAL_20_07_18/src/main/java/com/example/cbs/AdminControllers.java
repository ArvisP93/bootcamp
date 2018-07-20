package com.example.cbs;

import java.sql.SQLException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminControllers {
    
	public static DatabaseController db;
	public static final String username="springuser",password="parole",database="db_cbs";
	
	//**************************** MAIN ****************************//
	
    @GetMapping("admin/main")
    public String greeting(Model model) throws ClassNotFoundException, SQLException {      
        return "admin/main";
    }
	//**************************** END ****************************//   
	
	//**************************** ADD DATA ****************************//
    @GetMapping("admin/addCinema")
    public String addCinema(Model model) {
    	model.addAttribute("cinema", new Cinemas());
    	return "admin/addCinema";
    }
    
    @PostMapping("admin/addCinema")
    public String addCinema(@ModelAttribute Cinemas cinema) throws ClassNotFoundException, SQLException{
    	db=new DatabaseController("db_cbs", "springuser", "parole");
    	db.AddCinema(cinema.getName(), cinema.getLatitude(), cinema.getLatitude());
     	return "redirect:/admin/admShowCinemas";
    } 
    
    @GetMapping("admin/addMovie")
    public String addMovie(Model model) {
    	model.addAttribute("movie", new Movies());
    	return "admin/addMovie";
    }
    
    @PostMapping("admin/addMovie")
    public String adminAddMovie(@ModelAttribute Movies movie) throws ClassNotFoundException, SQLException {
     	db=new DatabaseController("db_cbs", "springuser", "parole");
    	db.AddMovie(movie.getName(), movie.getGenre());
    	return "redirect:/admin/admShowMovies";
    }

    @GetMapping("admin/addRoom")
    public String addRoom(Model model) throws ClassNotFoundException, SQLException {
    	db=new DatabaseController("db_cbs", "springuser", "parole");
    	model.addAttribute("room", new Rooms());
        model.addAttribute("cinemas", db.getCinemas());
    	return "admin/addRoom";
    }
 
    @PostMapping(value = "admin/addRoom")
    public String adminAddRoom(@ModelAttribute Rooms room) throws ClassNotFoundException, SQLException{
    	db=new DatabaseController("db_cbs", "springuser", "parole");
    	db.AddRoom(room.getCinema_id(), room.getName(), room.getSeats());
     	return "redirect:/admin/admShowRooms";
    }
    
    @GetMapping("admin/addShow")
    public String addShow(Model model) throws ClassNotFoundException, SQLException {
    	db=new DatabaseController("db_cbs", "springuser", "parole");
        model.addAttribute("cinemas", db.getCinemas());
    	return "admin/addShow";
    }
    
    @PostMapping("admin/addShow")
    public String addShow(@ModelAttribute("selectCinema") String selectCinema, Model model) throws ClassNotFoundException, SQLException{
    	db=new DatabaseController("db_cbs", "springuser", "parole");
    	model.addAttribute("rooms", db.getRooms(Integer.parseInt(selectCinema)));
    	model.addAttribute("cinema", db.getCinema(Integer.parseInt(selectCinema)));
    	model.addAttribute("movies", db.getFilmas());
     	return "admin/addShow";
    }
    
    @PostMapping("addShowFinal")
    public String addShowFinal(@ModelAttribute("selectCinema") String selectCinema,
    		@ModelAttribute("selectHour") String selectHour, 
    		@ModelAttribute("selectMinute") String selectMinute, 
    		@ModelAttribute("selectDate") String selectDate, 
    		@ModelAttribute("selectMovie") String selectMovie, 
    		@ModelAttribute("selectRoom") String selectRoom, Model model) throws ClassNotFoundException, SQLException{
    	db=new DatabaseController("db_cbs", "springuser", "parole");
    	System.out.println("DATE: " + selectDate.toString());
    	System.out.println("selectCinema: " + selectCinema);
    	String showDate = selectDate + " " + selectHour + ":" + selectMinute + ":00";
    	db.AddShow(Integer.parseInt(selectCinema), Integer.parseInt(selectMovie), Integer.parseInt(selectRoom), showDate, "");
     	return "redirect:/getShows?cinema=" + selectCinema;
    }
    
	//**************************** END ****************************//
    
	//**************************** DELETE DATA ****************************//
    
    @GetMapping("admin/deleteCinema")
    public String deleteCinema(Model model) throws ClassNotFoundException, SQLException {
    	db=new DatabaseController("db_cbs", "springuser", "parole");
        model.addAttribute("cinemas", db.getCinemas());
    	return "admin/deleteCinema";
    }
 
    @PostMapping("admin/deleteCinema")
    public String adminDeleteCinema(@ModelAttribute Cinemas cinema, String selectCinema) throws ClassNotFoundException, SQLException{
    	db=new DatabaseController("db_cbs", "springuser", "parole");
    	db.DeleteCinema(Integer.parseInt(selectCinema));
     	return "redirect:/admin/admShowCinemas";
    }
    
    @GetMapping("admin/deleteMovie")
    public String deleteMovie(Model model) throws ClassNotFoundException, SQLException {
    	db=new DatabaseController("db_cbs", "springuser", "parole");
        model.addAttribute("movies", db.getFilmas());
    	return "admin/deleteMovie";
    }
    
    @PostMapping("admin/deleteMovie")
    public String adminDeleteMovie(@ModelAttribute Cinemas cinema, String selectMovie) throws ClassNotFoundException, SQLException{
    	db=new DatabaseController("db_cbs", "springuser", "parole");
    	db.DeleteMovie(Integer.parseInt(selectMovie));
     	return "redirect:/admin/admShowMovies";
    }
    
    @GetMapping("admin/deleteRoom")
    public String deleteRoom(@RequestParam(name="cinemaID", required=true, defaultValue="1") String cinemaID, Cinemas cinema, Model model) throws ClassNotFoundException, SQLException {
    	db=new DatabaseController("db_cbs", "springuser", "parole");
      	model.addAttribute("cinemas", db.getCinemas());
        model.addAttribute("rooms", db.getRooms(Integer.parseInt(cinemaID)));
    	return "admin/deleteRoom";
    }
    @PostMapping("admin/deleteRoom")
    public String adminDeleteRoom(@RequestParam(name="cinemaID", required=true, defaultValue="1") String cinemaID, @RequestParam(name="deleteRoom", required=true, defaultValue="0") String deleteRoom, Cinemas cinema, Model model) throws ClassNotFoundException, SQLException{  	
        	db=new DatabaseController("db_cbs", "springuser", "parole");
        	model.addAttribute("cinemas", db.getCinemas());
        	model.addAttribute("rooms", db.getRooms(Integer.parseInt(cinemaID)));
    	if (Integer.parseInt(deleteRoom) != 0) {
    		db.DeleteRoom(Integer.parseInt(deleteRoom));
    		return "redirect:/admin/admShowRooms";	
    	}
     	return "admin/deleteRoom";	
    }
    
    @GetMapping("admin/deleteShow")
    public String deleteShow(@RequestParam(name="cinema_id", required=true, defaultValue="1") String cinema_id, Cinemas cinema, Model model) throws ClassNotFoundException, SQLException {
    	db=new DatabaseController("db_cbs", "springuser", "parole");
      	model.addAttribute("cinemas", db.getCinemas());
        model.addAttribute("shows", db.getShows((cinema_id)));
        model.addAttribute("cinema_id", cinema_id);
    	return "admin/deleteShow";
    }
    @PostMapping("admin/deleteShow")
    public String adminDeleteShow( @ModelAttribute("cinemaID") String cinemaID, @RequestParam(name="deleteShow", required=true, defaultValue="0") String deleteShow, Cinemas cinema, Model model) throws ClassNotFoundException, SQLException{  	
        	db=new DatabaseController("db_cbs", "springuser", "parole");
          	model.addAttribute("cinemas", db.getCinemas());
            model.addAttribute("shows", db.getShows((cinemaID)));
    	if (Integer.parseInt(deleteShow) != 0) {
    		db.DeleteShow(Integer.parseInt(deleteShow));
    		return "redirect:/admin/deleteShow?cinema_id="+cinemaID;		
    	}
    	return "redirect:/admin/deleteShow?cinema_id="+cinemaID; 	
    }
    
    @GetMapping("admin/deleteUser")
    public String deleteUser(Model model) throws ClassNotFoundException, SQLException {
    	db=new DatabaseController(database,username,password);
        model.addAttribute("users", db.getUsers());
    	return "admin/deleteUser";
    }
 
	//**************************** END ****************************//

    
	//**************************** SHOW DATA ****************************//   
    @GetMapping("/admin/admShowRooms")
    public String AdmShowRooms(@RequestParam(name="cinema_id", required=false, defaultValue="-1") int cinema_id, Model model) throws ClassNotFoundException, SQLException {//written by TM
    	db=new DatabaseController(database,username,password);
    	model.addAttribute("rooms",db.getRooms(cinema_id));
    	model.addAttribute("cinemas",db.getCinemas());
        return "admin/admShowRooms";
    }
    @GetMapping("admin/admShowMovies")
    public String AdmShowMovies(Model model) throws ClassNotFoundException, SQLException {//written by TM
    	db=new DatabaseController(database,username,password);
    	model.addAttribute("movies",db.getFilmas());
        return "admin/admShowMovies";
    }
    @GetMapping("admin/admShowCinemas")
    public String AdmShowCinemas(Model model) throws ClassNotFoundException, SQLException {//written by TM
    	db=new DatabaseController(database,username,password);
    	model.addAttribute("cinemas",db.getCinemas());
        return "admin/admShowCinemas";
    }
    @GetMapping("admin/admShowShows")
    public String AdmShowShows(Model model) throws ClassNotFoundException, SQLException {//written by TM
    	db=new DatabaseController(database,username,password);
    	model.addAttribute("shows",db.getShows("-1"));
        return "admin/admShowShows";
    }
    @GetMapping("admin/admShowUsers")
    public String AdmShowUsers(Model model) throws ClassNotFoundException, SQLException {//written by TM
    	db=new DatabaseController(database,username,password);
    	model.addAttribute("users",db.getUsers());
        return "admin/admShowUsers";
    }
	//**************************** END ****************************//
    
	//**************************** EDIT DATA ****************************////
    
    @GetMapping("admin/editCinema")
    public String showCinema(@RequestParam(name="cinema_id",required=true) int cinema_id, Model model) throws SQLException, ClassNotFoundException {
    	db=new DatabaseController(database,username,password);
    	model.addAttribute("cinema",db.getCinema(cinema_id));
    	return "admin/editCinema";
    }
    @PostMapping("admin/changeCinema")
    public String changeCinema(Cinemas cinema) throws ClassNotFoundException, SQLException {	
    	db=new DatabaseController(database,username,password);
    	db.changeCinema(cinema);	
    	return "redirect:/admin/editCinema?cinema_id="+cinema.getCinema_id();
    }
    @GetMapping("admin/editRoom")
    public String showRoom(@RequestParam(name="room_id",required=true) int room_id, Model model) throws SQLException, ClassNotFoundException {
    	db=new DatabaseController(database,username,password);
    	model.addAttribute("room",db.getRoom(room_id));
    	return "admin/editRoom";
    }
    @PostMapping("admin/changeRoom")
    public String changeRoom(Rooms room) throws ClassNotFoundException, SQLException {
    	
    	db=new DatabaseController(database,username,password);
    	db.changeRoom(room);
    	return "redirect:/admin/editRoom?room_id="+room.getRoom_id();
}
    @GetMapping("admin/editShow")
    public String showShow(@RequestParam(name="show_id",required=true) int show_id, Model model) throws SQLException, ClassNotFoundException {
    	db=new DatabaseController(database,username,password);
    	model.addAttribute("show",db.getShow(show_id));
    	model.addAttribute("movies", db.getFilmas());
    	return "admin/editShow";
}
    @PostMapping("admin/changeShow")
    public String changeShow(Shows show) throws ClassNotFoundException, SQLException {
    	db=new DatabaseController(database,username,password);
    	db.changeShow(show);	
    	return "redirect:admin/showShow?show_id="+show.getShow_id();
    }
 
    @GetMapping("admin/editMovie")
    public String showMovie(@RequestParam(name="movie_id",required=true) int movie_id, Model model) throws SQLException, ClassNotFoundException {
    	db=new DatabaseController(database,username,password);
    	model.addAttribute("movie",db.getMovie(movie_id));
    	return "admin/editMovie";
    }
    @PostMapping("admin/changeMovie")
    public String changeMovie(Movies movie) throws ClassNotFoundException, SQLException {
    	
    	db=new DatabaseController(database,username,password);
    	db.changeMovie(movie);	
    	return "redirect:/admin/editMovie?movie_id="+movie.getMovie_id();
    }
    
    @GetMapping("admin/editUser")
    public String showUser(@RequestParam(name="user_id",required=true) int user_id, Model model) throws SQLException, ClassNotFoundException {
    	db=new DatabaseController(database,username,password);
    	model.addAttribute("user",db.getUser(user_id));
    	return "admin/editUser";
    }
    @PostMapping("admin/changeUser")
    public String changeUser(Users user) throws ClassNotFoundException, SQLException { //NEW ONE FROM TM 20.07
    	
    	db=new DatabaseController(database,username,password);
    	db.changeUser(user);	
    	return "redirect:editUser?user_id="+user.getUser_id();
    }
    
}
