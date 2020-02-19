package com.stackroute.matchrecommendationservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "MatchRecommendData")
public class MatchRecommendData {

	@Id
	private String matchId;
	public String getMatchId() {
		return matchId;
	}
	public void setMatchId(String matchId) {
		this.matchId = matchId;
	}
	private Match match;
	private int counter = 0;
	
	public Match getMatch() {
		return match;
	}
	public void setMatch(Match match) {
		this.match = match;
	}
	public int getCounter() {
		return counter;
	}
	public void setCounter(int counter) {
		this.counter = counter;
	}
	
	public MatchRecommendData() {
	}
	public MatchRecommendData(Match match, int counter) {
		super();
		this.match = match;
		this.counter = counter;
	}
	
	
}
