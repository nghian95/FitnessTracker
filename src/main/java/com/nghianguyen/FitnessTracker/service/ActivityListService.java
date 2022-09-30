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
import com.nghianguyen.FitnessTracker.model.ActivityList;
import com.nghianguyen.FitnessTracker.repository.ActivityListRepository;
import com.nghianguyen.FitnessTracker.repository.ActivityRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ActivityListService {

   @Autowired
   private ActivityListRepository activityListRepository;

   public List<ActivityList> getAllActivityLists() {
//       List<Activity> activities = new ArrayList<Activity>();
//       activityRepository.findAll().forEach(activities::add);
	   return activityListRepository.findAll();
//       return activities;
   }

   public Optional<ActivityList> getActivityListById(Integer id) {
        return activityListRepository.findById(id);
   }

   public void addActivityList(ActivityList activityList) {
	   activityListRepository.save(activityList);
   }

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

   public void deleteActivityList(Integer id) {
	   activityListRepository.deleteById(id);
   }

   public void deleteAllActivityLists() {
	   activityListRepository.deleteAll();
   }

}
