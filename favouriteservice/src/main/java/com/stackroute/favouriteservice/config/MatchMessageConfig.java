package com.stackroute.favouriteservice.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MatchMessageConfig {

	private String exchangeName="favourite_exchange_match";
	
	private String favouritematchQueue="favourite_match";
	
	@Bean
	public Binding bindingQueues(Queue favouriteNews, DirectExchange directExchange) {
		return BindingBuilder.bind(favouriteNews).to(directExchange).with("favourite_match_routing_key");
	}
	
	
	@Bean
	public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
		return new Jackson2JsonMessageConverter();
	}
	
	@Bean
	public DirectExchange directExchange() {
		return new DirectExchange(exchangeName);
	}
	
	@Bean
	public Queue favouriteMatch() {
		return new Queue(favouritematchQueue,true);
	}
	
	@Bean
	public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
		RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(jackson2JsonMessageConverter());
		return rabbitTemplate;
	}
}