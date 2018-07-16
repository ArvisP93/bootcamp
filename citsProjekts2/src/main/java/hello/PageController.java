package hello;

import java.sql.SQLException;
//import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
    @GetMapping("/reserveSeat")
    public String reserveSeat(@RequestParam(name="show_id", required=true, defaultValue="1") String show_id, @RequestParam(name="seat_id", required=true, defaultValue="1") String seat_id, Model model) throws ClassNotFoundException, SQLException{
    	db=new DatabaseController(database, username, password);
    	System.out.println("SHOWID:" + show_id + " SEATID:" + seat_id);
    	model.addAttribute("reserved", db.reserveSeat(show_id, seat_id));
    	return "reserveSeat";
    }
    
    @GetMapping("/addMovie")
    public String addMovie(Model model) {
    	model.addAttribute("movie", new Movies());
    	return "addMovie";
    }
    
    @PostMapping("/addMovie")
    public String adminAddMovie(@ModelAttribute Movies movie) throws ClassNotFoundException, SQLException {
    	System.out.println(movie.getName());
    	
    	//TODO Add in DB
     	db=new DatabaseController(database, username, password);
    	db.AddMovie(movie.getName(), movie.getGenre());
    	return "redirect:/getMovies";
    }
    
    @GetMapping("/adminAddData")
    public String adminAddData(Model model) {
    	model.addAttribute("cinema", new Cinemas());
    	return "adminAddData";
    }
    
    @PostMapping(value = "/adminAddData")
    public String processForm(@ModelAttribute Cinemas cinema) throws ClassNotFoundException, SQLException{
     	db=new DatabaseController(database, username, password);
    	db.AddCinema(cinema.getName(), cinema.getLatitude(), cinema.getLatitude());
     	System.out.println(cinema.getLatitude());
     	
     	return "redirect:/cinemaList";
    }
    
    @GetMapping("/addRoom")
    public String addRoom(Model model) throws ClassNotFoundException, SQLException {
    	db=new DatabaseController(database, username, password);
    	model.addAttribute("room", new Rooms());
        model.addAttribute("cinemas", db.getCinemas());
    	return "addRoom";
    }
 
    @PostMapping(value = "/addRoom")
    public String addRoom(@ModelAttribute Rooms room, Cinemas cinema) throws ClassNotFoundException, SQLException{
     	db=new DatabaseController(database, username, password);
    	db.AddRoom(room.getCinema_id(), room.getName(), room.getSeats());
    	System.out.println();
     	return "redirect:/addRoom";
    }
    @GetMapping("/showRooms")
    public String showRooms(@RequestParam(name="cinema_id", required=false, defaultValue="-1") int cinema_id, Model model) throws ClassNotFoundException, SQLException {
    	db=new DatabaseController(database,username,password);
    	model.addAttribute("rooms",db.getRooms(cinema_id));
    	model.addAttribute("cinemas",db.getCinemas());
        return "/showRooms";
    }
    
    @GetMapping("/showCinema")
    public String showCinema(@RequestParam(name="cinema_id",required=true) int cinema_id, Model model) throws SQLException, ClassNotFoundException {
    	db=new DatabaseController(database,username,password);
    	model.addAttribute("cinema",db.getCinemas(cinema_id));
    	return "showCinema";
    }
    @PostMapping("/changeCinema")
    public String changeCinema(Cinemas cinema) throws ClassNotFoundException, SQLException {
    	
    	db=new DatabaseController(database,username,password);
    	db.changeCinema(cinema);	
    	return "redirect:/showCinema?cinema_id="+cinema.getCinema_id();
    }
    @GetMapping("/showRoom")
    public String showRoom(@RequestParam(name="room_id",required=true) int room_id, Model model) throws SQLException, ClassNotFoundException {
    	db=new DatabaseController(database,username,password);
    	model.addAttribute("room",db.getRoom(room_id));
    	return "showRoom";
    }
    @PostMapping("/changeRoom")
    public String changeRoom(Rooms room) throws ClassNotFoundException, SQLException {
    	
    	db=new DatabaseController(database,username,password);
    	db.changeRoom(room);	
    	return "redirect:/showRoom?room_id="+room.getRoom_id();
    }
    @GetMapping("/showShow")
    public String showShow(@RequestParam(name="show_id",required=true) int show_id, Model model) throws SQLException, ClassNotFoundException {
    	db=new DatabaseController(database,username,password);
    	model.addAttribute("show",db.getShow(show_id));
    	return "showShow";
    }
    @PostMapping("/changeShow")
    public String changeShow(Shows show) throws ClassNotFoundException, SQLException {
    	
    	db=new DatabaseController(database,username,password);
    	db.changeShow(show);	
    	return "redirect:/showShow?show_id="+show.getShow_id();
    }
    
 
}
