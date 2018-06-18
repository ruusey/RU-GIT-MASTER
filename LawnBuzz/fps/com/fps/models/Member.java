package com.fps.models;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Member", description = "A Member associated with a Calendar or Event")
public class Member implements Serializable {

  private static final long serialVersionUID = 1L;

  @ApiModelProperty(value = "ID of a Member")
  private int id;

  @ApiModelProperty(value = "First name of a Member")
  private String firstname;

  @ApiModelProperty(value = "Last name of a Member")
  private String lastname;

  @ApiModelProperty(value = "Member's phone number")
  private String phone;

  @ApiModelProperty(value = "Member's email address")
  private String email;

  @ApiModelProperty(value = "Member's date of birth")
  private Date dob;

  @ApiModelProperty(value = "Member's verification status")
  private boolean verified;

  @ApiModelProperty(value = "Member's username")
  private String username;

  @ApiModelProperty(value = "Member's unique identifier")
  private String identifier;

  @ApiModelProperty(value = "Member's overall score")
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

  public Member(
      int id,
      String firstname,
      String lastname,
      String phone,
      String email,
      Date dob,
      boolean verified,
      String username,
      String identifier,
      double score) {
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
