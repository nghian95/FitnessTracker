package com.nghianguyen.fitnesstracker.controller;

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

import com.nghianguyen.fitnesstracker.model.ActivityList;
import com.nghianguyen.fitnesstracker.service.ActivityListService;
/*
 * Controller that maps the requests related to ActivityList. Including CRUD operations.
 */
@Controller
public class ActivityListController {

	@Autowired
    private ActivityListService activityListService; 
	
	/*
	 * Returns a view of all of the ActivityLists in the databases.
	 */
	@GetMapping("/activityList")
   public String getAllActivityLists(Model model)         {
       model.addAttribute("activityLists", activityListService.getAllActivityLists());
       return "list_of_activity_lists";
   }

	/*
	 * Shows a single ActivityList based on it's activityListID
	 */
   @GetMapping("/activityList/{id}")
   public String getActivityListById(@PathVariable("id") int id, Model model) {
       model.addAttribute("activityList", activityListService.getActivityListByID(id));
       return "single_activity_list";
   }
   
	/*
	 * Returns a view where you can add a new ActivityList
	 */
	@GetMapping("/addActivityList")
	public String activityListForm(Model model) {
//		List<ActivityList> activityLists = activityListService.getAllActivityLists();
//		Set<String> activityListNames = activityLists.stream().map(ActivityList::getActivityListName).collect(Collectors.toSet());
//		model.addAttribute("activityListNames", activityListNames);
	   	return "add_activity_list";
	}
	
	/*
	 * Returns a view where you update a pre-existing ActivityList which is retrieved based on it's activityListID
	 * Both the ActivityList and all ActivityLists are passed to the Model.
	 */
	@GetMapping("/updateActivityList")
	public String updateActivityList(@RequestParam(value="activityListID") int activityListID, Model model) {
		ActivityList activityList = activityListService.getActivityListByID(activityListID).get();
		model.addAttribute("activityList", activityList);
		List<ActivityList> activityLists = activityListService.getAllActivityLists();
		model.addAttribute("activityLists", activityLists);
		return "update_activity_list";
	}
   
	/*
	 * Creates the ActivityList based on the form data submitted. Passes the retrieved ActivityList from the database
	 * to the view to show that the updates were made successfully.
	 */
   @PostMapping("/activityList")
   public String createActivityList(@ModelAttribute ActivityList activityList, Model model) {
	   activityListService.addActivityList(activityList);
	   ActivityList retrievedActivityList = activityListService.getActivityListByID(activityList.getActivityListID()).get();
	   model.addAttribute("activityList", retrievedActivityList);
	   return "single_activity_list";
   }

	/*
	 * Updates the ActivityList based on the changed properties passed in the activityList parameter.
	 * Passes retrieved ActivityList from database to the view to confirm the changes.
	 */
   @PutMapping("/updateActivityList")
   public String updateActivityList(@ModelAttribute ActivityList activityList, Model model) {
       activityListService.updateActivityList(activityList.getActivityListID(), activityList);
       ActivityList retrievedActivityList = activityListService.getActivityListByID(activityList.getActivityListID()).get();
       model.addAttribute("activityList", retrievedActivityList);
       return "single_activity_list";
   }

	/*
	 * Deletes the ActivityList based on the activityListID @RequestParam passed. 
	 * Returns a view of the list of ActivityLists.
	 */
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

	/*
	 * Deletes all ActivityLists. Meant for admin use.
	 */
   @GetMapping("/deleteAllActivityLists")
   public void deleteAllActivityLists() {
	   activityListService.deleteAllActivityLists();
   }

}

