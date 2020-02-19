package com.stackroute.matchrecommendationservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.matchrecommendationservice.exception.MatchNotFoundException;
import com.stackroute.matchrecommendationservice.model.Match;
import com.stackroute.matchrecommendationservice.model.MatchRecommendData;
import com.stackroute.matchrecommendationservice.service.MatchRecommendationService;

@CrossOrigin(origins = "*")
@RestController
public class MatchRecommendationController {

	@Autowired
	private MatchRecommendationService service;

	
	public MatchRecommendationService getMatchrecommendationservice() {
		return service;
	}

	public void setMatchrecommendationservice(MatchRecommendationService matchrecommendationservice) {
		this.service = matchrecommendationservice;
	}

	@Autowired
	public MatchRecommendationController(MatchRecommendationService bookrecommendationservice) {
		this.service = bookrecommendationservice;
	}

	public MatchRecommendationController() {
	}
	
	@GetMapping("/api/v1/matchRecomendations")
	public ResponseEntity<List<MatchRecommendData>> getmatchRecomendations() throws MatchNotFoundException {

		List<MatchRecommendData> recoList =new ArrayList<MatchRecommendData>();
		
		try {
			recoList =service.getAllRecommendations();	
			if(null!=recoList) {
				return new ResponseEntity<List<MatchRecommendData>>(recoList,HttpStatus.CREATED);
			}
		}
		catch(Exception e) {
			e.getMessage();
			return new ResponseEntity<List<MatchRecommendData>>(recoList,HttpStatus.CONFLICT);
		}
		return (ResponseEntity<List<MatchRecommendData>>) recoList;
	
	}
	@GetMapping("/api/v1/fetchRecommendedMatches")
	public ResponseEntity<List<Match>> getfavMatchesForuser() throws MatchNotFoundException {

		boolean userAdded =false;
		List<Match> matchesList =new ArrayList();
		try {
			matchesList =service.getAllRecommendedMatchList();	
			if(null!=matchesList) {
				return new ResponseEntity<List<Match>>(matchesList,HttpStatus.CREATED);
			}
		}
		catch(Exception e) {
			e.getMessage();
			return new ResponseEntity<List<Match>>(matchesList,HttpStatus.CONFLICT);
		}
		return (ResponseEntity<List<Match>>) matchesList;
	}
	
		
}
