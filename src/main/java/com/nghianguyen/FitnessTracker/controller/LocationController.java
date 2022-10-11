package com.nghianguyen.FitnessTracker.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nghianguyen.FitnessTracker.model.Location;
import com.nghianguyen.FitnessTracker.service.LocationService;

@Controller
public class LocationController {
	@Autowired
	LocationService locationService;
	
	@GetMapping("/location")
	public String getAllLocations(Model model) {
		model.addAttribute("locations", locationService.getAllLocations());
		return "list_of_locations";
	}
	
	@GetMapping("/location/{id}")
	public String getLocationByID(@PathVariable("id") int id, Model model) {
		model.addAttribute("location", locationService.getLocationByID(id));
		return "single_location";
	}
	
	@GetMapping("/addLocation")
	public String locationForm(Model model) {
		//make this editable for Location Name
		List<Location> locations = locationService.getAllLocations();
		Set<String> locationNames = locations.stream().map(Location::getLocationName).collect(Collectors.toSet());
		model.addAttribute("locationNames", locationNames);
	   	return "add_location";
	}
	
	@GetMapping("/updateLocation")
	public String updateLocation(@RequestParam(value="locationID") int locationID, Model model) {
		Location location = locationService.getLocationByID(locationID).get();
		model.addAttribute("location", location);
		List<Location> locations = locationService.getAllLocations();
		Set<String> locationNames = locations.stream().map(Location::getLocationName).collect(Collectors.toSet());
		model.addAttribute("locationNames", locationNames);
		return "update_location";
	}
	
	@PostMapping("/location")
	public String addLocation(@ModelAttribute Location location, Model model) {
		locationService.addLocation(location);
		Location retrievedLocation = locationService.getLocationByID(location.getLocationID()).get();
		model.addAttribute("location", retrievedLocation);
		return "single_location";
	}
	
	@PutMapping("/updateLocation") 
	public String updateLocation(@RequestParam(value="addUser") String addUser, @ModelAttribute Location location, Model model) {
//	public String updateLocation(@ModelAttribute Location location, Model model) {
		//test this method once you implement Users. 
		//You should be able to manually type a name as well 
		locationService.updateLocation(location.getLocationID(), location);
		
		Location retrievedLocation = locationService.getLocationByID(location.getLocationID()).get();
		model.addAttribute("location", retrievedLocation);
       
		return "single_location";
	}
	
	@GetMapping("/deleteLocation") 
	public String deleteLocationByID(@RequestParam(value="locationID") int locationID, Model model) {
		locationService.deleteLocationByID(locationID);
		model.addAttribute("locations", locationService.getAllLocations());
		return "list_of_locations";
	}
	
	@DeleteMapping("/deleteLocation") 
	public void deleteAllLocations() {
		locationService.deleteAllLocations();
	}
}


