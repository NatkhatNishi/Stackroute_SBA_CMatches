/**
 * 
 */
package com.stackroute.favouriteservice.repository;

import com.stackroute.favouriteservice.model.UserFavouriteMatch;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author NishigandhaomanwarOm
 *
 */
@Repository
public interface UserFavouriteMatchRepository extends MongoRepository<UserFavouriteMatch , String>{ 

}
