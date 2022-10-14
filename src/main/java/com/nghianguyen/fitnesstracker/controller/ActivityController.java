package com.nghianguyen.fitnesstracker.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nghianguyen.fitnesstracker.model.Activity;
import com.nghianguyen.fitnesstracker.model.ActivityList;
import com.nghianguyen.fitnesstracker.model.Set;
import com.nghianguyen.fitnesstracker.model.Workout;
import com.nghianguyen.fitnesstracker.service.ActivityListService;
import com.nghianguyen.fitnesstracker.service.ActivityService;
import com.nghianguyen.fitnesstracker.service.WorkoutService;
/*
 * 	Controller for any Activity related mappings and methods. CRUD Operations for Activity.
 */
@Controller
public class ActivityController {

	@Autowired
    private ActivityService activityService; 
	
	@Autowired
	private ActivityListService activityListService;
	
	@Autowired	
	private WorkoutService workoutService;
	
	/*
	 * 	Method to show all activities in the database, meant for admin use as this is not restricted
	 * 	by users.
	 */
	@GetMapping("/activity")
	public String getAllActivities(Model model){
		model.addAttribute("listOfActivities", activityService.getAllActivities());
		return "list_of_activities";
	}

	/*	
	 * 	Return activity based on the Activity ID
	 */
	@GetMapping("/activity/{id}")
	public String getActivityById(@PathVariable("id") int id, Model model) {
		model.addAttribute("activity", activityService.getActivityById(id).get());
		return "single_activity";
	}
   
	/*	Gets a workoutID request parameter so that the form to add Activity will know what
	 *	workout to associate with it. Model is used to pass List of ActivityList so user can 
	 *  choose which workout they're doing. Page to add activity is shown
	 */ 
	@GetMapping("/addActivity")
	public String activityForm(@RequestParam(value="workoutID") int workoutID, Model model) {
		List<ActivityList> activityLists = activityListService.getAllActivityLists();
	   	model.addAttribute("activityLists",activityLists);
	   	Workout workout = workoutService.getWorkoutByID(workoutID).get();
	   	model.addAttribute("workout", workout);
//	   	Activity activity = new Activity();
//	   	model.addAttribute("activity", activity);
	   	return "add_activity";
	}
	
	/*
	 * This method is to show the update_activity.html page so that users can update their current
	 * activities. Takes in the activityID of the Activity they want to change so it can prepopulate
	 * fields and then passes that activity and a list of ActivityLists for the view page.
	 */
   @GetMapping("/updateActivity")
//   public String updateActivity(@ModelAttribute("eachActivity") Activity activity, Model model) {
   public String updateActivity(@RequestParam(value="activityID") int activityID, Model model) {
	   Activity activity = activityService.getActivityById(activityID).get();
	   List<Set> sets = activity.getSets();
	   if (sets.size() == 0) {
		   sets.add(new Set());
	   }
	   model.addAttribute("activity", activity);
	   List<ActivityList> activityLists = activityListService.getAllActivityLists();
	   model.addAttribute("activityLists",activityLists);
	   return "update_activity";
   }
   
   /*
    * Creates the Activity once the form has been completed and submitted. Gets the workout and relates it to the
    * activity. Then the activity is added, and the returned view shows the added activity to ensure that it was added
    * successfully.
    */
   @PostMapping("/activity")
//   public String addActivity(@RequestParam(name = "activityList") String activityListName, @ModelAttribute Activity activity, ModelMap model) {
   public String addActivity(@RequestParam(value="workoutID") int workoutID, @ModelAttribute Activity activity, BindingResult result, ModelMap model) {
	   activity.setWorkout(workoutService.getWorkoutByID(workoutID).get());
       List<Set> sets = activity.getSets();
//       Set set = sets.get(sets.size()-1);
//     if ((set.getReps() == 0) && set.getWeight() == 0) {
//	   sets.remove(set);
//   }
       for (int i = 0; i < sets.size(); i++) {
    	   if ((sets.get(i).getReps() == 0) && sets.get(i).getWeight() == 0) {
    		   sets.remove(i);
    		   i--;
    	   }
       }
	   activityService.addActivity(activity);
	   Activity retrievedActivity = activityService.getActivityById(activity.getActivityID()).get();
	   model.addAttribute("activity", retrievedActivity);
	   String URL = "redirect:/workout/" + workoutID;
	   return URL;
   }

   /*
    * This is the method that actually updates the Activity once the changes have been submitted.
    * Has activity parameter that holds the activity properties to be added. Grabs the current activity 
    * by same activityID and updates each corresponding property. Checks if the last set is empty or not 
    * in case user didn't add any details to the last set and removes it if it's empty. Shows the updated
    * activity.
    */
   @PutMapping("/updateActivity")
//   public void updateActivity(@PathVariable("id") int id, @RequestBody Activity activity) {
   public String updateActivity(@ModelAttribute Activity activity, BindingResult result, Model model) {
       Optional<Activity> activityData = activityService.getActivityById(activity.getActivityID());

       if (activityData.isPresent()) {
    	   Activity updateActivity = activityData.get();
           updateActivity.setActivityList(activity.getActivityList());
           updateActivity.setComment(activity.getComment());
           List<Set> sets = activity.getSets();
//           Set set = sets.get(sets.size()-1);
//           if ((set.getReps() == 0) && set.getWeight() == 0) {
//        	   sets.remove(set);
//           }
           for (int i = 0; i < sets.size(); i++) {
        	   if ((sets.get(i).getReps() == 0) && sets.get(i).getWeight() == 0) {
        		   sets.remove(i);
        		   i--;
        	   }
           }
           updateActivity.setSets(sets);
           activityService.addActivity(updateActivity);
       }
       
	   Activity retrievedActivity = activityService.getActivityById(activity.getActivityID()).get();
	   model.addAttribute("activity", retrievedActivity);
       
       return "single_activity";
   }
   
   /*
    * Deletes the Activity based on the RequestParam activityID. Has @RequestParam workoutID to return
    * the single_workout view that the Activity was deleted from.
    */
   @GetMapping("/deleteActivity")
   public String deleteActivity(@RequestParam("activityID") int activityID, @RequestParam("workoutID") int workoutID, Model model) {
	   activityService.deleteActivity(activityID);
	   model.addAttribute("workout", workoutService.getWorkoutByID(workoutID).get());
	   model.addAttribute("listOfActivities", activityService.findActivitiesInWorkout(workoutID));
	   return "single_workout";
   }
   
  /*
   * Deletes all activities. Meant for admin usage.
   */
   @DeleteMapping("/deleteActivity")
   public void deleteAllActivities() {
	   activityService.deleteAllActivities();
   }
}



