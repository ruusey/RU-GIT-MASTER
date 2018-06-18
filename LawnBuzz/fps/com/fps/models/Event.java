package com.fps.models;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.fps.constants.EventType;
import com.fps.constants.Outcome;
import com.fps.constants.Requirement;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel(value="Event", description="An Event contained within a Calendar")
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
	@ApiModelProperty(value = "Id of the Event")
	public int getEventId() {
		return eventId;
	}
	public void setEventId(int eventId) {
		this.eventId = eventId;
	}
	@ApiModelProperty(value = "Date and time of the Event")
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	@ApiModelProperty(value = "Type of event (EventType)")
	public EventType getType() {
		return type;
	}
	public void setType(EventType type) {
		this.type = type;
	}
	@ApiModelProperty(value = "List of memberIds associated with this Event")
	public List<Integer> getMemberIds() {
		return memberIds;
	}
	public void setMemberIds(List<Integer> memberIds) {
		this.memberIds = memberIds;
	}
	@ApiModelProperty(value = "If the event has been completed or not")
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
	@ApiModelProperty(value = "Description of the event")
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
