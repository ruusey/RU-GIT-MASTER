package com.fps.models;

import java.sql.Timestamp;
import java.util.List;

public class Calendar {
	public int id;
	String title;
	String label;
	Timestamp created;
	boolean deleted;
	List<Member> members;
	List<Event> events;
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
	public Timestamp getCreated() {
		return created;
	}
	public void setCreated(Timestamp created) {
		this.created = created;
	}
	public boolean isDeleted() {
		return deleted;
	}
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	public List<Member> getMembers() {
		return members;
	}
	public void setMembers(List<Member> members) {
		this.members = members;
	}
	public List<Event> getEvents() {
		return events;
	}
	public void setEvents(List<Event> events) {
		this.events = events;
	}
	public Calendar(int id, String title, String label, Timestamp created, boolean deleted, List<Member> members,
			List<Event> events) {
		super();
		this.id = id;
		this.title = title;
		this.label = label;
		this.created = created;
		this.deleted = deleted;
		this.members = members;
		this.events = events;
	}
	

}
