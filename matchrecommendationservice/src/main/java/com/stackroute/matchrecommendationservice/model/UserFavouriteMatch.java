package com.stackroute.matchrecommendationservice.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author NishigandhaomanwarOm
 *
 */

@Document(collection = "UserFavouriteMatch")
public class UserFavouriteMatch {
	
	@Id
	private String userId;
	private List<Match> matchList;
	
	public UserFavouriteMatch() {}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String favMatchId) {
		this.userId = favMatchId;
	}

	public List<Match> getMatchList() {
		return matchList;
	}

	public void setMatchList(List<Match> matchList) {
		this.matchList = matchList;
	}

	public UserFavouriteMatch(String userId, List<Match> matchList) {
		super();
		this.userId = userId;
		this.matchList = matchList;
	}

	
}
