package com.stackroute.matchrecommendationservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.stackroute.matchrecommendationservice.model.UserFavouriteMatch;
import com.stackroute.matchrecommendationservice.service.MatchRecommendationService;

import brave.sampler.Sampler;
@SpringBootApplication
@EnableBinding(Sink.class)
@CrossOrigin(origins="*")
@EnableDiscoveryClient
public class MatchRecommendationServiceApplication {

	MatchRecommendationService matchrecommendedService;
	
	public MatchRecommendationServiceApplication() {
	}

	@Autowired
	public MatchRecommendationServiceApplication(MatchRecommendationService matchrecommendedService) {
		this.matchrecommendedService = matchrecommendedService;
	}

	public static void main(String[] args) {
		SpringApplication.run(MatchRecommendationServiceApplication.class, args);
	}
	
	@StreamListener(target = Sink.INPUT)
	public void processRegisterUsers(UserFavouriteMatch userFavouriteMatch) {
		System.out.println("User Favourite Match received " + userFavouriteMatch.getUserId());
		if(null != userFavouriteMatch.getMatchList()) {
		matchrecommendedService.updateRecommendedData(userFavouriteMatch);
		}
	}
	
	
	/*
	 * @Bean public WebMvcConfigurer corsConfigurer() { return new
	 * WebMvcConfigurerAdapter(){
	 * 
	 * @Override public void addCorsMappings(CorsRegistry registry){
	 * registry.addMapping("/*").allowedOrigins("https://localhost:9200").
	 * allowedOrigins("http://localhost:4200"); } }; }
	 */
	
	@Bean
	public Sampler defaultSampler(){
		return Sampler.ALWAYS_SAMPLE;
	}

}

