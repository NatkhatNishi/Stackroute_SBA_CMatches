package com.stackroute.userservice.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class User {

	
	@Id
	@Column(name = "user_Id")
	private String userid;
	@Column(name = "user_password")
	private String userPassword;
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;
	@Column(name = "user_role")
	private String userRole;
	@Column(name = "user_added_date")
	private Date userAddedDate;

	public User(String userId, String userPassword, String firstName, String lastName, String userRole,
			Date userAddedDate) {
		this.userid = userId;
		this.userPassword = userPassword;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userRole = userRole;
		this.userAddedDate = userAddedDate;
	}
	public User() {
	}
	public String getUserPassword() {
		return userPassword;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	public Date getUserAddedDate() {
		return userAddedDate;
	}
	public void setUserAddedDate(Date userAddedDate) {
		this.userAddedDate = userAddedDate;
	}	
	
}
