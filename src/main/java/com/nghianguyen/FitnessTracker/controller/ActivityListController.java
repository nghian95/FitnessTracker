package com.nghianguyen.FitnessTracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.nghianguyen.FitnessTracker.model.ActivityList;
import com.nghianguyen.FitnessTracker.service.ActivityListService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping()
public class ActivityListController {

	@Autowired
    private ActivityListService activityListService; 
	
	@GetMapping("/activityList")
   public List<ActivityList> getAllActivityLists()         {
       return activityListService.getAllActivityLists();
      
   }

   @GetMapping("/activityList/{id}")
   public Optional<ActivityList> getActivityListById(@PathVariable("id") int id) {
    /* the TutorialRepository provides a method findById(). This methods takes the id of the
    Tutorial to find. This method used to be findOne(). But since Spring data jpa 2.0 it's changed to findById().
     */
       return  activityListService.getActivityListById(id);
   }
  /*  To add new Tutorials is really easy. You do this by using the TutorialRepository save() method.
   */
   
   @PostMapping("/activityList")
   public void createActivityList(@RequestBody ActivityList activityList) {
	   activityListService.addActivityList(activityList);
   }

// To update a tutorial record, we used the same save() and findById()
   @PutMapping("/activityList/{id}")
   public void updateActivityList(@PathVariable("id") int id, @RequestBody ActivityList activityList) {
       Optional<ActivityList> activityListData = activityListService.getActivityListById(id);

       if (activityListData.isPresent()) {
    	   ActivityList updateActivityList = activityListData.get();
    	   updateActivityList.setActivityName(activityList.getActivityName());
    	   updateActivityList.setDescription(activityList.getDescription());
    	   updateActivityList.setMajorMuscleGroup(activityList.getMajorMuscleGroup());
    	   updateActivityList.setMinorMuscles(activityList.getMinorMuscles());
           activityListService.addActivityList(updateActivityList);
       }
   }

   @DeleteMapping("/activityList/{id}")
   public void deleteActivityList(@PathVariable("id") int id) {
	   activityListService.deleteActivityList(id);
   }
  /* To delete a tutorials record, you simply use the deleteById() method provided by the tutorialRepository.
   Then you pass in the id of the record you want to delete.
   */
   @DeleteMapping("/activityList")
   public void deleteAllActivityLists() {
	   activityListService.deleteAllActivityLists();
   }

   }

