package com.nghianguyen.FitnessTracker.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.nghianguyen.FitnessTracker.model.Activity;
import com.nghianguyen.FitnessTracker.model.ActivityList;
import com.nghianguyen.FitnessTracker.model.Set;
import com.nghianguyen.FitnessTracker.service.ActivityListService;
import com.nghianguyen.FitnessTracker.service.ActivityService;

@Controller
//@RequestMapping()
public class ActivityController {

	@Autowired
    private ActivityService activityService; 
	
	@Autowired
	private ActivityListService activityListService;
	
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
   
   @GetMapping("/addActivity")
   public String activityForm(Model model) {
	   List<ActivityList> activityLists = activityListService.getAllActivityLists();
	   model.addAttribute("activityLists",activityLists);
	   return "addActivity";
   }
   
   @GetMapping("/updateActivity")
//   public String updateActivity(@ModelAttribute("eachActivity") Activity activity, Model model) {
   public String updateActivity(@RequestParam(value="activityID") int activityID, Model model) {
//	   model.addAttribute("activity", activity);
	   Activity activity = activityService.getActivityById(activityID).get();
	   activity.getSets().add(new Set());
	   model.addAttribute("activity", activity);
	   List<ActivityList> activityLists = activityListService.getAllActivityLists();
	   model.addAttribute("activityLists",activityLists);
	   return "updateActivity";
   }
   
   @PostMapping("/activity")
//   public String addActivity(@RequestParam(name = "activityList") String activityListName, @ModelAttribute Activity activity, ModelMap model) {
   public String addActivity(@ModelAttribute Activity activity, ModelMap model) {
	   activityService.addActivity(activity);
	   Activity retrievedActivity = activityService.getActivityById(activity.getActivityID()).get();
	   model.addAttribute("activity", retrievedActivity);
	   return "singleActivity";
   }

// To update a tutorial record, we used the same save() and findById()
//   @PutMapping("/activity/{id}")
   @PutMapping("/updateActivity")
//   public void updateActivity(@PathVariable("id") int id, @RequestBody Activity activity) {
   public String updateActivity(@ModelAttribute Activity activity, Model model) {
       Optional<Activity> activityData = activityService.getActivityById(activity.getActivityID());

       if (activityData.isPresent()) {
    	   Activity updateActivity = activityData.get();
           updateActivity.setActivityList(activity.getActivityList());
           updateActivity.setComment(activity.getComment());
           List<Set> sets = activity.getSets();
           Set set = sets.get(sets.size()-1);
           if ((set.getReps() == 0) && set.getWeight() == 0) {
        	   sets.remove(set);
           }
           updateActivity.setSets(sets);
           activityService.addActivity(updateActivity);
       }
       
	   Activity retrievedActivity = activityService.getActivityById(activity.getActivityID()).get();
	   model.addAttribute("activity", retrievedActivity);
       
       return "singleActivity";
   }
   
   @DeleteMapping("/deleteActivity/{id}")
   public void deleteActivity(@PathVariable("id") int id) {
	   activityService.deleteActivity(id);
//	   getAllActivities(model);
//	   return new ModelAndView("redirect:/activity");
//	   return new ModelAndView("activity");
//	   return "redirect:/activity";
   }
  /* To delete a tutorials record, you simply use the deleteById() method provided by the tutorialRepository.
   Then you pass in the id of the record you want to delete.
   */
   @DeleteMapping("/deleteActivity")
   public void deleteAllActivities() {
	   activityService.deleteAllActivities();
   }

	@GetMapping("/nav")
	public String getNav() {
		return "header.html";
	}
}



