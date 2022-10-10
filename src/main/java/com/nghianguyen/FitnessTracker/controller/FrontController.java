package com.nghianguyen.FitnessTracker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FrontController {
	@GetMapping("/index")
	public String getIndex() {
		return "index";
	}
	
    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }
    
    @GetMapping("/user")
    public String userIndex() {
        return "user/index";
    }
}
