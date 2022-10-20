package com.nghianguyen.fitnesstracker.controller;

import java.security.Principal;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.nghianguyen.fitnesstracker.model.Location;
import com.nghianguyen.fitnesstracker.model.User;
import com.nghianguyen.fitnesstracker.security.UserRegistrationDto;
import com.nghianguyen.fitnesstracker.service.UserServiceImpl;
/*
 * Controller for Registering a new User.
 */
@Controller
public class UserController {
	
	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	UserServiceImpl userService;
	
//	@Autowired
//	LocationService locationService;
	
	@ModelAttribute("user")
	public UserRegistrationDto userRegistrationDto() {
		return new UserRegistrationDto();
	}
	
	@GetMapping("/profile")
	public String getUserByID(Model model, Principal principal) {	
		String username = principal.getName();
		User user = userService.findByEmail(username).get();
		for (Location location : user.getLocations()) {
			System.out.println(location);
		}
		model.addAttribute("user", user);
//		List<Location> locations = locationService.
		return "user_profile";
	}
//	
//	@GetMapping("/user2")
//	public List<User> getAllUsers() {
//		return userService.getAllUsers();
//	}
//	
//	@PostMapping("/user2")
//	public void addUser(@RequestBody User user) {
//		userService.addUser(user);
//	}
//	
//	@PutMapping("/user2/{email}")
//	public void updateUser(@PathVariable("email") String email, @RequestBody User user) {
//		userService.updateUser(email, user);
//	}
//	
//	@DeleteMapping("/user2")
//	public void deleteAllUsers() {
//		userService.deleteAllUsers();
//	}
//	
//	@DeleteMapping("/user2/{email}")
//	public void deleteUserByID(@PathVariable("email") String email) {
//		userService.deleteUserByEmail(email);
//	}
	
	/*
	 * Returns the registration.html view page
	 */
	@GetMapping("/registration")
	public String showRegistrationForm(Model model) {
		return "registration";
	}

	/*
	 * The actual method that registers a User after the submission of the form. Form has duplicate fields for password
	 * and email so the @ModelAttribute is based off of UserRegistrationDto which has validation constraints that check
	 * if the form fields are valid. Also uses @FieldMatch to check if the two duplicate fields match one another.
	 */
	@PostMapping("/registration")
	public String registerUserAccount(@ModelAttribute("user") @Valid UserRegistrationDto userDto, BindingResult result){

		Optional<User> existingData = userService.findByEmail(userDto.getEmail());
		User existing = null;
		if (existingData.isPresent()) {
			existing = existingData.get();
		}
		
		if (existing != null){
			result.rejectValue("email", null, "There is already an account registered with that email");
		}
		
		if (result.hasErrors()){
			return "registration";
		}
		userService.save(userDto);
		String URL = "redirect:/registration?success";
       	return URL;
	}
}
