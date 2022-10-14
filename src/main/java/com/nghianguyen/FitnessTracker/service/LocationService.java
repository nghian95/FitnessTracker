package com.nghianguyen.FitnessTracker.service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nghianguyen.FitnessTracker.model.Location;
import com.nghianguyen.FitnessTracker.model.User;
import com.nghianguyen.FitnessTracker.repository.LocationRepository;
import com.nghianguyen.FitnessTracker.repository.UserRepository;

/*
 * Service class that calls the CRUD Operations in LocationRepository. 
 * Holds any business logic required for Locations.
 */
@Service
public class LocationService {

	@Autowired
	LocationRepository locationRepository;
	
	@Autowired
	UserRepository userRepository;
	
	/*
	 * Retrieves all Locations in the database
	 */
	public List<Location> getAllLocations() {
		return locationRepository.findAll();
	}
	
	/*
	 * Retrives a specific Location based on parameter id
	 */
	public Optional<Location> getLocationByID(Integer id) {
		return locationRepository.findById(id);
	}
	
	/*
	 * Adds a Location passed from the LocationController.
	 */
	public void addLocation(Location location) {
		locationRepository.save(location);
	}
	
	/*
	 * Finds a Location in the database based on parameter id, then if that Location
	 * exists then it goes and updates each property with the new values.
	 */
	public void updateLocation(Integer id, Location location, User user, boolean addUser) {
		Optional<Location> locationData = locationRepository.findById(id);
		
		if (locationData.isPresent()) {
			Location updatedLocation = locationData.get();
			updatedLocation.setLocationName(location.getLocationName());
			updatedLocation.setLocationAddress(location.getLocationAddress());
			if (addUser) {
				updatedLocation.getUser().add(user);
				user.getLocations().add(updatedLocation);
			} else {
				user.getLocations().remove(updatedLocation);
			}
//			User user = userRepository.findByEmail(principal.getName());
//			user
//			if (location.getUser().size() > 0) {
//				User user = location.getUser().get(location.getUser().size()-1);
//				List<Location> locations = user.getLocations();
//				locations.add(location);
//				userRepository.save(user);
//			}
			userRepository.save(user);
			locationRepository.save(updatedLocation);
		}
	}
	
	/*
	 * Deletes Location based on parameter id passed to it.
	 */
	public void deleteLocationByID(Integer id) {
		locationRepository.deleteById(id);
	}
	
	/*
	 * Deletes all Locations. Meant for admins.
	 */
	public void deleteAllLocations() {
		locationRepository.deleteAll();
	}
}
