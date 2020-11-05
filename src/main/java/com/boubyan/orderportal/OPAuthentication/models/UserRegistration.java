package com.boubyan.orderportal.OPAuthentication.models;

public class UserRegistration {

	String email;
	String name;
	String password;
	String repeatPassword;
	String role;


	

	 public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	public UserRegistration() {
		// TODO Auto-generated constructor stub
	}


	public String getRepeatPassword() {
		return repeatPassword;
	}


	public void setRepeatPassword(String repeatPassword) {
		this.repeatPassword = repeatPassword;
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
