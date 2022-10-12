package com.nghianguyen.FitnessTracker.service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nghianguyen.FitnessTracker.model.Activity;
import com.nghianguyen.FitnessTracker.repository.ActivityRepository;
import com.nghianguyen.FitnessTracker.repository.SetRepository;

@Service
public class ActivityService {

   @Autowired
   private ActivityRepository activityRepository;
   
   @Autowired
   private SetRepository setRepository;

   public List<Activity> getAllActivities() {
//       List<Activity> activities = new ArrayList<Activity>();
//       activityRepository.findAll().forEach(activities::add);
	   return activityRepository.findAll();
//       return activities;
   }
   
   public Collection<Activity> findActivitiesInWorkout(int id) {
	   return activityRepository.findActivitiesInWorkout(id);
   }


   public Optional<Activity> getActivityById(Integer id) {
        Optional<Activity> activity = activityRepository.findById(id);
        Collections.sort(activity.get().getSets());
        return activity;
   }

   public void addActivity(Activity activity) {
	   if (activity.getSets().size() != 0) {
		   setRepository.save(activity.getSets().get(0));
	   }	
       activityRepository.save(activity);
   }

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

   public void deleteActivity(Integer id) {
       activityRepository.deleteById(id);
   }

   public void deleteAllActivities() {
       activityRepository.deleteAll();
   }

}
