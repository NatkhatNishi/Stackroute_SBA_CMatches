/**
 * 
 */
package com.stackroute.matchrecommendationservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.matchrecommendationservice.model.RecommendedMatch;

/**
 * @author NishigandhaomanwarOm
 *
 */
@Repository
public interface MatchRecomendationRepository extends MongoRepository<RecommendedMatch, String>{
}
