package com.nguyennghia.FitnessTracker;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.nghianguyen.fitnesstracker.FitnessTrackerApplication;
import com.nghianguyen.fitnesstracker.model.ActivityList;
import com.nghianguyen.fitnesstracker.service.ActivityListService;

/*
 * Tests for ActivityListService
 */
@SpringBootTest(classes = FitnessTrackerApplication.class)
class ActivityListServiceTest {

	@Autowired
	ActivityListService activityListService;
	
	/*
	 * Creates two ActivityLists and compares them. Tests 3 methods: addActivitylist, getActivitylistsByID, 
	 * deleteActivityList
	 */
	@ParameterizedTest
	@CsvSource({
		"Push Ups,'With the tips of your feet and your palms touching the floor, facing downward, start with your elbows extended out "
			+ "and then bend them outward until you hit 90 degrees and then push out to fully extend again.',Chest,Triceps",
		"Sit Ups,'Sitting on the ground, have your knees bent and lie on your back. Then use your abs to bring yourself up as far as "
		+ "you can and lie back down after. Repeat this.',Abs,"
			})
	void testAddFindandDeleteActivityList(String activityName, String description, String majorMuscleGroup, String minorMuscles) {
		ActivityList activityList = new ActivityList(activityName, description, majorMuscleGroup, minorMuscles);
		activityListService.addActivityList(activityList);
		ActivityList retrievedActivityList = activityListService.getActivityListByID(activityList.getActivityListID()).get();
		Assertions.assertEquals(activityList, retrievedActivityList);
		activityListService.deleteActivityList(activityList.getActivityListID());
		Optional<ActivityList> activityListData = activityListService.getActivityListByID(activityList.getActivityListID());
		Assertions.assertTrue(activityListData.isEmpty());
	}
	
	/*
	 * Testing the getPagedActivityLists() method by comparing the results with the expected values
	 * Also tests the getActivityListByID further.
	 */
	@Test
	void testGetPagedActivityLists() {
		List<ActivityList> retrievedList = activityListService.getPagedActivityLists(1, 5);
		List<ActivityList> expectedList = new ArrayList<>();
		expectedList.add(activityListService.getActivityListByID(505).get());
		expectedList.add(activityListService.getActivityListByID(2).get());
		expectedList.add(activityListService.getActivityListByID(3).get());
		expectedList.add(activityListService.getActivityListByID(4).get());
		expectedList.add(activityListService.getActivityListByID(7).get());
		Assertions.assertEquals(expectedList, retrievedList);
	}

}
