package com.stackroute.userservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@EnableDiscoveryClient
@CrossOrigin(origins="*")
public class UserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}
	
	/*
	 * @Bean public WebMvcConfigurer corsConfigurer() { return new
	 * WebMvcConfigurerAdapter(){
	 * 
	 * @Override public void addCorsMappings(CorsRegistry registry){
	 * registry.addMapping("/*").allowedOrigins("https://localhost:9100").
	 * allowedOrigins("http://localhost:4200/"); } }; }
	 */

//	@Bean
//	public Sampler defaultSampler(){
//		return Sampler.ALWAYS_SAMPLE;
//	}

}

