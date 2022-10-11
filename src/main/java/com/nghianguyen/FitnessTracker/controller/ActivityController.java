package com.nghianguyen.FitnessTracker.controller;

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

import com.nghianguyen.FitnessTracker.model.Activity;
import com.nghianguyen.FitnessTracker.model.ActivityList;
import com.nghianguyen.FitnessTracker.model.Set;
import com.nghianguyen.FitnessTracker.model.Workout;
import com.nghianguyen.FitnessTracker.service.ActivityListService;
import com.nghianguyen.FitnessTracker.service.ActivityService;
import com.nghianguyen.FitnessTracker.service.WorkoutService;

@Controller
public class ActivityController {

	@Autowired
    private ActivityService activityService; 
	
	@Autowired
	private ActivityListService activityListService;
	
	@Autowired	
	private WorkoutService workoutService;
	
	@GetMapping("/activity")
	public String getAllActivities(Model model){
		model.addAttribute("listOfActivities", activityService.getAllActivities());
		return "list_of_activities";
	}

	@GetMapping("/activity/{id}")
	public String getActivityById(@PathVariable("id") int id, Model model) {
		model.addAttribute("activity", activityService.getActivityById(id).get());
		return "single_activity";
	}
   
	@GetMapping("/addActivity")
	public String activityForm(@RequestParam(value="workoutID") int workoutID, Model model) {
		List<ActivityList> activityLists = activityListService.getAllActivityLists();
	   	model.addAttribute("activityLists",activityLists);
	   	Workout workout = workoutService.getWorkoutByID(workoutID).get();
	   	model.addAttribute("workout", workout);
	   	return "add_activity";
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
	   return "update_activity";
   }
   
   @PostMapping("/activity")
//   public String addActivity(@RequestParam(name = "activityList") String activityListName, @ModelAttribute Activity activity, ModelMap model) {
   public String addActivity(@RequestParam(value="workoutID") int workoutID, @ModelAttribute Activity activity, ModelMap model) {
	   activity.setWorkout(workoutService.getWorkoutByID(workoutID).get());
	   activityService.addActivity(activity);
	   Activity retrievedActivity = activityService.getActivityById(activity.getActivityID()).get();
	   model.addAttribute("activity", retrievedActivity);
	   return "redirect:/workout/" + workoutID;
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
       
       return "single_activity";
   }
   
   @GetMapping("/deleteActivity")
   public String deleteActivity(@RequestParam("activityID") int activityID) {
	   activityService.deleteActivity(activityID);
	   return "activityList";
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



