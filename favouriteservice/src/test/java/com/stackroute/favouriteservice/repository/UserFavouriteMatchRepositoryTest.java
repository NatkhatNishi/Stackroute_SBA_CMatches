package com.stackroute.favouriteservice.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.stackroute.favouriteservice.model.Match;
import com.stackroute.favouriteservice.model.UserFavouriteMatch;

@RunWith(SpringRunner.class)
@DataMongoTest
public class UserFavouriteMatchRepositoryTest {

	@Autowired
	private UserFavouriteMatchRepository repo;
	private UserFavouriteMatch favmatch;

	@Before
	public void setUp() throws Exception {

		Match m = new Match();
		m.setTeam1("team1");
		m.setTeam2("team2");
		favmatch = new UserFavouriteMatch();

		List<Match> matches = new ArrayList();
		matches.add(m);
		favmatch.setMatchList(matches);
		favmatch.setUserId("11334");

	}

	@After
	public void tearDown() throws Exception {
		repo.deleteAll();
	}

	@Test
	public void createUserFavouriteTest() {

		repo.insert(favmatch);
		UserFavouriteMatch userFavouritematch1 = repo.findById(favmatch.getUserId()).get();
		Assert.assertEquals(favmatch.getMatchList().size(), userFavouritematch1.getMatchList().size());

	}
	
	
	  @Test(expected = NoSuchElementException.class)
	    public void deleteUserFavouriteTest() {
		  repo.insert(favmatch);
		  UserFavouriteMatch userFavouriteMatch1 = repo.findById(favmatch.getUserId()).get();
		  repo.delete(userFavouriteMatch1);
		  userFavouriteMatch1 = repo.findById(favmatch.getUserId()).get();
	    }


}
