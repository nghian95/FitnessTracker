package com.nghianguyen.fitnesstracker.service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nghianguyen.fitnesstracker.model.ActivityList;
import com.nghianguyen.fitnesstracker.repository.ActivityListRepository;
/*
 * Utilizes the ActivityListRepository's CRUD operations to make changes
 * to ActivityList entities.
 */
@Service
public class ActivityListService {

   @Autowired
   private ActivityListRepository activityListRepository;

   /*
    * Gets all ActivityLists in the database by using the ActivityListRepository
    */
   public List<ActivityList> getAllActivityLists() {
	   return activityListRepository.findAll();
   }

   /*
    * Returns an Optional<ActivityList> based on an Integer id. If data was retrieved can 
    * use .get() on the Optional<> otherwise it's null.
    */
   public Optional<ActivityList> getActivityListByID(Integer id) {
        return activityListRepository.findById(id);
   }
   
   /*
    * ActivityListRepository method used to add / save a new Activity List which is passed
    * as an argument.
    */
   public void addActivityList(ActivityList activityList) {
	   activityListRepository.save(activityList);
   }

   /*
    * Updates the ActivityList with the provided id and new properties.
    * Uses save() method to save the changes.
    */
   public void updateActivityList(Integer id, ActivityList activityList) {
       Optional<ActivityList> activityListData = activityListRepository.findById(id);

       if (activityListData.isPresent()) {
    	   ActivityList updateActivityList = activityListData.get();
    	   updateActivityList.setActivityName(activityList.getActivityName());
    	   updateActivityList.setDescription(activityList.getDescription());
    	   updateActivityList.setMajorMuscleGroup(activityList.getMajorMuscleGroup());
    	   updateActivityList.setMinorMuscles(activityList.getMinorMuscles());
           activityListRepository.save(updateActivityList);
       }
   }

   /*
    * Based on the parameter Integer id the ActivityList is deleted from the database.
    */
   public void deleteActivityList(Integer id) {
	   activityListRepository.deleteById(id);
   }

   /*
    * Deletes all of the ActivityLists available through the ActivityListRepository.
    */
   public void deleteAllActivityLists() {
	   activityListRepository.deleteAll();
   }

   /*
    * Gets a list of ActivityLists via paging
    */
   public List<ActivityList> getPagedActivityLists(int pageNum, int pageSize) {
	   List<ActivityList> pageResults = activityListRepository.getPagedActivityLists((pageNum-1)*pageSize, pageSize);
//	   for (ActivityList activityList : pageResults) {
//		   System.out.println(activityList);
//	   }
	   return pageResults;
   }
}
