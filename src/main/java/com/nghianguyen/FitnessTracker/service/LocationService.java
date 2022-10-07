package com.nghianguyen.FitnessTracker.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nghianguyen.FitnessTracker.model.Location;
import com.nghianguyen.FitnessTracker.repository.LocationRepository;

@Service
public class LocationService {

	@Autowired
	LocationRepository locationRepository;
	
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
