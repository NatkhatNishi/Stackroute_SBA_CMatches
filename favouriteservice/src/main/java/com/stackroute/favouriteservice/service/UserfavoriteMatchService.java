package com.stackroute.favouriteservice.service;

import java.util.List;

import javax.el.MethodNotFoundException;

import com.stackroute.favouriteservice.model.Match;
import com.stackroute.favouriteservice.model.UserFavouriteMatch;

/**
 * @author NishigandhaomanwarOm
 *
 */
public interface UserfavoriteMatchService {

	List<Match> getAllMatchByUserId(String userId) throws MethodNotFoundException;
	
	Match getMatchByMatchId(String userId,int MatchId) throws MethodNotFoundException;
	
	boolean deleteAllMatches(String userId) throws MethodNotFoundException;
	
	UserFavouriteMatch updateFavourites(UserFavouriteMatch match, String userId) throws MethodNotFoundException;
	
	
	
}
