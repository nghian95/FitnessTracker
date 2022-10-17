package com.nghianguyen.fitnesstracker.service;

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

import com.nghianguyen.fitnesstracker.model.Role;
import com.nghianguyen.fitnesstracker.model.User;
import com.nghianguyen.fitnesstracker.repository.UserRepository;
import com.nghianguyen.fitnesstracker.security.UserRegistrationDto;

/*
 * Implementation of the UserService. Includes implementation of CRUD operations for User
 */
@Service
public class UserServiceImpl implements UserService{
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	/*
	 * Gets all Users in the database. 
	 */
	public List<User> getAllUsers(){
		return userRepository.findAll();
	}
	
	/*
	 * Gets a specific user based on the parameter email.
	 */
//	public Optional<User> getUserByEmail(String email) {
//		return userRepository.findById(email);
//	}
	
	/*
	 * Adds a user based on the parameter User passed through by the UserController.
	 */
	public void addUser(User user) {
		userRepository.save(user);
	}
	
	/*
	 * User gets retrieved from database based on parameter email then if it exists then
	 * it has its values updated with the new ones. Then it's saved and added.
	 */
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
	
	/*
	 * Deletes the user by parameter email.
	 */
	public void deleteUserByEmail(String email) {
		userRepository.deleteById(email);
	}
	
	/*
	 * Deletes all users.
	 */
	public void deleteAllUsers() {
		userRepository.deleteAll();
	}

	/*
	 * Finds User by email. If user is found, returns it. If not, returns null. 
	 */
	public Optional<User> findByEmail(String email){
//		return userRepository.findByEmail(email);
		Optional<User> user = userRepository.findById(email);
		return user;
//		if (user.isPresent()) {
//			return user;
//		} else {
//			return null;
//		}
	}
	
	/*
	 * Implementing the loadUserByUsername for UserDetailsService interface. 
	 * If User is present in the database UserDetails is returned with email, password and authorities.
	 */
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//		User user = userRepository.findByEmail(email);
		Optional<User> optionalUser = userRepository.findById(email);
		User user = null;
		if (optionalUser.isPresent()) {
			user = optionalUser.get();
		}
		if (user == null){
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(),
				user.getPassword(),
				mapRolesToAuthorities(user.getRoles()));
	}
	
	/*
	 * Turns the Collection of roles into a list of SimpleGrantedAuthority(s)
	 */
	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
		return roles.stream()
               .map(role -> new SimpleGrantedAuthority(role.getName()))
               .collect(Collectors.toList());
	}
	
	/*
	 * Gets the relevant User details from UserRegistrationDto parameter registration and encodes
	 * the password with passwordEncoder. Sets role as a User and saves the new user.
	 */
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
