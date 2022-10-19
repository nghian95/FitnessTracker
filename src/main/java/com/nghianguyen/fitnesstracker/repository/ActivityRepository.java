package com.nghianguyen.fitnesstracker.repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.persistence.Tuple;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nghianguyen.fitnesstracker.model.Activity;

/*
 * Provides the basic CRUD methods via JPARepository for Activity. Includes a 
 * Custom query to select a list of activities that are associated with a Workout.
 */
@Repository
public interface ActivityRepository extends JpaRepository<Activity, Integer>
{
	@Query("SELECT a FROM Activity a WHERE a.workout.workoutID = :workoutID")
	Collection<Activity> findActivitiesInWorkout(int workoutID);
	
	@Query(value = "SELECT * FROM activity ORDER BY activityid DESC LIMIT 1",
			nativeQuery = true)
	Optional<Activity> findLastAddedActivity();
	
//	@Query(value = "SELECT IFNULL(CONCAT(major_muscle_group,'=',COUNT(major_muscle_group)),'N/A') FROM activity a "
//			+ "INNER JOIN activity_list al "
//			+ "ON a.activity_list_activity_listid = al.activity_listid "
//			+ "WHERE a.workout_workoutid = :workoutID",
//			nativeQuery=true)
	@Query(value = "SELECT IFNULL(CONCAT(major_muscle_group,'=', COUNT(major_muscle_group)),'N/A') FROM "
			+ "(SELECT * FROM activity WHERE activity.workout_workoutid = :workoutID) AS a "
			+ "INNER JOIN activity_list al ON a.activity_list_activity_listid = al.activity_listid "
			+ "GROUP BY major_muscle_group",
			nativeQuery=true)
	List<String> findCountOfMuscleGroups(int workoutID);
}

