package com.nghianguyen.FitnessTracker.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.nghianguyen.FitnessTracker.model.User;
import com.nghianguyen.FitnessTracker.security.UserRegistrationDto;

/*
 * Used to extend UserDetailsService to work with Spring Security.
 * Adds two methods that need to be implemented findByEmail() and save()
 */
public interface UserService extends UserDetailsService {
//   User findByEmail(String email);
   User save(UserRegistrationDto registration);
}