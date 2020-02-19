package com.stackroute.favouriteservice.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.favouriteservice.config.FavMatchMessageProducer;
import com.stackroute.favouriteservice.exception.MatchAlreadyExistsException;
import com.stackroute.favouriteservice.exception.MatchNotFoundException;
import com.stackroute.favouriteservice.model.Match;
import com.stackroute.favouriteservice.model.UserFavouriteMatch;
import com.stackroute.favouriteservice.service.UserfavoriteMatchService;

@CrossOrigin(origins = "*")
//@EnableBinding(FavouriteMatchSource.class)
@RestController
@EnableDiscoveryClient
//@ComponentScan("com.stackroute.favouriteservice") 
public class FacouriteMatchController {
	
//@Autowired
//FavouriteMatchSource favouriteMatchSource;

@Autowired
private UserfavoriteMatchService service;

@Autowired(required= true )
private FavMatchMessageProducer producer;

public UserfavoriteMatchService getUserfavoriteMatchService() {
	return service;
}

public void setUserfavoriteMatchService(UserfavoriteMatchService UserfavoriteMatchService) {
	this.service = UserfavoriteMatchService;
}

@Autowired
public FacouriteMatchController(UserfavoriteMatchService service, FavMatchMessageProducer producer) {
	super();
	this.service = service;
	this.producer = producer;
}

public FacouriteMatchController() {}


@PostMapping("/api/v1/addtoFavourite")
public ResponseEntity<UserFavouriteMatch> updateFavouriteMatch(@RequestBody UserFavouriteMatch userFavouriteMatch) throws MatchAlreadyExistsException, IOException, TimeoutException {

	boolean userAdded =false;
	List<Match> matchList = new ArrayList<Match>();
	try {
								
		 matchList =service.getAllMatchByUserId(userFavouriteMatch.getUserId());	
		if(null != matchList && !matchList.isEmpty()) {
			service.deleteAllMatches(userFavouriteMatch.getUserId());
			service.updateFavourites(userFavouriteMatch,userFavouriteMatch.getUserId());
			userAdded=true;
		}
		if(null == matchList || matchList.isEmpty()) {
			service.updateFavourites(userFavouriteMatch,userFavouriteMatch.getUserId());
			userAdded= true;
		}
	}
	catch(Exception e) {
		e.getMessage();
		return new ResponseEntity<UserFavouriteMatch>(userFavouriteMatch,HttpStatus.CONFLICT);
	}

	if(userAdded) {
//		pushMessage(userFavouriteMatch);
		producer.sendFavMatch(userFavouriteMatch);
		
	}
	
	
	return new ResponseEntity<UserFavouriteMatch>(userFavouriteMatch,HttpStatus.CREATED);
}


//public void pushMessage(UserFavouriteMatch UserFavouriteMatch) {
//	favouriteMatchSource.userRegistration().send(MessageBuilder.withPayload(UserFavouriteMatch).build());
//	System.out.println(UserFavouriteMatch.toString());
//}


@GetMapping("/api/v1/fetchFavouriteMatches/{userid}")
public ResponseEntity<List<Match>> getFavouriteMatchesForUser(@PathVariable("userid") String userid) throws MatchNotFoundException {

	List<Match> matchList =null;
	try {
		matchList =service.getAllMatchByUserId(userid);	
		if(null!=matchList) {
			return new ResponseEntity<List<Match>>(matchList,HttpStatus.CREATED);
		}
	}
	catch(Exception e) {
		e.getMessage();
		return new ResponseEntity<List<Match>>(matchList,HttpStatus.CONFLICT);
	}
	return null;
}


}
