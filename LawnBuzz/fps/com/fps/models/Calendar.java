package com.fps.models;

import java.util.Date;
import java.util.List;

public class Calendar {
	public int id;
	public String title;
	public String label;
	public Date created;
	public boolean deleted;
	public List<Integer> members;
	public List<Integer> events;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public boolean isDeleted() {
		return deleted;
	}
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	public List<Integer> getMembers() {
		return members;
	}
	public void setMembers(List<Integer> members) {
		this.members = members;
	}
	public List<Integer> getEvents() {
		return events;
	}
	public Calendar(int id, String title, String label, Date created, boolean deleted, List<Integer> members,
			List<Integer> events) {
		super();
		this.id = id;
		this.title = title;
		this.label = label;
		this.created = created;
		this.deleted = deleted;
		this.members = members;
		this.events = events;
	}
	public void setEvents(List<Integer> events) {
		this.events = events;
	}
	public Calendar() {
		
	}
	
	

}
