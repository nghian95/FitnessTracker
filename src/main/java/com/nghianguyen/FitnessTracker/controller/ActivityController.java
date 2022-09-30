package com.nghianguyen.FitnessTracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.nghianguyen.FitnessTracker.model.Activity;
import com.nghianguyen.FitnessTracker.service.ActivityService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping()
public class ActivityController {

	@Autowired
    private ActivityService activityService; 
	
	@GetMapping("/activity")
   public List<Activity> getAllActivities()         {
       return activityService.getAllActivities();
      
   }

   @GetMapping("/activity/{id}")
   public Optional<Activity> getActivityById(@PathVariable("id") int id) {
    /* the TutorialRepository provides a method findById(). This methods takes the id of the
    Tutorial to find. This method used to be findOne(). But since Spring data jpa 2.0 it's changed to findById().
     */
       return  activityService.getActivityById(id);
   }
  /*  To add new Tutorials is really easy. You do this by using the TutorialRepository save() method.
   */
   
   @PostMapping("/activity")
   public void createActivity(@RequestBody Activity activity) {
	   activityService.addActivity(activity);
   }

// To update a tutorial record, we used the same save() and findById()
   @PutMapping("/activity/{id}")
   public void updateActivity(@PathVariable("id") int id, @RequestBody Activity activity) {
       Optional<Activity> activityData = activityService.getActivityById(id);

       if (activityData.isPresent()) {
    	   Activity updateActivity = activityData.get();
           updateActivity.setActivityList(activity.getActivityList());
           updateActivity.setComment(activity.getComment());
           updateActivity.setSets(activity.getSets());
           activityService.addActivity(updateActivity);
       }
   }

   @DeleteMapping("/activity/{id}")
   public void deleteActivity(@PathVariable("id") int id) {
	   activityService.deleteActivity(id);
   }
  /* To delete a tutorials record, you simply use the deleteById() method provided by the tutorialRepository.
   Then you pass in the id of the record you want to delete.
   */
   @DeleteMapping("/activity")
   public void deleteAllActivities() {
	   activityService.deleteAllActivities();
   }

   }

