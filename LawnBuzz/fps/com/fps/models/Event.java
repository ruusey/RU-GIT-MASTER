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
	public int id;
	public int eventId;
	public Date date;
	public EventType type;
	public List<Integer> memberIds;
	public String description;
	boolean completed;
	
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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Event(int id, int eventId, Date date, EventType type, List<Integer> memberIds, String description,
			boolean completed) {
		super();
		this.id = id;
		this.eventId = eventId;
		this.date = date;
		this.type = type;
		this.memberIds = memberIds;
		this.description = description;
		this.completed = completed;
	}
	public Event() {
		
	}
}
