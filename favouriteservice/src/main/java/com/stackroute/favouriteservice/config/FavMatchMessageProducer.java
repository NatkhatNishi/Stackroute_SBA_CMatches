package com.stackroute.favouriteservice.config;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.stackroute.favouriteservice.model.UserFavouriteMatch;

@Component
public class FavMatchMessageProducer {
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@Autowired
	private DirectExchange exchange;
	
	public FavMatchMessageProducer(RabbitTemplate rabbitTemplate, DirectExchange exchange) {
		super();
		this.rabbitTemplate = rabbitTemplate;
		this.exchange = exchange;
	}
	
	public void sendFavMatch(UserFavouriteMatch match) {
		rabbitTemplate.convertAndSend(exchange.getName(), "favourite_match_routing_key", match);
	}
	
	
}