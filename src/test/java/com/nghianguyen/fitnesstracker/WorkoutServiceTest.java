package com.nghianguyen.fitnesstracker;

import java.util.Collection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.nghianguyen.fitnesstracker.FitnessTrackerApplication;
import com.nghianguyen.fitnesstracker.model.Workout;
import com.nghianguyen.fitnesstracker.service.WorkoutService;

/*
 * Tests WorkoutService methods
 */
@SpringBootTest(classes = FitnessTrackerApplication.class)
class WorkoutServiceTest {

	@Autowired
	WorkoutService workoutService;
	
	/*
	 * Gets workout by ID and makes sure it's not null
	 */
	@Test
	void testGetWorkoutByID() {
		Workout workout = workoutService.getWorkoutByID(16).get();
		Assertions.assertNotNull(workout);
	}
	
	/*
	 * Tests custom query to find all workouts related to a user.
	 */
	@Test
	void testFindWorkoutsByUser() {
		Collection<Workout> workouts = workoutService.findWorkoutsByUser("pete@asdf.com");
		Assertions.assertFalse(workouts.isEmpty());
	}
}
