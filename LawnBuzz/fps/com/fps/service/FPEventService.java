package com.fps.service;

import java.util.List;

import com.fps.constants.EventType;
import com.fps.constants.Requirement;
import com.fps.models.Event;
import com.fps.models.Response;
import com.fps.models.User;

public interface FPEventService {
	public int registerEvent(Event event);
	public Event getEventById(int id);
	public List<Integer> getUserIdsByEvent(int eventId);
	public List<Requirement> getUserEventRequirements(int eventId,int userId);
	public List<Event> getEventsByType(EventType type);
	public void registerEventRoles(int id);
	public void registerEventRequirements(User newUser);
	public void registerEventOutcomes();
}
