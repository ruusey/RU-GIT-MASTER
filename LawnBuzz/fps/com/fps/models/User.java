package com.fps.models;

import java.io.Serializable;
import java.sql.Timestamp;

public class User implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int id;
	public String firstname;
	public String lastname;
	public String phone;
	public String email;
	public Timestamp birth;
	public boolean verified;
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
	public Timestamp getBirth() {
		return birth;
	}
	public void setBirth(Timestamp birth) {
		this.birth = birth;
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
	
	public User(int id, String firstname, String lastname, String phone, String email, Timestamp birth,
			boolean verified) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.phone = phone;
		this.email = email;
		this.birth = birth;
		this.verified = verified;
	}
	public User() {
		
	}
	
}