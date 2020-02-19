package com.stackroute.favouriteservice.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.stackroute.favouriteservice.exception.MatchNotFoundException;
import com.stackroute.favouriteservice.model.Match;
import com.stackroute.favouriteservice.model.UserFavouriteMatch;
import com.stackroute.favouriteservice.repository.UserFavouriteMatchRepository;

import junit.framework.Assert;

public class UserfavoriteMatchServiceTest {

	
    @Mock
    UserFavouriteMatchRepository repo;

    UserFavouriteMatch favmatch;

    @InjectMocks
    UserfavoriteMatchServiceImpl service;

    List<Match> matches;
    Optional<UserFavouriteMatch> options;

    @Before
    public void setUp()
    {
        MockitoAnnotations.initMocks(this);
        
        Match m = new Match();
		m.setTeam1("team1");
		m.setTeam2("team2");
		List<Match> listmatch = new ArrayList();
		listmatch.add(m);
		favmatch = new UserFavouriteMatch();
		favmatch.setMatchList(listmatch);
		favmatch.setUserId("11334");

        options = Optional.of(favmatch);
    }
    
    @Test
    public void createUserFavouriteSuccess() throws MatchNotFoundException 
    {
    	 when(repo.insert((UserFavouriteMatch) any())).thenReturn(favmatch);
//        when(repo.insert((UserFavouriteMatch) any())).thenReturn(favmatch);
        UserFavouriteMatch userFavouriteBookSaved = service.updateFavourites(favmatch, "1234");
        Assert.assertEquals(favmatch, userFavouriteBookSaved);

    }
    	
}
