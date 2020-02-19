package com.stackroute.userservice.repository;

import org.springframework.stereotype.Repository;

import com.stackroute.userservice.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface UserAutheticationRepository extends JpaRepository<User, String> {
	
	@Query("select u from User u where u.userid = (?1) and u.userPassword = (?2)")
    User findByUserIdAndUserPassword(String userId, String userPassword);
	
}
