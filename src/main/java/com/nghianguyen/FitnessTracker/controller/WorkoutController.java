package com.nghianguyen.FitnessTracker.controller;

import java.util.List;
import java.util.Optional;

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
import com.nghianguyen.FitnessTracker.model.Workout;
import com.nghianguyen.FitnessTracker.service.LocationService;
import com.nghianguyen.FitnessTracker.service.WorkoutService;

@Controller
public class WorkoutController {
	@Autowired
	WorkoutService workoutService;
	
	@Autowired
	LocationService locationService;
	
	@GetMapping("/workout")
	public String getAllWorkouts(Model model) {
		model.addAttribute("listOfWorkouts",workoutService.getAllWorkouts());
		for (Workout workout : workoutService.getAllWorkouts()) {
			System.out.println(workout);
		}
		return "listOfWorkouts";
	}
	
	@GetMapping("/workout/{id}")
	public String getWorkoutByID(@PathVariable("id") int id, Model model) {
		model.addAttribute("workout", workoutService.getWorkoutByID(id));
		return "singleWorkout";
	}
	
	@GetMapping("/addWorkout")
	public String workoutForm(Model model) {
		model.addAttribute("locations", locationService.getAllLocations());
	   	return "addWorkout";
	}
	
	@GetMapping("/updateWorkout")
	public String updateWorkout(@RequestParam(value="workoutID") int workoutID, Model model) {
		Workout workout = workoutService.getWorkoutByID(workoutID).get();
		model.addAttribute("workout", workout);
		List<Location> locations = locationService.getAllLocations();
		model.addAttribute("locations", locations);
		return "updateWorkout";
   }
	
	@PostMapping("/workout")
	public String addWorkout(@ModelAttribute  Workout workout, Model model) {
		workoutService.addWorkout(workout);
		Workout retrievedWorkout = workoutService.getWorkoutByID(workout.getWorkoutID()).get();
		model.addAttribute("workout", retrievedWorkout);
		return "singleWorkout";
	}
	
	@PutMapping("/updateWorkout")
	public String updateWorkout(@ModelAttribute Workout workout, Model model) {
	       Optional<Workout> workoutData = workoutService.getWorkoutByID(workout.getWorkoutID());

	       if (workoutData.isPresent()) {
	    	   Workout updateWorkout = workoutData.get();
	    	   updateWorkout.setWorkoutDate(workout.getWorkoutDate());
	    	   updateWorkout.setLocation(workout.getLocation());
	           workoutService.addWorkout(updateWorkout);
	       }
	       
		   Workout retrievedWorkout = workoutService.getWorkoutByID(workout.getWorkoutID()).get();
		   model.addAttribute("workout", retrievedWorkout);
	       
	       return "singleWorkout";
	}
	
	@DeleteMapping("/deleteWorkout/{id}")
	public String deleteWorkout(@PathVariable("id") int id) {
		workoutService.deleteWorkoutByID(id);
		return "listOfWorkouts";
	}
	
	@DeleteMapping("/deleteWorkout") 
	public void deleteAllWorkouts() {
		workoutService.deleteAllWorkouts();
	}
}
