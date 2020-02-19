package com.stackroute.userservice.service;

import org.springframework.stereotype.Service;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;

import com.stackroute.userservice.exception.UserAlreadyExistsException;
import com.stackroute.userservice.exception.UserNotFoundException;
import com.stackroute.userservice.model.User;
import com.stackroute.userservice.repository.UserAutheticationRepository;

@Service
public class UserAuthenticationServiceImpl  implements UserAuthenticationService{

	private UserAutheticationRepository userAutheticationRepository;

	public UserAutheticationRepository getUserAutheticationRepository() {
		return userAutheticationRepository;
	}

	public void setUserAutheticationRepository(UserAutheticationRepository userAutheticationRepository) {
		this.userAutheticationRepository = userAutheticationRepository;
	}



	@Autowired
	public UserAuthenticationServiceImpl(UserAutheticationRepository userAutheticationRepository) {
		this.userAutheticationRepository = userAutheticationRepository;
	}


	public UserAuthenticationServiceImpl() {
	}

	/*
	 * This method should be used to validate a user using userId and password.
	 *  Call the corresponding method of Respository interface.
	 * 
	 */
	@Override
	public User findByUserIdAndPassword(String userId, String password) throws UserNotFoundException {


		User u =userAutheticationRepository.findByUserIdAndUserPassword(userId, password);
		if(null!=u) {
			return u;
		}
		else {
			return null;
		}

	}

	/*
	 * This method should be used to save a new user.Call the corresponding method
	 * of Respository interface.
	 */

	@Override
	public boolean saveUser(User user) throws UserAlreadyExistsException  {

		try {
		//User u  = userAutheticationRepository.findById(user.getUserId()).get();
		if(null!=user) {
			userAutheticationRepository.save(user);
			return true;
		}
		else {

			throw new UserAlreadyExistsException("Cannot Register User"); 
		}
		}
		catch(NoSuchElementException e) {
			e.getMessage();
			return true;
		}

	}

}
