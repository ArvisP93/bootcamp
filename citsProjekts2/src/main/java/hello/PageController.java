package hello;

import java.sql.SQLException;
//import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PageController {
	
	public static DatabaseController db;
	
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
    	
    	db=new DatabaseController("db_cbs", "springuser", "parole");
    	
        model.addAttribute("filmas", db.getFilmas());
        return "getMovies";
    }
    @GetMapping("/index1")
    public String index1(Model model) throws ClassNotFoundException, SQLException {
    	
    	db=new DatabaseController("db_cbs", "springuser", "parole");
    	
        model.addAttribute("cinemas", db.getCinemas());
        //model.addAttribute("filmas", db.getShows());
        return "index1";
    }
    
    @GetMapping("/cinemaList")
    public String cinemaList(Model model) throws ClassNotFoundException, SQLException{
    	db=new DatabaseController("db_cbs", "springuser", "parole");
    	model.addAttribute("cinemas", db.getCinemas());
    	return "cinemaList";
    }
    
    @GetMapping("/getShows")
    public String getShows(@RequestParam(name="cinema", required=true, defaultValue="1") String cinema, Model model) throws ClassNotFoundException, SQLException{
    	db=new DatabaseController("db_cbs", "springuser", "parole");
    	model.addAttribute("shows", db.getShows(cinema));
    	model.addAttribute("cinema", cinema);
    	return "getShows";
    }
    @GetMapping("/getShow")
    public String getShow(@RequestParam(name="show_id", required=true, defaultValue="1") String show_id, Model model) throws ClassNotFoundException, SQLException{
    	db=new DatabaseController("db_cbs", "springuser", "parole");
    	model.addAttribute("show", db.getShow(show_id));
    	return "getShow";
    }
 
}
