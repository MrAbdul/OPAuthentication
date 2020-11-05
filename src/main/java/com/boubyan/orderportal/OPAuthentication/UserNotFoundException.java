package com.boubyan.orderportal.OPAuthentication;

public class UserNotFoundException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5704792257122704928L;
	private String userId;
	
	public UserNotFoundException(String userId) {
		// TODO Auto-generated constructor stub
		super(String.format("User is not found with id:'%s'", userId));
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
	