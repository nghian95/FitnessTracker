package com.nghianguyen.fitnesstracker.service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nghianguyen.fitnesstracker.model.Activity;
import com.nghianguyen.fitnesstracker.model.Set;
import com.nghianguyen.fitnesstracker.repository.ActivityRepository;
import com.nghianguyen.fitnesstracker.repository.SetRepository;

/*
 * This is the implementation for any CRUD methods in relation to Activity entity.
 * Uses the ActivityRepository mainly for implementation.
 */
@Service
public class ActivityService {

   @Autowired
   private ActivityRepository activityRepository;
   
   @Autowired
   private SetRepository setRepository;

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
	   
		   for (var i = 0; i < sets.size(); i++) {
			   Set set = sets.get(i);
			   Integer setID = setRepository.getSetIDByProperties(set.getSetOrder(), set.getReps(), set.getWeight());
			   if(setID == null) {
				   setRepository.save(sets.get(i));
			   } else {
				   set = setRepository.getById(setID);
				   sets.set(i, set);
			   }
		   }
		   activity.setSets(sets);
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

}
