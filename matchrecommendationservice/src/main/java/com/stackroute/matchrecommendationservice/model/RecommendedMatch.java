/**
 * 
 */
package com.stackroute.matchrecommendationservice.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author NishigandhaomanwarOm
 *
 */
@Document(collection = "RecomendedMatches")
public class RecommendedMatch {
	@Id
	private String userId;
	private List<Match> recommendedmatchList;
	
	
	public RecommendedMatch() {}


	public RecommendedMatch(String userId, List<Match> recommendedmatchList) {
		super();
		this.userId = userId;
		this.recommendedmatchList = recommendedmatchList;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public List<Match> getRecommendedmatchList() {
		return recommendedmatchList;
	}


	public void setRecommendedmatchList(List<Match> recommendedmatchList) {
		this.recommendedmatchList = recommendedmatchList;
	}
	
	

	
	
}
