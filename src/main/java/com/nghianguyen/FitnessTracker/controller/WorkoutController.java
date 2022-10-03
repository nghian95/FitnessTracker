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
import org.springframework.web.bind.annotation.RestController;

import com.nghianguyen.FitnessTracker.model.Workout;
import com.nghianguyen.FitnessTracker.service.WorkoutService;

@RestController
public class WorkoutController {
	@Autowired
	WorkoutService workoutService;
	
	@GetMapping("/workout")
	public List<Workout> getAllWorkouts() {
		return workoutService.getAllWorkouts();
	}
	
	@GetMapping("/workout/{id}")
	public Optional<Workout> getWorkoutByID(@PathVariable("id") int id) {
		return workoutService.getWorkoutByID(id);
	}
	
	@PostMapping("/workout")
	public void addWorkout(@RequestBody Workout workout) {
		workoutService.addWorkout(workout);
	}
	
	@PutMapping("/workout/{id}")
	public void updateWorkout(@PathVariable("id") int id, @RequestBody Workout workout) {
//		Activity activity = activityService.getActivityById(activityID).get();
//		set.addActivity(activity);
		workoutService.updateWorkout(id, workout);
	}
	
	@DeleteMapping("/workout/{id}")
	public void deleteWorkout(@PathVariable("id") int id) {
		workoutService.deleteWorkoutByID(id);
	}
	
	@DeleteMapping("/workout") 
	public void deleteAllWorkouts() {
		workoutService.deleteAllWorkouts();
	}
}
