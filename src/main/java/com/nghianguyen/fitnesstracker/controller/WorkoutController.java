package com.nghianguyen.fitnesstracker.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nghianguyen.fitnesstracker.model.Location;
import com.nghianguyen.fitnesstracker.model.User;
import com.nghianguyen.fitnesstracker.model.Workout;
import com.nghianguyen.fitnesstracker.service.ActivityService;
import com.nghianguyen.fitnesstracker.service.LocationService;
import com.nghianguyen.fitnesstracker.service.UserServiceImpl;
import com.nghianguyen.fitnesstracker.service.WorkoutService;

/*
 * Controller that handles the mapping for all CRUD HTTP requests related to Workout entity
 */
@Controller
public class WorkoutController {
	
	private static final Logger log = LoggerFactory.getLogger(WorkoutController.class);
	
	@Autowired
	WorkoutService workoutService;
	
	@Autowired
	LocationService locationService;
	
	@Autowired
	UserServiceImpl userServiceImpl;
	
	@Autowired
	ActivityService activityService;
	
	/*
	 * Gets a list of all the workouts by calling getAllWorkouts() from WorkoutService,
	 * irrespective of who the current user is. Returns a view of the entire list of workouts.
	 * Meant for admin usage
	 */
	@GetMapping("/admin/workout")
	public String getAllWorkouts(Model model) {
		model.addAttribute("listOfWorkouts",workoutService.getAllWorkouts());
		return "list_of_workouts";
	}
	
	/*
	 * Gets the user's email by using the Principal from Spring Security. Then uses method 
	 * findWorkoutsByUser in WorkoutService to get a list of workouts specific to the user.
	 * Returns that list to a view via Model.
	 */
	@GetMapping("/workout")
	public String findWorkoutsByUser(Model model, Principal principal) {
		Collection<Workout> userWorkouts = workoutService.findWorkoutsByUser(principal.getName());
		model.addAttribute("listOfWorkouts", userWorkouts);
		List<List<String>> listOfWorkoutsMuscleGroups = new ArrayList<>();
		for (Workout workout : userWorkouts) {
			List<String> muscleGroupCount = activityService.findCountOfMuscleGroups(workout.getWorkoutID());
			listOfWorkoutsMuscleGroups.add(muscleGroupCount);
		}
		model.addAttribute("listOfWorkoutsMuscleGroups", listOfWorkoutsMuscleGroups);
		return "list_of_workouts";
	}
	
	/*
	 * Gets a single workout by calling getWorkoutByID from WorkoutService and passing the 
	 * @PathVariable id to it. The workout and list of activities associated with that workout
	 * are added to the Model.
	 */
	@GetMapping("/workout/{id}")
	public String getWorkoutByID(@PathVariable("id") int id, Model model) {
		model.addAttribute("workout", workoutService.getWorkoutByID(id).get());
		model.addAttribute("listOfActivities", activityService.findActivitiesInWorkout(id));
		List<String> namesOfActivities = activityService.findCountOfMuscleGroups(id);
		model.addAttribute("namesOfActivities", namesOfActivities);
		return "single_workout";
	}
	
	/*
	 * This is to return the view where user can add a new workout. Uses Model to pass a list
	 * of locations.
	 */
	@GetMapping("/addWorkout")
	public String workoutForm(Model model) {
		model.addAttribute("locations", locationService.getAllLocations());
	   	return "add_workout";
	}
	
	/*
	 * This is to return a view where user can update their workout. By using @RequestParam 
	 * workoutID the method getWorkoutByID is able to be used to add the Workout to the Model.
	 * List of locations is also provided to Model.
	 */
	@GetMapping("/updateWorkout")
	public String updateWorkout(@RequestParam(value="workoutID") int workoutID, Model model) {
		Workout workout = workoutService.getWorkoutByID(workoutID).get();
		model.addAttribute("workout", workout);
		List<Location> locations = locationService.getAllLocations();
		model.addAttribute("locations", locations);
		return "update_workout";
   }
	
	/*
	 * This creates a workout based on user inputted values that are mapped via @ModelAttribute
	 * Workout. Spring Security Principal is used to find the user and add it to the workout.
	 * addWorkout() method is used to add it, then the updated workout is retrieved and added to 
	 * the Model.
	 */
	@PostMapping("/workout")
	public String addWorkout(@ModelAttribute Workout workout, Model model, Principal principal) {
		User user = userServiceImpl.findByEmail(principal.getName()).get();
		workout.setUser(user);
		workoutService.addWorkout(workout);
		Workout retrievedWorkout = workoutService.getWorkoutByID(workout.getWorkoutID()).get();
		model.addAttribute("workout", retrievedWorkout);
		model.addAttribute("listOfActivities", activityService.findActivitiesInWorkout(workout.getWorkoutID()));
		List<String> namesOfActivities = activityService.findCountOfMuscleGroups(workout.getWorkoutID());
		model.addAttribute("namesOfActivities", namesOfActivities);
		return "single_workout";
	}
	
	/*
	 * @ModelAttribute Workout takes in updated parameters such as the WorkoutDate or Location 
	 * from a form and updates the workout in the database with the updated values using WorkoutService.
	 * Updated workout is then retrieved and passed to the view via Model
	 */
	@PutMapping("/updateWorkout")
	public String updateWorkout(@ModelAttribute Workout workout, Model model) {
	       Optional<Workout> workoutData = workoutService.getWorkoutByID(workout.getWorkoutID());

	       if (workoutData.isPresent()) {
	    	   Workout updateWorkout = workoutData.get();
	    	   updateWorkout.setWorkoutDate(workout.getWorkoutDate());
	    	   updateWorkout.setLocation(workout.getLocation());
	           workoutService.addWorkout(updateWorkout);
	       }
	       
		   Workout retrievedWorkout = workoutService.getWorkoutByID(workout.getWorkoutID()).get();
		   model.addAttribute("workout", retrievedWorkout);
		   
		   model.addAttribute("listOfActivities", activityService.findActivitiesInWorkout(workout.getWorkoutID()));
		   List<String> namesOfActivities = activityService.findCountOfMuscleGroups(workout.getWorkoutID());
		   model.addAttribute("namesOfActivities", namesOfActivities);
	       return "single_workout";
	}
	
	/*
	 * This is used to delete a specific workout that's specified via @RequestParam workoutID using
	 * WorkoutService's method deleteWorkoutID(). Using Principal and Model we pass a list of Workouts
	 * related to the User to the view.
	 */
	@GetMapping("/deleteWorkout")
	public String deleteWorkout(@RequestParam(value="workoutID") int workoutID, Model model, Principal principal) {
		workoutService.deleteWorkoutByID(workoutID);
		String email = principal.getName();
		model.addAttribute("listOfWorkouts", workoutService.findWorkoutsByUser(email));
		return "list_of_workouts";
	}
	
	/*
	 * Meant for admin use. Deletes all workouts by calling WorkoutService's method deleteAllWorkouts()
	 */
	@DeleteMapping("/deleteWorkout") 
	public void deleteAllWorkouts() {
		workoutService.deleteAllWorkouts();
	}
}
