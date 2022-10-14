package com.nghianguyen.FitnessTracker.controller;

import java.security.Principal;
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
import com.nghianguyen.FitnessTracker.model.User;
import com.nghianguyen.FitnessTracker.service.LocationService;
import com.nghianguyen.FitnessTracker.service.UserServiceImpl;

/*
 * Controller that maps all of the CRUD operations and requests related to the Location
 */
@Controller
public class LocationController {
	@Autowired
	LocationService locationService;
	
	@Autowired
	UserServiceImpl userService;
	
	/*
	 * Passes a list of all locations to the view via Model
	 */
	@GetMapping("/location")
	public String getAllLocations(Model model) {
		model.addAttribute("locations", locationService.getAllLocations());
		return "list_of_locations";
	}
	
	/*
	 * Through @PathVariable id this method retrieves the respective Location and passes it
	 * to the view via Model
	 */
	@GetMapping("/location/{id}")
	public String getLocationByID(@PathVariable("id") int id, Model model) {
		model.addAttribute("location", locationService.getLocationByID(id));
		return "single_location";
	}
	
	/*
	 * Retrieves the page where you can add a Location's data through a form. 
	 * Passes a unique Set of location names to choose from. 
	 */
	@GetMapping("/addLocation")
	public String locationForm(Model model) {
		//make this editable for Location Name
		List<Location> locations = locationService.getAllLocations();
		Set<String> locationNames = locations.stream().map(Location::getLocationName).collect(Collectors.toSet());
		model.addAttribute("locationNames", locationNames);
	   	return "add_location";
	}
	
	/*
	 * Through @RequestParam locationID we retrieve the Location that user wants to update. 
	 * Then that Location and a list of unique Set of locationNames are passed to the Model for the view.
	 */
	@GetMapping("/updateLocation")
	public String updateLocation(@RequestParam(value="locationID") int locationID, Model model) {
		Location location = locationService.getLocationByID(locationID).get();
		model.addAttribute("location", location);
		List<Location> locations = locationService.getAllLocations();
		Set<String> locationNames = locations.stream().map(Location::getLocationName).collect(Collectors.toSet());
		model.addAttribute("locationNames", locationNames);
		return "update_location";
	}
	
	/*
	 * Request that creates the new Location. Properties are in @ModelAttribute Location.
	 * After adding the location it's retrieved from the db and passed to the view to confirm 
	 * the changes.
	 */
	@PostMapping("/location")
	public String addLocation(@ModelAttribute Location location, Model model) {
		locationService.addLocation(location);
		Location retrievedLocation = locationService.getLocationByID(location.getLocationID()).get();
		model.addAttribute("location", retrievedLocation);
		return "single_location";
	}
	
	/*
	 * The method that actually updates the location once the form has been submitted. Takes @RequestParam
	 * addUser to determine if the Location should add current user or not. Then updates the Location with
	 * LocationService's method updateLocation() passing the updated properties. 
	 * Finally returns a view with the retrieved & updated Location.
	 */
	@PutMapping("/updateLocation") 
	public String updateLocation(@RequestParam(value="addUser") String addUser, @ModelAttribute Location location, Model model, Principal principal) {
//	public String updateLocation(@ModelAttribute Location location, Model model) {
		//You should be able to manually type a name as well 
		User user = userService.findByEmail(principal.getName());
		boolean flag = addUser.equals("Yes") ? true : false;
//		if (addUser.equals("Yes")) {
//			location.getUser().add(userService.findByEmail(principal.getName()));
			
//		}
		
		locationService.updateLocation(location.getLocationID(), location, user, flag);
		
		Location retrievedLocation = locationService.getLocationByID(location.getLocationID()).get();
		model.addAttribute("location", retrievedLocation);
       
		return "single_location";
	}
	
	/*
	 * Takes @RequestParam locationID which is used to delete the Location via LocationService's method
	 * deleteLocationByID() and passes the list of locations to Model for a view of them.
	 */
	@GetMapping("/deleteLocation") 
	public String deleteLocationByID(@RequestParam(value="locationID") int locationID, Model model) {
		locationService.deleteLocationByID(locationID);
		model.addAttribute("locations", locationService.getAllLocations());
		return "list_of_locations";
	}
	
	/*
	 * Uses LocationService's method deleteAllLocations to delete all locations. Admin use.
	 */
	@DeleteMapping("/deleteLocation") 
	public void deleteAllLocations() {
		locationService.deleteAllLocations();
	}
}


