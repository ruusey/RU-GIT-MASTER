package com.fps.models;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

import com.fps.constants.EventType;
import com.fps.constants.Outcome;
import com.fps.constants.Requirement;

public class Event {
	public int id;
	public EventType type;
	public Timestamp date;
	public List<Member> members;
	boolean completed;
	public HashMap<Member,Requirement> reqs;
	public HashMap<Member,Outcome> outcomes;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public EventType getType() {
		return type;
	}
	public void setType(EventType type) {
		this.type = type;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	public List<Member> getMembers() {
		return members;
	}
	public void setMembers(List<Member> members) {
		this.members = members;
	}
	public boolean isCompleted() {
		return completed;
	}
	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
	public HashMap<Member, Requirement> getReqs() {
		return reqs;
	}
	public void setReqs(HashMap<Member, Requirement> reqs) {
		this.reqs = reqs;
	}
	public HashMap<Member, Outcome> getOutcomes() {
		return outcomes;
	}
	public void setOutcomes(HashMap<Member, Outcome> outcomes) {
		this.outcomes = outcomes;
	}
	public Event(int id, EventType type, Timestamp date, List<Member> members, boolean completed,
			HashMap<Member, Requirement> reqs, HashMap<Member, Outcome> outcomes) {
		super();
		this.id = id;
		this.type = type;
		this.date = date;
		this.members = members;
		this.completed = completed;
		this.reqs = reqs;
		this.outcomes = outcomes;
	}
	
}
