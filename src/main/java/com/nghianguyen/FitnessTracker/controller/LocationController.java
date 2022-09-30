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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nghianguyen.FitnessTracker.model.Location;
import com.nghianguyen.FitnessTracker.service.LocationService;

@RestController
//@RequestMapping()
public class LocationController {
	@Autowired
	LocationService locationService;
	
	@GetMapping("/location")
	public List<Location> getAllLocations() {
		return locationService.getAllLocations();
	}
	
	@GetMapping("/location/{id}")
	public Optional<Location> getLocationByID(@PathVariable("id") int id) {
		return locationService.getLocationByID(id);
	}
	
	@PostMapping("/location")
	public void addLocation(@RequestBody Location location) {
		locationService.addLocation(location);
	}
	
	@PutMapping("/location/{id}") 
	public void updateLocation(@PathVariable int id, @RequestBody Location location) {
		Optional<Location> locationData = locationService.getLocationByID(id);
		
		if (locationData.isPresent()) {
			Location updatedLocation = locationData.get();
			updatedLocation.setLocationName(location.getLocationName());
			updatedLocation.setLocationAddress(location.getLocationAddress());
			locationService.updateLocation(id, updatedLocation);
		}
	}
	
	@DeleteMapping("/location/{id}") 
	public void deleteLocation(@PathVariable int id) {
		locationService.deleteLocation(id);
	}
	
	@DeleteMapping("/location") 
	public void deleteAllLocations() {
		locationService.deleteAllLocations();
	}
}


