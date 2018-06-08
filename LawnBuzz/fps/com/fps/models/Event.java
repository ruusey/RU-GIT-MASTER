package com.fps.models;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.fps.constants.EventType;
import com.fps.constants.Outcome;
import com.fps.constants.Requirement;

public class Event implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int eventId;
	public Date date;
	public EventType type;
	public List<Integer> memberIds;
	boolean completed;
	public Map<Integer,Requirement> memberRequirements;
	public Map<Integer,Outcome> memberOutcomes;
	public int getEventId() {
		return eventId;
	}
	public void setEventId(int eventId) {
		this.eventId = eventId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public EventType getType() {
		return type;
	}
	public void setType(EventType type) {
		this.type = type;
	}
	public List<Integer> getMemberIds() {
		return memberIds;
	}
	public void setMemberIds(List<Integer> memberIds) {
		this.memberIds = memberIds;
	}
	public boolean isCompleted() {
		return completed;
	}
	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
	public Map<Integer, Requirement> getMemberRequirements() {
		return memberRequirements;
	}
	public void setMemberRequirements(Map<Integer, Requirement> memberRequirements) {
		this.memberRequirements = memberRequirements;
	}
	public Map<Integer, Outcome> getMemberOutcomes() {
		return memberOutcomes;
	}
	public void setMemberOutcomes(Map<Integer, Outcome> memberOutcomes) {
		this.memberOutcomes = memberOutcomes;
	}
	public Event(int eventId, Date date, EventType type, List<Integer> memberIds, boolean completed,
			Map<Integer, Requirement> memberRequirements, Map<Integer, Outcome> memberOutcomes) {
		super();
		this.eventId = eventId;
		this.date = date;
		this.type = type;
		this.memberIds = memberIds;
		this.completed = completed;
		this.memberRequirements = memberRequirements;
		this.memberOutcomes = memberOutcomes;
	}
	public Event() {
		super();
	}
}
