package com.nghianguyen.FitnessTracker.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.nghianguyen.FitnessTracker.model.Set;
import com.nghianguyen.FitnessTracker.service.SetService;
/*
 * Controller that maps any requests related to Set to their respective methods and views
 */
@Controller
public class SetController {
	@Autowired
	SetService setService;
//	@Autowired
//	ActivityService activityService;
	
	/*
	 * Calls the SetService method getAllSets() to return a view that has a list of all Sets
	 */
//	@GetMapping("/set")
//	public List<Set> getAllSets() {
//		return setService.getAllSets();
//	}
//	
//	@GetMapping("/set/{id}")
//	public Optional<Set> getSetByID(@PathVariable("id") int id) {
//		return setService.getSetByID(id);
//	}
//	
//	@PostMapping("/set")
//	public void addSet(@RequestBody Set set) {
//		setService.addSet(set);
//	}
	
//	@PutMapping("/set/{id}")
//	public void updateSet(@PathVariable("id") int id, @RequestBody Set set) {
//		setService.updateSet(id, set);
//	}
	
//	@DeleteMapping("/set/{id}")
//	public void deleteSet(@PathVariable("id") int id) {
//		setService.deleteSet(id);
//	}
	
//	@DeleteMapping("/set") 
//	public void deleteAllSets() {
//		setService.deleteAllSets();
//	}
	
//	@PutMapping("/set/{sID}/activity/{aID}")
//	public void addActivityToSet(@PathVariable("sID") int sID, @PathVariable("aID") int aID) {
//		setService.addActivityToSet(sID, aID);
//	}
}
