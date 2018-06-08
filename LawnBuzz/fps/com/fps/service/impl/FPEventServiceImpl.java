package com.fps.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fps.constants.EventType;
import com.fps.constants.Requirement;
import com.fps.mappers.FPEventMapper;
import com.fps.models.Event;
import com.fps.models.User;
import com.fps.service.FPEventService;
@Service("fpEventService")
public class FPEventServiceImpl implements FPEventService{
	@Autowired
	private FPEventMapper mapper;
	public FPEventServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int registerEvent(Event event) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Event getEventById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Event> getEventsByType(EventType type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void registerEventRoles(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void registerEventRequirements(User newUser) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void registerEventOutcomes() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Integer> getUserIdsByEvent(int eventId) {
		return mapper.getUsersByEvent(eventId);
	}

	@Override
	public List<Requirement> getUserEventRequirements(int eventId, int userId) {
		return mapper.getUserEventRequirements(eventId, userId);
	}

}
