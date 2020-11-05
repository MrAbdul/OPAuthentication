package com.boubyan.orderportal.OPAuthentication.models;



//fields need to be encapsulated with getters and sitters so springboot can convert them to json
public class NotFound{
	String msg;
	public NotFound() {
		// TODO Auto-generated constructor stub
	}
	

	public void setMsg(String msg) {
		this.msg = msg;
		
	}
	public String getMsg() {
		return msg;
	}

}
