package com.stackroute.matchrecommendationservice.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.matchrecommendationservice.repository.MatchRecomendationRepository;
import com.stackroute.matchrecommendationservice.repository.RecomendationRepository;
import com.stackroute.matchrecommendationservice.model.Match;
import com.stackroute.matchrecommendationservice.model.MatchRecommendData;
import com.stackroute.matchrecommendationservice.model.RecommendedMatch;
import com.stackroute.matchrecommendationservice.model.UserFavouriteMatch;

@Service
public class MatchRecommendationServiceImpl implements MatchRecommendationService {
	
	@Autowired
	MatchRecomendationRepository repo;
	@Autowired
	RecomendationRepository recomendationRepository;
	
	public MatchRecommendationServiceImpl() {
	}

	@Autowired
	public MatchRecommendationServiceImpl(MatchRecomendationRepository MatchRecomendationRepository) {
		this.repo = MatchRecomendationRepository;
	}

	public MatchRecomendationRepository getMatchRecomendationRepository() {
		return repo;
	}

	public void setMatchRecomendationRepository(MatchRecomendationRepository repo) {
		this.repo = repo;
	}

	@Override
	public List<Match> filteruniqueData(List<Match> list) {
		
		List<Match> storedlist =getAllRecommendedMatchList();
		if(null!=storedlist) {
		list.addAll(storedlist);
		}
        Set<Match> set = new LinkedHashSet<>(); 
        set.addAll(list); 
        list.clear(); 
        list.addAll(set); 
        return list; 
	}

	@Override
	public void updateRecommendedData(UserFavouriteMatch favmatch) {
		List<Match> listMatchs = new ArrayList<Match>();
		listMatchs = favmatch.getMatchList();
		if(null!=listMatchs) {
		List<Match> listtobeUpdated =	filteruniqueData(listMatchs);
			RecommendedMatch recommendMatch = new RecommendedMatch();
			recommendMatch.setRecommendedmatchList(listMatchs);
			recommendMatch.setUserId(favmatch.getUserId());
//			repo.deleteAll();
			repo.insert(recommendMatch);
		}
	}

	@Override
	public List<Match> getAllRecommendedMatchList() {
		List<RecommendedMatch> recommendedlist = repo.findAll();
		List<Match> listMatchs = new ArrayList();
		if(null!=recommendedlist) {
			Iterator it = recommendedlist.listIterator();
			while (it.hasNext()) {
				RecommendedMatch b = (com.stackroute.matchrecommendationservice.model.RecommendedMatch) it.next();
				listMatchs.addAll(b.getRecommendedmatchList());
			}
			
		}
		return listMatchs;
	}

	
	public void updateRecomendations(Match match) {
		
		MatchRecommendData newrecommendation ;
		int conter = 0;
		
		Optional<MatchRecommendData> recmatch = recomendationRepository.findById(match.getUnique_ID());
		if(recmatch.isPresent()) {
			newrecommendation = recmatch.get();
			conter = newrecommendation.getCounter();
			newrecommendation.setCounter(conter);
			
		} else {
			
			newrecommendation = new MatchRecommendData();
			newrecommendation.setMatch(match);
			newrecommendation.setCounter(1);
		}
		recomendationRepository.save(newrecommendation);
	}

	@Override
	public List<MatchRecommendData> getAllRecommendations() {
		List<MatchRecommendData> list = new ArrayList<MatchRecommendData>();
		list = recomendationRepository.findAll();
		return list;
	}
}
