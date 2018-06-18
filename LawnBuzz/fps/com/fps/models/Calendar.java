package com.fps.models;

import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel(value="Calendar", description="A Calendar consisting of Members and Events")
public class Calendar {
	@ApiModelProperty(value = "ID of the Calendar")
	private int id;
	@ApiModelProperty(value = "Title of a Calendar")
	private String title;
	@ApiModelProperty(value = "Description of a Calendar")
	private String label;
	@ApiModelProperty(value = "DateTime a Calendar was created")
	private Date created;
	@ApiModelProperty(value = "Calendar deleted or not")
	private boolean deleted;
	@ApiModelProperty(value = "A List of memberIds associated with this Calendar")
	private List<Integer> members;
	@ApiModelProperty(value = "A List of eventIds associated with this Calendar")
	private List<Integer> events;
	
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
