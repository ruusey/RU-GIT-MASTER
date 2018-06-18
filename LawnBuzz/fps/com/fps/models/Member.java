package com.fps.models;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel(value="Member", description="A Member associated with a Calendar or Event")
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
	@ApiModelProperty(value = "ID of this member")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@ApiModelProperty(value = "First name of member")
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	@ApiModelProperty(value = "Last name of member")
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	@ApiModelProperty(value = "Member's phone number")
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@ApiModelProperty(value = "Member's date of birth")
	public Date getDob() {
		return dob;
	}
	public void setBirth(Date dob) {
		this.dob = dob;
	}
	@ApiModelProperty(value = "Whether the member has been verified")
	public boolean isVerified() {
		return verified;
	}
	public void setVerified(boolean verified) {
		this.verified = verified;
	}
	@ApiModelProperty(value = "Member's email address")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@ApiModelProperty(value = "Member's username")
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@ApiModelProperty(value = "Member's unique identifier")
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	@ApiModelProperty(value = "Member's overall score")
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