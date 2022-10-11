package com.nghianguyen.FitnessTracker.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;

import com.nghianguyen.FitnessTracker.model.ActivityList;
import com.nghianguyen.FitnessTracker.service.ActivityListService;

@Controller
public class ActivityListController {

	@Autowired
    private ActivityListService activityListService; 
	
	@GetMapping("/activityList")
   public String getAllActivityLists(Model model)         {
       model.addAttribute("activityLists", activityListService.getAllActivityLists());
       return "list_of_activity_lists";
   }

   @GetMapping("/activityList/{id}")
   public String getActivityListById(@PathVariable("id") int id, Model model) {
       model.addAttribute("activityList", activityListService.getActivityListByID(id));
       return "single_activity_list";
   }
   
	@GetMapping("/addActivityList")
	public String activityListForm(Model model) {
//		List<ActivityList> activityLists = activityListService.getAllActivityLists();
//		Set<String> activityListNames = activityLists.stream().map(ActivityList::getActivityListName).collect(Collectors.toSet());
//		model.addAttribute("activityListNames", activityListNames);
	   	return "add_activity_list";
	}
	
	@GetMapping("/updateActivityList")
	public String updateActivityList(@RequestParam(value="activityListID") int activityListID, Model model) {
		ActivityList activityList = activityListService.getActivityListByID(activityListID).get();
		model.addAttribute("activityList", activityList);
		List<ActivityList> activityLists = activityListService.getAllActivityLists();
		model.addAttribute("activityLists", activityLists);
		return "update_activity_list";
	}
   
   @PostMapping("/activityList")
   public String createActivityList(@ModelAttribute ActivityList activityList, Model model) {
	   activityListService.addActivityList(activityList);
	   ActivityList retrievedActivityList = activityListService.getActivityListByID(activityList.getActivityListID()).get();
	   model.addAttribute("activityList", retrievedActivityList);
	   return "single_activity_list";
   }

   @PutMapping("/updateActivityList")
   public String updateActivityList(@ModelAttribute ActivityList activityList, Model model) {
       activityListService.updateActivityList(activityList.getActivityListID(), activityList);
       ActivityList retrievedActivityList = activityListService.getActivityListByID(activityList.getActivityListID()).get();
       model.addAttribute("activityList", retrievedActivityList);
       return "single_activity_list";
   }

   @GetMapping("/deleteActivityList")
   public String deleteActivityList(@RequestParam(value="activityListID") int activityListID, Model model) {
	   activityListService.deleteActivityList(activityListID);
	   model.addAttribute("activityLists", activityListService.getAllActivityLists());
	   return "list_of_activity_lists";
   }
   
//   @GetMapping("/deleteActivityList")
//   public void deleteActivityList(ActivityList activityList) {
//	   activityListService.deleteActivityList(activityList.getActivityListID());
//   }


   @GetMapping("/deleteAllActivityLists")
   public void deleteAllActivityLists() {
	   activityListService.deleteAllActivityLists();
   }

}

