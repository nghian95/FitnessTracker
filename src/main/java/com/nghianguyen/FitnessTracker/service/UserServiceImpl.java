package com.nghianguyen.FitnessTracker.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.nghianguyen.FitnessTracker.model.Role;
import com.nghianguyen.FitnessTracker.model.User;
import com.nghianguyen.FitnessTracker.repository.UserRepository;
import com.nghianguyen.FitnessTracker.security.UserRegistrationDto;

/*
 * Implementation of the UserService. Includes implementation of CRUD operations for User
 */
@Service
public class UserServiceImpl implements UserService{
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
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

	public User findByEmail(String email){
		return userRepository.findByEmail(email);
	}
	
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(email);
		if (user == null){
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(),
				user.getPassword(),
				mapRolesToAuthorities(user.getRoles()));
	}
	
	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
		return roles.stream()
               .map(role -> new SimpleGrantedAuthority(role.getName()))
               .collect(Collectors.toList());
	}
	
	@Override
	public User save(UserRegistrationDto registration) {
		User user = new User();
		user.setFirstName(registration.getFirstName());
		user.setLastName(registration.getLastName());
		user.setEmail(registration.getEmail());
		user.setPassword(passwordEncoder.encode(registration.getPassword()));
		user.setRoles(Arrays.asList(new Role("ROLE_USER")));
       	return userRepository.save(user);
	}
}
