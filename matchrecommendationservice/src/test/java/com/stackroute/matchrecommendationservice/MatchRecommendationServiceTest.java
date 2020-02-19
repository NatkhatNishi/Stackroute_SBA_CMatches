package com.stackroute.matchrecommendationservice;

import static org.mockito.Mockito.when;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.stackroute.matchrecommendationservice.model.Match;
import com.stackroute.matchrecommendationservice.model.RecommendedMatch;
import com.stackroute.matchrecommendationservice.model.UserFavouriteMatch;
import com.stackroute.matchrecommendationservice.repository.MatchRecomendationRepository;
import com.stackroute.matchrecommendationservice.service.MatchRecommendationServiceImpl;

public class MatchRecommendationServiceTest {

	
    @Mock
    MatchRecomendationRepository repo;

    RecommendedMatch recmatch;

    @InjectMocks
    MatchRecommendationServiceImpl service;

    List<Book> bookList;
    Optional<RecommendedMatch> options;

    @Before
    public void setUp()
    {
        MockitoAnnotations.initMocks(this);
        
    	Match m = new Match();
		m.setTeam1("team1");
		m.setTeam2("team2");
		recmatch = new RecommendedMatch();

		List<Match> matches = new ArrayList();
		matches.add(m);
		recmatch.setRecommendedmatchList(matches);
        options = Optional.of(recmatch);
    }
    
    @Test
    public void updateRecommendedData()  
    {
    	
//    	RecommendedMatch recommend = new RecommendedMatch();
    	List<Match> matches = new ArrayList();
//    	recommend.setRecommendedmatchList(matches);
//        when(repo.findAll()).thenReturn(new ArrayList());
        UserFavouriteMatch recoment = new UserFavouriteMatch();
        recoment.setUserId("abc");
        recoment.setMatchList(matches);
        when(repo.findAll()).thenReturn(new ArrayList());
        service.updateRecommendedData(recoment);
       
    }
	
}
