package com.nghianguyen.fitnesstracker.controller;

import java.security.Principal;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.nghianguyen.fitnesstracker.model.User;
import com.nghianguyen.fitnesstracker.service.UserServiceImpl;

@Controller
public class FrontController {
	
	private static final Logger log = LoggerFactory.getLogger(FrontController.class);

	@Autowired
	UserServiceImpl userServiceImpl;
	
	/*
	 * Home page. Default screen once user logs in.
	 */
	@GetMapping("/index")
	public String getIndex(Model model, Principal principal) {
		String userName = principal.getName();
		Optional<User> userData = userServiceImpl.findByEmail(userName);
		if (userData.isPresent()) {
			User user = userData.get();
			model.addAttribute("name", user.getFirstName());
		}
		return "index";
	}
	
	/*
	 * Default screen user sees when loading the webpage for the first time. Either logs in or registers first.
	 */
    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }
    
    /*
     * Redirects any localhost:8080/ webpage visits to the index mapping
     */
	@GetMapping("/")
	public String getIndex() {
		return "redirect:/index";
	}
    
//    @GetMapping("/invalidSession")
//    public String invalid(Model model) {
//        return "invalidSession";
//    }
//    @GetMapping("/user")
//    public String userIndex() {
//        return "user/index";
//    }

// 	@GetMapping("/nav")
// 	public String getNav() {
// 		return "header.html";
// 	}
}
