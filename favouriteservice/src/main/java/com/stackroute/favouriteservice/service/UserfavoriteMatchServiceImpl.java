package com.stackroute.favouriteservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.el.MethodNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.favouriteservice.model.Match;
import com.stackroute.favouriteservice.model.UserFavouriteMatch;
import com.stackroute.favouriteservice.repository.UserFavouriteMatchRepository;

/**
 * @author NishigandhaomanwarOm
 *
 */

@Service
public class UserfavoriteMatchServiceImpl implements UserfavoriteMatchService {

	@Autowired
	private UserFavouriteMatchRepository repo;

	public UserfavoriteMatchServiceImpl() {
	}

	@Autowired
	public UserfavoriteMatchServiceImpl(UserFavouriteMatchRepository userfavouriteMatchRepository) {
		this.repo = userfavouriteMatchRepository;
	}

	public UserFavouriteMatchRepository getUserfavouriteMatchRepository() {
		return repo;
	}

	public void setUserfavouriteMatchRepository(UserFavouriteMatchRepository userfavouriteMatchRepository) {
		this.repo = userfavouriteMatchRepository;
	}

	@Override
	public List<Match> getAllMatchByUserId(String userId) throws MethodNotFoundException {
		try {
			Optional<UserFavouriteMatch> favMatch = repo.findById(userId);

			if (favMatch.isPresent()) {
				List<Match> list = favMatch.get().getMatchList();
				return list;
			} else {
				return new ArrayList<Match>();
			}
		} catch (Exception e) {
			e.getMessage();

		}

		return null;
	}

	@Override
	public Match getMatchByMatchId(String userId, int MatchId) throws MethodNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteAllMatches(String userId) throws MethodNotFoundException {

		repo.deleteById(userId);
		return true;
	}

	@Override
	public UserFavouriteMatch updateFavourites(UserFavouriteMatch match, String userId) throws MethodNotFoundException {

		UserFavouriteMatch matchToSave = repo.insert(match);
		return matchToSave;

	}
}
