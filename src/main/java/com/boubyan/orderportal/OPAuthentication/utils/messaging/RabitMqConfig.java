package com.boubyan.orderportal.OPAuthentication.utils.messaging;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import 
@Configuration
public class RabitMqConfig {
	@Value("${queue.audit.messagingQueue}")
	 private String queueName;
	@Bean
	public Queue testQueue() {
		return new Queue(queueName, true);
		
	}
	

}
