package com.boubyan.orderportal.OPAuthentication.models;

import java.io.Serializable;

public class User implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -1634635581928220415L;


	String id;
	String email;
	String name;
	String password;
	String role;
	public String getRole() {
		return role;
	}



	public void setRole(String role) {
		this.role = role;
	}

	String jwt;

	public String getJwt() {
		return jwt;
	}



	public void setJwt(String jwt) {
		this.jwt = jwt;
	}



	public User() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	


}
