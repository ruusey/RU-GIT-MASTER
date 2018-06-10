package com.fps.models;

import java.io.Serializable;
import java.util.Date;

public class Member implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	private int id;
	private String firstname;
	private String lastname;
	private String phone;
	private String email;
	private Date dob;
	private boolean verified;
	private String username;
	private String identifier;
	private double score;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Date getDob() {
		return dob;
	}
	public void setBirth(Date dob) {
		this.dob = dob;
	}
	public boolean isVerified() {
		return verified;
	}
	public void setVerified(boolean verified) {
		this.verified = verified;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	
	public Member(int id, String firstname, String lastname, String phone, String email, Date dob, boolean verified,
			String username, String identifier, double score) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.phone = phone;
		this.email = email;
		this.dob = dob;
		this.verified = verified;
		this.username = username;
		this.identifier = identifier;
		this.score = score;
	}
	public Member() {
		super();
	}
	
}