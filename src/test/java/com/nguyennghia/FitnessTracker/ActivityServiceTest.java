package com.nguyennghia.FitnessTracker;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.nghianguyen.fitnesstracker.FitnessTrackerApplication;
import com.nghianguyen.fitnesstracker.model.Activity;
import com.nghianguyen.fitnesstracker.model.ActivityList;
import com.nghianguyen.fitnesstracker.model.Workout;
import com.nghianguyen.fitnesstracker.service.ActivityListService;
import com.nghianguyen.fitnesstracker.service.ActivityService;
import com.nghianguyen.fitnesstracker.service.WorkoutService;

/*
 * Tests related to ActivityService
 */
@SpringBootTest(classes = FitnessTrackerApplication.class)
class ActivityServiceTest {
	@Autowired
	ActivityService activityService;
	
	@Autowired
	ActivityListService activityListService;
	
	@Autowired
	WorkoutService workoutService;
	
//	@AfterEach
//	public void cleanUpEach() {
//		Activity lastActivity = activityService.findLastAddedActivity();
//		if (lastActivity.getComment().equals("this was added via JUnit testing")) {
//			activityService.deleteActivity(lastActivity.getActivityID());
//		}
//	}
	
	/*
	 * Tests the query to find all activities in a workout and makes sure it's not null.
	 */
	@Test
	void testFindActivitiesInWorkout() {
		Collection<Activity> listOfActivities = activityService.findActivitiesInWorkout(124);
		Assertions.assertNotNull(listOfActivities);
		Assertions.assertTrue(listOfActivities.size() == 4);
		
	}
	
	/*
	 * Tests getting Activity by ID and compares the values.
	 */
	@Test
	void testGetActivityByID() {
		Optional<Activity> actualActivityData = activityService.getActivityById(131);
		Activity actualActivity = null;
		if (actualActivityData.isPresent()) {
			actualActivity = actualActivityData.get();
		}
		ActivityList activityList1 = new ActivityList(1, "Barbell Flat Bench Press", "Lying on a flat bench,"
		+ " pressing a barbell weight upwards with both hands. Lower the barbell to chest level until "
		+ "it touches the chest, then press upwards extending arms out until the elbows are locked out."
		,"Chest", null);
		Workout workout = workoutService.getWorkoutByID(124).get();
		Activity expectedActivity = new Activity(131, "Barbell", activityList1, workout);
//		Assertions.assertEquals(expectedActivity, actualActivity);
		Assertions.assertEquals(expectedActivity.getActivityID(), actualActivity.getActivityID());
		Assertions.assertEquals(expectedActivity.getComment(), actualActivity.getComment());
		Assertions.assertNotNull(expectedActivity.getActivityList());
//		Assertions.assertNotNull(expectedActivity.getSets());
		Assertions.assertNotNull(expectedActivity.getWorkout());
	}

	/*
	 * Tests a variety of methods: addActivity(), findLastAddedActivity(), deleteActivity()
	 */
	@Test
	void testAddActivityAndFindLastAddedActivityAndDelete() {
		Workout workout = workoutService.getWorkoutByID(124).get();
		Activity activity = new Activity("this was added via JUnit testing", workout);
		activityService.addActivity(activity);
		Activity retrievedActivity = activityService.findLastAddedActivity().get();
//		Assertions.assertEquals(activity, retrievedActivity);
//		activityService.deleteActivity(retrievedActivity.getActivityID());
		Assertions.assertEquals(activity.getActivityID(), retrievedActivity.getActivityID());
		Assertions.assertEquals(activity.getComment(), retrievedActivity.getComment());
		Assertions.assertNotNull(activity.getWorkout());
		activityService.deleteActivity(activity.getActivityID());
		retrievedActivity = activityService.findLastAddedActivity().get();
		Assertions.assertNotEquals(activity.getActivityID(), retrievedActivity.getActivityID());
	}
	
	@Test
	void testFindCountOfMuscleGroups() {
		List<String> muscleGroups = activityService.findCountOfMuscleGroups(16);
		List<String> expectedMuscleGroups = new ArrayList<>();
		expectedMuscleGroups.add("Chest=3");
		expectedMuscleGroups.add("Tricep=3");
		Assertions.assertEquals(expectedMuscleGroups, muscleGroups);
	}
}
