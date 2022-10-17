package com.nguyennghia.FitnessTracker;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.nghianguyen.fitnesstracker.FitnessTrackerApplication;
import com.nghianguyen.fitnesstracker.model.Set;
import com.nghianguyen.fitnesstracker.service.SetService;
/*
 * Tests methods related to SetService
 */
@SpringBootTest(classes = FitnessTrackerApplication.class)
class SetServiceTest {

	@Autowired
	SetService setService;
	
	/*
	 * Creates new Set and compares it with the values of Set with id 402.
	 */
	@Test
	void testGetSetByID() {
		Set set = setService.getSetByID(402).get();
		Set expectedSet = new Set(1,1,1);
//		Assertions.assertNotNull(set);
		Assertions.assertEquals(expectedSet.getSetOrder(), set.getSetOrder());
		Assertions.assertEquals(expectedSet.getReps(), set.getReps());
		Assertions.assertEquals(expectedSet.getWeight(), set.getWeight());
		
	}

}
