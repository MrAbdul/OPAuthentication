package com.boubyan.orderportal.OPAuthentication.utils.messaging;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.github.f4b6a3.uuid.UuidCreator;

@Component
//@RabbitListener(queues = "${queue.audit.messagingQueue}")
public class RabitMqHelper {
	
	@Value("${queue.serviceName}")
	 private String SERVICE_ID;
	
	
	@Value("${queue.audit.messagingQueue}")
	 private String queueName;
	
	@Autowired
	RabbitTemplate rabbitTemplate;
	
	public  void sendMessage( String actionId,String extraInfo) {
		Date date = new Date();
		long timeStamp=date.getTime();
		UUID uuid = UuidCreator.getTimeBased();
//		MessageModel messageModel = new MessageModel();
		Map<String, Object> message = new HashMap<String, Object>();
		message.put("actionId", actionId);
		message.put("extraInfo", extraInfo);
		message.put("SERVICE_ID", SERVICE_ID);
		message.put("timeStamp", timeStamp);
		message.put("uuid", uuid);
//		messageModel.setActionId(actionId);
//		messageModel.setExtraInfo(extraInfo);
//		messageModel.setServiceId(SERVICE_ID);
//		messageModel.setTimeStamp(timeStamp);
//		messageModel.setUuid(uuid);
		rabbitTemplate.convertAndSend(queueName,message);
		
	}
//	@RabbitHandler
//	public void recive(MessageModel mm) {
//		System.out.println("message model"+mm.getExtraInfo());
//	}
	
}
