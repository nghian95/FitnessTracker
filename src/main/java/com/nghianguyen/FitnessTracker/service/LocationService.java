package com.nghianguyen.FitnessTracker.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nghianguyen.FitnessTracker.model.Location;
import com.nghianguyen.FitnessTracker.model.User;
import com.nghianguyen.FitnessTracker.repository.LocationRepository;
import com.nghianguyen.FitnessTracker.repository.UserRepository;

@Service
public class LocationService {

	@Autowired
	LocationRepository locationRepository;
	
	@Autowired
	UserRepository userRepository;
	
	public List<Location> getAllLocations() {
		return locationRepository.findAll();
	}
	
	public Optional<Location> getLocationByID(Integer id) {
		return locationRepository.findById(id);
	}
	
	public void addLocation(Location location) {
		locationRepository.save(location);
	}
	
	public void updateLocation(Integer id, Location location) {
		Optional<Location> locationData = locationRepository.findById(id);
		
		if (locationData.isPresent()) {
			Location updatedLocation = locationData.get();
			updatedLocation.setLocationName(location.getLocationName());
			updatedLocation.setLocationAddress(location.getLocationAddress());
			if (location.getUser().size() > 0) {
				User user = location.getUser().get(location.getUser().size()-1);
				List<Location> locations = user.getLocations();
				locations.add(location);
				userRepository.save(user);
//				updatedLocation.setUser(location.getUser());
			}
			locationRepository.save(updatedLocation);
		}
	}
	
	public void deleteLocationByID(Integer id) {
		locationRepository.deleteById(id);
	}
	
	public void deleteAllLocations() {
		locationRepository.deleteAll();
	}
}
