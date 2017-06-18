package com.lawnbuzz.models;

import java.io.Serializable;

public class JobRequest implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private Service service;
	private String shortDescription;
	private String longDescription;
	private GeoLocation loc;
	private double pay;
	private boolean complete;

	public JobRequest() {
		super();
	}

	public JobRequest(int id, Service job, String shortDescription,
			String longDescrption, GeoLocation loc, double pay, boolean complete) {
		super();
		this.id = id;
		this.service = job;
		this.shortDescription = shortDescription;
		this.longDescription = longDescrption;
		this.loc = loc;
		this.pay = pay;
		this.complete = complete;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getLongDescription() {
		return longDescription;
	}

	public void setLongDescrption(String longDescription) {
		this.longDescription = longDescription;
	}

	public GeoLocation getLoc() {
		return loc;
	}

	public void setLoc(GeoLocation loc) {
		this.loc = loc;
	}

	public double getPay() {
		return pay;
	}

	public void setPay(double pay) {
		this.pay = pay;
	}

	public boolean isComplete() {
		return complete;
	}

	public void setComplete(boolean complete) {
		this.complete = complete;
	}

}
