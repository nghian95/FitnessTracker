package com.nghianguyen.FitnessTracker.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nghianguyen.FitnessTracker.model.Activity;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Integer>
{
//	List<Activity> findByPublished(boolean published);
	@Query("SELECT a FROM Activity a WHERE a.workout.workoutID = :workoutID")
	Collection<Activity> findActivitiesInWorkout(int workoutID);
	
}

