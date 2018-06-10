package com.fps.models;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import com.fps.constants.Privledge;
import com.fps.constants.Role;

public class Member implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int userId;
	public String identifier;
	public String username;
	public double score;
	public int incompleteJobs;
	public HashMap<Integer, Role> roles;
	public HashMap<Integer,Privledge> privledges;
	public List<Event> events;
	public User user;
	
	public Member() {
		super();
	}
	
	public Member(int userId, String identifier, String username, double score, int incompleteJobs,
			HashMap<Integer, Role> roles, HashMap<Integer, Privledge> privledges, List<Event> events, User user) {
		super();
		this.userId = userId;
		this.identifier = identifier;
		this.username = username;
		this.score = score;
		this.incompleteJobs = incompleteJobs;
		this.roles = roles;
		this.privledges = privledges;
		this.events = events;
		this.user = user;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public List<Event> getEvents() {
		return events;
	}
	public void setEvents(List<Event> events) {
		this.events = events;
	}
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	public int getIncompleteJobs() {
		return incompleteJobs;
	}
	public void setIncompleteJobs(int incompleteJobs) {
		this.incompleteJobs = incompleteJobs;
	}
	public HashMap<Integer, Role> getRoles() {
		return roles;
	}
	public void setRoles(HashMap<Integer, Role> roles) {
		this.roles = roles;
	}
	public HashMap<Integer, Privledge> getPrivledges() {
		return privledges;
	}
	public void setPrivledges(HashMap<Integer, Privledge> privledges) {
		this.privledges = privledges;
	}
	

}
