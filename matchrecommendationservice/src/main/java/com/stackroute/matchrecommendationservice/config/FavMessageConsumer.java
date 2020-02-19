package com.stackroute.matchrecommendationservice.config;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.stackroute.matchrecommendationservice.model.UserFavouriteMatch;
import com.stackroute.matchrecommendationservice.service.MatchRecommendationService;

@Component
public class FavMessageConsumer {

	@Autowired
	MatchRecommendationService servive;

	@RabbitListener(queues = "favourite_match")
	public void getFavMatch(UserFavouriteMatch match) {
		servive.updateRecommendedData(match);
	}

//	@RabbitListener(queues = "favourite_match")
//	public void getFavMatch(Match match) {
//	servive.updateRecomendations(match);
//}
}
