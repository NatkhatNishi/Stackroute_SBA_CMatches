/**
 * 
 */
package com.stackroute.matchrecommendationservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.matchrecommendationservice.model.MatchRecommendData;

/**
 * @author NishigandhaomanwarOm
 *
 */
@Repository
public interface RecomendationRepository extends MongoRepository<MatchRecommendData, String>{
}
