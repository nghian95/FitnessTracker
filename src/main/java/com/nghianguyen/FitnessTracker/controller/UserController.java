package com.nghianguyen.FitnessTracker.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nghianguyen.FitnessTracker.model.User;
import com.nghianguyen.FitnessTracker.service.UserService;

@RestController
public class UserController {
	@Autowired
	UserService userService;
	
	@GetMapping("/user/{email}")
	public Optional<User> getUserByID(@PathVariable("email") String email) {
		return userService.getUserByEmail(email);
	}
	
	@GetMapping("/user")
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}
	
	@PostMapping("/user")
	public void addUser(@RequestBody User user) {
		userService.addUser(user);
	}
	
	@PutMapping("/user/{email}")
	public void updateUser(@PathVariable("email") String email, @RequestBody User user) {
		userService.updateUser(email, user);
	}
	
	@DeleteMapping("/user")
	public void deleteAllUsers() {
		userService.deleteAllUsers();
	}
	
	@DeleteMapping("/user/{email}")
	public void deleteUserByID(@PathVariable("email") String email) {
		userService.deleteUserByEmail(email);
	}
}
