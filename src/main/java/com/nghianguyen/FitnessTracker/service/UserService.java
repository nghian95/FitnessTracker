package com.nghianguyen.FitnessTracker.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.nghianguyen.FitnessTracker.model.User;
import com.nghianguyen.FitnessTracker.security.UserRegistrationDto;

public interface UserService extends UserDetailsService {
   User findByEmail(String email);
   User save(UserRegistrationDto registration);
}