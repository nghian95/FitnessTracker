package com.nghianguyen.FitnessTracker.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nghianguyen.FitnessTracker.model.User;
import com.nghianguyen.FitnessTracker.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository userRepository;
	
	public List<User> getAllUsers(){
		return userRepository.findAll();
	}
	
	public Optional<User> getUserByEmail(String email) {
		return userRepository.findById(email);
	}
	
	public void addUser(User user) {
		userRepository.save(user);
	}
	
	public void updateUser(String email, User user) {
		Optional<User> userData = userRepository.findById(email);
		
		if (userData.isPresent()) {
			User updatedUser = userData.get();
			updatedUser.setEmail(user.getEmail());
			updatedUser.setFirstName(user.getFirstName());
			updatedUser.setLastName(user.getLastName());
			updatedUser.setPassword(user.getPassword());
			updatedUser.setPhoneNumber(user.getPhoneNumber());
			userRepository.save(updatedUser);
		}
	}
	
	public void deleteUserByEmail(String email) {
		userRepository.deleteById(email);
	}
	
	public void deleteAllUsers() {
		userRepository.deleteAll();
	}
}
