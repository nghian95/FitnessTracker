package com.nghianguyen.FitnessTracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.nghianguyen.FitnessTracker.model.Activity;
import com.nghianguyen.FitnessTracker.repository.ActivityRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ActivityService {

   @Autowired
   private ActivityRepository activityRepository;

   public List<Activity> getAllActivities() {
       List<Activity> activities = new ArrayList<Activity>();
       activityRepository.findAll().forEach(activities::add);
       return activities;
   }


   public Optional<Activity> getActivityById(Integer id) {
        return activityRepository.findById(id);
   }

   public void addActivity(Activity activity) {
       activityRepository.save(activity);
   }

   public void updateActivity(int id, Activity activity) {
       Optional<Activity> activityData = activityRepository.findById(id);

       if (activityData.isPresent()) {
    	   Activity updateActivity = activityData.get();
           updateActivity.setActivityList(activity.getActivityList());
           updateActivity.setComment(activity.getComment());
           updateActivity.setSets(activity.getSets());
           activityRepository.save(updateActivity);
       }
   }

   public void deleteActivity(int id) {
       activityRepository.deleteById(id);
   }

   public void deleteAllActivities() {
       activityRepository.deleteAll();
   }

}
