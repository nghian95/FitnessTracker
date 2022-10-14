package com.nghianguyen.FitnessTracker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FrontController {
	/*
	 * Home page. Default screen once user logs in.
	 */
	@GetMapping("/index")
	public String getIndex() {
		return "index";
	}
	
	/*
	 * Default screen user sees when loading the webpage for the first time. Either logs in or registers first.
	 */
    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }
    
//    @GetMapping("/user")
//    public String userIndex() {
//        return "user/index";
//    }

// 	@GetMapping("/nav")
// 	public String getNav() {
// 		return "header.html";
// 	}
}
