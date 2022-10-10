package com.nghianguyen.FitnessTracker.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.nghianguyen.FitnessTracker.model.User;
import com.nghianguyen.FitnessTracker.security.UserRegistrationDto;
import com.nghianguyen.FitnessTracker.service.UserServiceImpl;

@Controller
public class UserController {
	@Autowired
	UserServiceImpl userService;
	
	@ModelAttribute("user")
	public UserRegistrationDto userRegistrationDto() {
		return new UserRegistrationDto();
	}
	
	@GetMapping("/user2/{email}")
	public Optional<User> getUserByID(@PathVariable("email") String email) {
		return userService.getUserByEmail(email);
	}
	
	@GetMapping("/user2")
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}
	
	@PostMapping("/user2")
	public void addUser(@RequestBody User user) {
		userService.addUser(user);
	}
	
	@PutMapping("/user2/{email}")
	public void updateUser(@PathVariable("email") String email, @RequestBody User user) {
		userService.updateUser(email, user);
	}
	
	@DeleteMapping("/user2")
	public void deleteAllUsers() {
		userService.deleteAllUsers();
	}
	
	@DeleteMapping("/user2/{email}")
	public void deleteUserByID(@PathVariable("email") String email) {
		userService.deleteUserByEmail(email);
	}
	
	@GetMapping("/registration")
	public String showRegistrationForm(Model model) {
		return "registration";
	}

	@PostMapping("/registration")
	public String registerUserAccount(@ModelAttribute("user") @Valid UserRegistrationDto userDto, BindingResult result){

		User existing = userService.findByEmail(userDto.getEmail());
		if (existing != null){
			result.rejectValue("email", null, "There is already an account registered with that email");
		}

		if (result.hasErrors()){
			return "registration";
		}

		userService.save(userDto);
       	return "redirect:/registration?success";
	}
}
