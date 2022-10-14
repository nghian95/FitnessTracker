package com.nghianguyen.FitnessTracker.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nghianguyen.FitnessTracker.model.Activity;

/*
 * Provides the basic CRUD methods via JPARepository for Activity. Includes a 
 * Custom query to select a list of activities that are associated with a Workout.
 */
@Repository
public interface ActivityRepository extends JpaRepository<Activity, Integer>
{
	@Query("SELECT a FROM Activity a WHERE a.workout.workoutID = :workoutID")
	Collection<Activity> findActivitiesInWorkout(int workoutID);
	
}

