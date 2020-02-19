package com.stackroute.userservice.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.userservice.exception.UserAlreadyExistsException;
import com.stackroute.userservice.model.User;
import com.stackroute.userservice.service.UserAuthenticationService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@CrossOrigin(origins = "*")
@RestController
public class UserAuthenticationController {
	
	static final long EXPIRATIONTIME = 300000;
	Map<String, String> map = new HashMap<>();
	
	@Autowired
	private  UserAuthenticationService userAuthenticationService;



	public UserAuthenticationController() {
	}

	public UserAuthenticationController(UserAuthenticationService userAuthenticationService) {
		this.userAuthenticationService = userAuthenticationService;
	}

	@PostMapping("/api/v1/auth/register")
	public ResponseEntity<User> registerUser(@RequestBody User user) throws UserAlreadyExistsException {

		boolean isuseradded =false;
		try {
			user.setUserAddedDate(new Date());
			boolean usertobecreated  = userAuthenticationService.saveUser(user);
			
			if(usertobecreated) {
				isuseradded =true;
			}
		}
		catch(Exception e) {
			e.getMessage();
			return new ResponseEntity<User>(user,HttpStatus.CONFLICT);
		}

		if(isuseradded) {
			return new ResponseEntity<User>(user,HttpStatus.CREATED);
		}

		else {
			return new ResponseEntity<User>(user,HttpStatus.CREATED);
		}
	}
	
	public UserAuthenticationService getUserAuthenticationService() {
		return userAuthenticationService;
	}



	public void setUserAuthenticationService(UserAuthenticationService userAuthenticationService) {
		this.userAuthenticationService = userAuthenticationService;
	}


	@PostMapping("/api/v1/auth/login")
	public ResponseEntity<?> login(@RequestBody User user) throws ServletException {

		String jwtToken = "";

		try {
			jwtToken = getToken(user.getUserid(), user.getUserPassword());
			map.clear();
			map.put("message", "user successfully logged in");
			map.put("token", jwtToken);
		} catch (Exception e) {
			String exceptionMessage = e.getMessage();
			map.clear();
			map.put("token", null);
			map.put("message", exceptionMessage);
			return new ResponseEntity<>(map, HttpStatus.UNAUTHORIZED);
		}
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	// Generate JWT token
	public String getToken(String username, String password) throws Exception {

		if (username == null || password == null) {
			throw new ServletException("Please fill in username and password");
		}

		User u = userAuthenticationService.findByUserIdAndPassword(username, password);

		if (null==u) {
			throw new ServletException("Invalid credentials.");
		}
		

		String jwtToken = Jwts.builder()
				.setSubject(username)
				.setIssuedAt(new Date())
//				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
				.signWith(SignatureAlgorithm.HS256, "secretkey").compact();

		return jwtToken;


	}	
	
	
}
