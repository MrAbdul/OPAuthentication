package com.boubyan.orderportal.OPAuthentication.utils.messaging;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import org.springframework.stereotype.Component;



@Component
public class MessageModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7898762532243946639L;
	String serviceId;
	String actionId;
	String extraInfo;
	long timeStamp;
	UUID uuid;
	public MessageModel() {

		
	}
	public String getServiceId() {
		return serviceId;
	}
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}
	public String getActionId() {
		return actionId;
	}
	public void setActionId(String actionId) {
		this.actionId = actionId;
	}
	public String getExtraInfo() {
		return extraInfo;
	}
	public void setExtraInfo(String extraInfo) {
		this.extraInfo = extraInfo;
	}
	public long getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}
	public UUID getUuid() {
		return uuid;
	}
	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}
	
	
	}
