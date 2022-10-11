package com.nghianguyen.FitnessTracker.controller;

import java.security.Principal;
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
import com.nghianguyen.FitnessTracker.model.User;
import com.nghianguyen.FitnessTracker.model.Workout;
import com.nghianguyen.FitnessTracker.service.ActivityService;
import com.nghianguyen.FitnessTracker.service.LocationService;
import com.nghianguyen.FitnessTracker.service.UserServiceImpl;
import com.nghianguyen.FitnessTracker.service.WorkoutService;

@Controller
public class WorkoutController {
	@Autowired
	WorkoutService workoutService;
	
	@Autowired
	LocationService locationService;
	
	@Autowired
	UserServiceImpl userServiceImpl;
	
	@Autowired
	ActivityService activityService;
	
	@GetMapping("/admin/workout")
	public String getAllWorkouts(Model model) {
		model.addAttribute("listOfWorkouts",workoutService.getAllWorkouts());
		for (Workout workout : workoutService.getAllWorkouts()) {
			System.out.println(workout);
		}
		return "list_of_workouts";
	}
	
	@GetMapping("/workout")
	public String findWorkoutsByUser(Model model, Principal principal) {
		model.addAttribute("listOfWorkouts",workoutService.findWorkoutsByUser(principal.getName()));
		return "list_of_workouts";
	}
	
	@GetMapping("/workout/{id}")
	public String getWorkoutByID(@PathVariable("id") int id, Model model) {
		model.addAttribute("workout", workoutService.getWorkoutByID(id).get());
//		model.addAttribute("listOfActivities", activityService.getAllActivities());
		model.addAttribute("listOfActivities", activityService.findActivitiesInWorkout(id));
		return "single_workout";
	}
	
	@GetMapping("/addWorkout")
	public String workoutForm(Model model) {
		model.addAttribute("locations", locationService.getAllLocations());
	   	return "add_workout";
	}
	
	@GetMapping("/updateWorkout")
	public String updateWorkout(@RequestParam(value="workoutID") int workoutID, Model model) {
		Workout workout = workoutService.getWorkoutByID(workoutID).get();
		model.addAttribute("workout", workout);
		List<Location> locations = locationService.getAllLocations();
		model.addAttribute("locations", locations);
		return "update_workout";
   }
	
	@PostMapping("/workout")
	public String addWorkout(@ModelAttribute Workout workout, Model model, Principal principal) {
		User user = userServiceImpl.findByEmail(principal.getName());
		workout.setUser(user);
		workoutService.addWorkout(workout);
		Workout retrievedWorkout = workoutService.getWorkoutByID(workout.getWorkoutID()).get();
		model.addAttribute("workout", retrievedWorkout);
		return "single_workout";
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
		   
		   model.addAttribute("listOfActivities", activityService.getAllActivities());
	       
	       return "single_workout";
	}
	
	@GetMapping("/deleteWorkout")
	public String deleteWorkout(@RequestParam(value="workoutID") int workoutID, Model model, Principal principal) {
		workoutService.deleteWorkoutByID(workoutID);
		String email = principal.getName();
		model.addAttribute("listOfWorkouts", workoutService.findWorkoutsByUser(email));
		return "list_of_workouts";
	}
	
	@DeleteMapping("/deleteWorkout") 
	public void deleteAllWorkouts() {
		workoutService.deleteAllWorkouts();
	}
}
