package com.example.demo.cbs;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SeatingController {
	@Autowired
	DataSource dataSource;
    @GetMapping("/cinema")
    public String seating(@RequestParam(name="id", required=true) long cinemaID, Model model) {
        model.addAttribute("cinema", cinemaID);
        DbConnect cdb = new DbConnect();
        try {
			//
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return "greeting";
    }
}
