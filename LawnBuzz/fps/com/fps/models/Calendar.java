package com.fps.models;

import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel(value="Calendar", description="A Calendar consisting of Members and Events")
public class Calendar {
	public int id;
	public String title;
	public String label;
	public Date created;
	public boolean deleted;
	public List<Integer> members;
	public List<Integer> events;
	@ApiModelProperty(value = "ID of the Calendar")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@ApiModelProperty(value = "This Calendar's title")
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@ApiModelProperty(value = "This Calendar's label/description")
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	@ApiModelProperty(value = "The DateTime this Calendar was created")
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	@ApiModelProperty(value = "Whether the Calendar has been deleted")
	public boolean isDeleted() {
		return deleted;
	}
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	@ApiModelProperty(value = "A List of memberIds associated with this Calendar")
	public List<Integer> getMembers() {
		return members;
	}
	public void setMembers(List<Integer> members) {
		this.members = members;
	}
	@ApiModelProperty(value = "A List of eventIds associated with this Calendar")
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
