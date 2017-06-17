package com.lawnbuzz.models;

public class JobRequest {
	private int id;
	private Service service;
	private String shortDescripion;
	private String longDescrption;
	private GeoLocation loc;
	private double pay;
	private boolean complete;

	public JobRequest() {
		super();
	}

	public JobRequest(int id, Service job, String shortDescripion,
			String longDescrption, GeoLocation loc, double pay, boolean complete) {
		super();
		this.id = id;
		this.service = job;
		this.shortDescripion = shortDescripion;
		this.longDescrption = longDescrption;
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

	public String getShortDescripion() {
		return shortDescripion;
	}

	public void setShortDescripion(String shortDescripion) {
		this.shortDescripion = shortDescripion;
	}

	public String getLongDescrption() {
		return longDescrption;
	}

	public void setLongDescrption(String longDescrption) {
		this.longDescrption = longDescrption;
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
