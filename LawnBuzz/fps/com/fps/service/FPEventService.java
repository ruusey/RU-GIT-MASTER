package com.fps.service;

import java.util.List;

import com.fps.constants.EventType;
import com.fps.constants.Outcome;
import com.fps.constants.Requirement;
import com.fps.constants.Role;
import com.fps.models.Event;

public interface FPEventService {
	public int registerEvent(Event event);
	public Event getEventById(int id);
	public List<Integer> getUserIdsByEvent(int eventId);
	public List<Requirement> getUserEventRequirements(int eventId,int userId);
	public List<Outcome> getUserEventOutcomes(int eventId,int userId);
	public List<Event> getEventsByType(EventType type);
	public void addEventMemberRole(int eventId,int userId, Role role);
	public void addEventMemberOutcome(int eventId,int userId, Outcome outcome);
	public void addEventMemberRequirement(int eventId,int userId, Requirement requirement);
}
