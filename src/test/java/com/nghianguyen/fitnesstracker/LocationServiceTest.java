package com.nghianguyen.fitnesstracker;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.nghianguyen.fitnesstracker.FitnessTrackerApplication;
import com.nghianguyen.fitnesstracker.model.Location;
import com.nghianguyen.fitnesstracker.service.LocationService;

/*
 * Tests LocationService methods
 */
@SpringBootTest(classes = FitnessTrackerApplication.class)
class LocationServiceTest {

	@Autowired
	LocationService locationService;
	
	/*
	 * Checks if the method getAllLocations() returns a list of locations.
	 */
	@Test
	void testGetAllLocations() {
		List<Location> locations = locationService.getAllLocations();
		Assertions.assertNotNull(locations);
	}

}
