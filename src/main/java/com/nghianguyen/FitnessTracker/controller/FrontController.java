package com.nghianguyen.FitnessTracker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FrontController {
	@GetMapping("/index")
	public String getIndex() {
		return "index";
	}
}
