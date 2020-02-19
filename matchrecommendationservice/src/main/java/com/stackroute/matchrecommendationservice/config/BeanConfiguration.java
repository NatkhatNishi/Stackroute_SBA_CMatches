/*configuration class to call all filters*/
package com.stackroute.matchrecommendationservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class BeanConfiguration {

  @Bean
  public CorsFilter corsFilter(){
    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    final CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowCredentials(true);
    configuration.addAllowedOrigin("*");
    configuration.addAllowedHeader("*");
    configuration.addAllowedMethod("OPTIONS");
    configuration.addAllowedMethod("HEAD");
    configuration.addAllowedMethod("GET");
    configuration.addAllowedMethod("PUT");
    configuration.addAllowedMethod("POST");
    configuration.addAllowedMethod("DELETE");
    configuration.addAllowedMethod("PATCH");
    source.registerCorsConfiguration("/**",configuration);
    return new CorsFilter(source);
  }
}
