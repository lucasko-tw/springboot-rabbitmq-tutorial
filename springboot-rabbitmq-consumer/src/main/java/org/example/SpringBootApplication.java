package org.example;

import org.example.service.ComsumrerService;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
 


@org.springframework.boot.autoconfigure.SpringBootApplication
public class SpringBootApplication {
  

	public final static String SFG_MESSAGE_QUEUE = "test-message-queue";

	@Bean
	Queue queue() {
		return new Queue(SFG_MESSAGE_QUEUE, false);
	}

	@Bean
	TopicExchange exchange() {
		return new TopicExchange("spring-boot-exchange");
	}

	@Bean
	Binding binding(Queue queue, TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(SFG_MESSAGE_QUEUE);
	}

	@Bean
	SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
	MessageListenerAdapter listenerAdapter) {
		System.out.println("567...");
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setQueueNames(SFG_MESSAGE_QUEUE);
		container.setMessageListener(listenerAdapter);
		return container;	
	}

	@Bean
	MessageListenerAdapter listenerAdapter(ComsumrerService receiver) {
		System.out.println("123...");
		return new MessageListenerAdapter(receiver, "receiveMessage");
	}

	public static void main(String[] args) {
		
		 SpringApplication app = new SpringApplication(SpringBootApplication.class);
		 app.setWebApplicationType(WebApplicationType.NONE ); // disable web application
	
		 ConfigurableApplicationContext ctx = app.run(args);
		
		 
	}


  
}