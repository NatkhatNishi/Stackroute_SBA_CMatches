package com.stackroute.matchrecommendationservice.service;

import java.util.List;
import com.stackroute.matchrecommendationservice.model.Match;
import com.stackroute.matchrecommendationservice.model.MatchRecommendData;
import com.stackroute.matchrecommendationservice.model.UserFavouriteMatch;

public interface MatchRecommendationService {
	
	public List<?> filteruniqueData(List<Match> matchList);
	
	public void updateRecommendedData(UserFavouriteMatch UserFavouriteMatch);
	
	public List<Match> getAllRecommendedMatchList();
	public List<MatchRecommendData> getAllRecommendations();
	public void updateRecomendations(Match match);
	

}
