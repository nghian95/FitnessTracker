package com.nghianguyen.FitnessTracker.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.nghianguyen.FitnessTracker.model.Activity;
import com.nghianguyen.FitnessTracker.service.ActivityService;

@Controller
//@RequestMapping()
public class ActivityController {

	@Autowired
    private ActivityService activityService; 
	
	@GetMapping("/activity")
   public String getAllActivities(Model model){
//		for (Activity act: activityService.getAllActivities()) {
//			System.out.println(act);
//		}
		model.addAttribute("listOfActivities", activityService.getAllActivities());
		return "listOfActivities";
   }
	

   @GetMapping("/activity/{id}")
   public String getActivityById(@PathVariable("id") int id, Model model) {
		model.addAttribute("activity", activityService.getActivityById(id).get());
		return "singleActivity";
   }
  /*  To add new Tutorials is really easy. You do this by using the TutorialRepository save() method.
   */
   
   @PostMapping("/activity")
   public void addActivity(@ModelAttribute Activity activity, Model model) {
//	   model.addAttribute("activity", activity);
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

