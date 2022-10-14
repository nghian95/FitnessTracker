package com.nghianguyen.FitnessTracker.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nghianguyen.FitnessTracker.model.Activity;
import com.nghianguyen.FitnessTracker.model.Set;
import com.nghianguyen.FitnessTracker.repository.ActivityRepository;
import com.nghianguyen.FitnessTracker.repository.SetRepository;

@Service
public class SetService {
	
//	@Autowired
//	SetRepository setRepository;
//	
//	@Autowired
//	ActivityRepository activityRepository;
//	
//	public List<Set> getAllSets() {
//		return setRepository.findAll();
//	}
//	
//	public Optional<Set> getSetByID(Integer id) {
//		return setRepository.findById(id);
//	}
//	
//	public int getSetIDByProperties(int setOrder, int reps, int weight) {
//		return setRepository.getSetIDByProperties(setOrder, reps, weight);
//	}
//	
//	public void addSet(Set set) {
//		setRepository.save(set);
//	}
//	
//	public void updateSet(Integer id, Set set) {
//		Optional<Set> setData = setRepository.findById(id);
//		
//		if (setData.isPresent()) {
//			Set updatedSet = setData.get();
//			updatedSet.setActivity(set.getActivity());
//			updatedSet.setReps(set.getReps());
//			updatedSet.setWeight(set.getWeight());
////			setRepository.deleteById(id);
//			setRepository.save(updatedSet);
//			
//		}
//	}
//	
//	public void addActivityToSet(Integer setID, Integer activityID) {
//		Optional<Set> setData = setRepository.findById(setID);
//		Optional<Activity> activityData = activityRepository.findById(activityID);
//		if (activityData.isPresent() && setData.isPresent()) {
//			Set set = setData.get();
//			Activity activity = activityData.get();
//
////			if (set.getActivity() != null) {
////				set.getActivity().add(activity);
////			} else {
////				List<Activity> activities = new ArrayList<>();
////				activities.add(activity);
////				set.setActivity(activities);
////			}
////			System.out.println(set.getActivity());
////			setRepository.save(set);
//			
//			activity.addSet(set);
//			activityRepository.save(activity);
//			
////			SessionFactory factory = new Configuration().configure().buildSessionFactory();
////			Session session = factory.openSession();
////			session.save(set);
//		}
//	}
//	
//	public void deleteSet(Integer id) {
//		setRepository.deleteById(id);
//	}
//	
//	public void deleteAllSets() {
//		setRepository.deleteAll();
//	}
}
