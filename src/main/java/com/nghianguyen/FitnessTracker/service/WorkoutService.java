package com.nghianguyen.FitnessTracker.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nghianguyen.FitnessTracker.model.Workout;
import com.nghianguyen.FitnessTracker.repository.WorkoutRepository;

/*
 * Service that implements Workout CRUD operations
 */
@Service
public class WorkoutService {
	@Autowired
	WorkoutRepository workoutRepository;
	
	/*
	 * Gets a list of all workouts in the database
	 */
	public List<Workout> getAllWorkouts(){
		return workoutRepository.findAll();
	}
	
	/*
	 * Gets a list of workouts that are associated with a specific User's email
	 */
	public Collection<Workout> findWorkoutsByUser(String email){
		return workoutRepository.findWorkoutsByUser(email);
	}
	
	/*
	 * Gets a Optional<Workout> from the database based on the parameter id
	 */
	public Optional<Workout> getWorkoutByID(Integer id) {
		return workoutRepository.findById(id);
	}
	
	/*
	 * Adds a new Workout based on the parameter workout that's passed from 
	 * WorkoutController.
	 */
	public void addWorkout(Workout workout) {
		workoutRepository.save(workout);
	}
	
	/*
	 * Retrieves a Workout with parameter id. Then takes the values from parameter
	 * Workout and updates the retrieved Workout with the new values. Updated Workout
	 * is saved.
	 */
	public void updateWorkout(Integer id, Workout workout) {
		Optional<Workout> workoutData = workoutRepository.findById(id);
		
		if (workoutData.isPresent()) {
			Workout updatedWorkout = workoutData.get();
			updatedWorkout.setLocation(workout.getLocation());
			updatedWorkout.setWorkoutDate(workout.getWorkoutDate());
			updatedWorkout.setUser(workout.getUser());
			workoutRepository.save(updatedWorkout);
		}
	}
	
	/*
	 * Deletes Workout based on parameter id.
	 */
	public void deleteWorkoutByID(Integer id) {
		workoutRepository.deleteById(id);
	}
	
	/*
	 * Deletes all Workouts
	 */
	public void deleteAllWorkouts() {
		workoutRepository.deleteAll();
	}
}
