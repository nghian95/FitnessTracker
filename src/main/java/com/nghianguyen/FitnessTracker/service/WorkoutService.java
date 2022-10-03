package com.nghianguyen.FitnessTracker.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nghianguyen.FitnessTracker.model.Workout;
import com.nghianguyen.FitnessTracker.repository.WorkoutRepository;

@Service
public class WorkoutService {
	@Autowired
	WorkoutRepository workoutRepository;
	
	public List<Workout> getAllWorkouts(){
		return workoutRepository.findAll();
	}
	
	public Optional<Workout> getWorkoutByID(Integer id) {
		return workoutRepository.findById(id);
	}
	
	public void addWorkout(Workout workout) {
		workoutRepository.save(workout);
	}
	
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
	
	public void deleteWorkoutByID(Integer id) {
		workoutRepository.deleteById(id);
	}
	
	public void deleteAllWorkouts() {
		workoutRepository.deleteAll();
	}
}
