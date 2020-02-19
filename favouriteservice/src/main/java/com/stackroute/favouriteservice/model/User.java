package com.stackroute.favouriteservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "User")
public class User {
	
	@Id
	private  String id;
	private String username;
	private String userpassword;
	private String userAddeddate;
	
	public User() {
	}
	public User(String id, String username, String userpassword, String userAddeddate) {
		this.id = id;
		this.username = username;
		this.userpassword = userpassword;
		this.userAddeddate = userAddeddate;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserpassword() {
		return userpassword;
	}
	public void setUserpassword(String userpassword) {
		this.userpassword = userpassword;
	}
	public String getUserAddeddate() {
		return userAddeddate;
	}
	public void setUserAddeddate(String userAddeddate) {
		this.userAddeddate = userAddeddate;
	}
	
}
