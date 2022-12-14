package com.nghianguyen.fitnesstracker.service;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.Tuple;
import javax.persistence.TupleElement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nghianguyen.fitnesstracker.model.Activity;
import com.nghianguyen.fitnesstracker.model.Set;
import com.nghianguyen.fitnesstracker.repository.ActivityRepository;

/*
 * This is the implementation for any CRUD methods in relation to Activity entity.
 * Uses the ActivityRepository mainly for implementation.
 */
@Service
public class ActivityService {

   @Autowired
   private ActivityRepository activityRepository;
   
   @Autowired
   private SetService setService;

   /*
    * Gets all Activities and returns it by using findAll() method.
    */
   public List<Activity> getAllActivities() {
//       List<Activity> activities = new ArrayList<Activity>();
//       activityRepository.findAll().forEach(activities::add);
	   return activityRepository.findAll();
//       return activities;
   }
   
   /*
    * Gets a collection of Activities in a specific Workout. The Workout in question is
    * retrieved based on the workoutID.
    */
   public Collection<Activity> findActivitiesInWorkout(int id) {
	   return activityRepository.findActivitiesInWorkout(id);
   }

   /*
    * Gets Activity by id and returns the the activity with the Sets sorted.
    */
   public Optional<Activity> getActivityById(Integer id) {
        Optional<Activity> activity = activityRepository.findById(id);
        Collections.sort(activity.get().getSets());
        return activity;
   }

   /*
    * This adds an Activity. If the set already exists inside of the database then we replace the set
    * inside the Activity with the one retrieved from the database so we don't have any persistence issues
    * or errors. If Set doesn't exist yet then it'll be saved. Then save and add the Activity with the Sets.
    */
   public void addActivity(Activity activity) {
	   try {
		   List<Set> sets = activity.getSets();
		   if (sets.size() != 0) {
			   for (var i = 0; i < sets.size(); i++) {
				   Set set = sets.get(i);
				   Integer setID = setService.getSetIDByProperties(set.getSetOrder(), set.getReps(), set.getWeight());
				   System.out.println(setID);
				   if(setID == null) {
					   setService.addSet(sets.get(i));
				   } else {
					   set = setService.getSetByID(setID).get();
					   sets.set(i, set);
				   }
			   }
			   activity.setSets(sets);
		   }
	       activityRepository.save(activity);
   		} catch(Exception e) {
   			System.out.println(e);
   		}
   }

   /*
    * This updates the Activity retrieved from the database via the parameter id, and then
    * updates each property if the Activity exists already. 
    */
   public void updateActivity(Integer id, Activity activity) {
       Optional<Activity> activityData = activityRepository.findById(id);

       if (activityData.isPresent()) {
    	   Activity updateActivity = activityData.get();
           updateActivity.setActivityList(activity.getActivityList());
           updateActivity.setComment(activity.getComment());
           updateActivity.setSets(activity.getSets());
           activityRepository.save(updateActivity);
       }
   }

   /*
    * Deletes Activity based on the parameter id. 
    */
   public void deleteActivity(Integer id) {
       activityRepository.deleteById(id);
   }

   /*
    * Deletes all Activities.
    */
   public void deleteAllActivities() {
       activityRepository.deleteAll();
   }

   /*
    * Finds the last added activity by looking at largest activityID and selecting it
    */
   public Optional<Activity> findLastAddedActivity() {
	   return activityRepository.findLastAddedActivity();
   }
   
   /*
    * Find list of Major Muscles worked for each workout. Backend logic rather than SQL queries.
    */
//   public Map<String, Integer> findMajorMusclesWorked(int id) {
//	   Map<String, Integer> majorMuscles = new HashMap<>();
//	   Collection<Activity> activitiesInWorkout = findActivitiesInWorkout(id);
//	   for (Activity activity : activitiesInWorkout) {
//		   String activityName = activity.getActivityList().getMajorMuscleGroup();
//		   if (majorMuscles.containsKey(activityName)) {
//			   majorMuscles.put(activityName, majorMuscles.get(activityName) + 1);
//		   } else {
//			   majorMuscles.put(activityName, 1);
//		   }
//	   }
//	   return majorMuscles;
//   }
   
   /*
    * Find list of Major Muscles worked for each workout based on SQL Queries
    */
   public List<String> findCountOfMuscleGroups(int workoutID) {
	   List<String> muscleGroups = activityRepository.findCountOfMuscleGroups(workoutID);
//	   for (String str : muscleGroups) {
//		   System.out.println(str);
//	   }
//	   HashMap<String,Integer> muscleGroupCount = new HashMap<>();
	   return muscleGroups;
   }
}
