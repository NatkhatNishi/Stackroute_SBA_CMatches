package com.stackroute.netflixzuulapigatewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/*
 * The @SpringBootApplication annotation is equivalent to using @Configuration, @EnableAutoConfiguration 
 * and @ComponentScan with their default attributes
 * 
 * Add @EnableZuulProxy and @EnableDiscoveryClient
 * 
 */
@SpringBootApplication
@EnableZuulProxy
@EnableDiscoveryClient
public class NetflixZuulApiGatewayServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(NetflixZuulApiGatewayServerApplication.class, args);
	}
	
	@Bean
    public ZuulLoggingFilter preFilter() {
        return new ZuulLoggingFilter();
    }

@SuppressWarnings({ "rawtypes", "unchecked" })
@Bean
public FilterRegistrationBean jwtFilter() {
   final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
   registrationBean.setFilter(new JwtFilter());
   
   registrationBean.addUrlPatterns("/muzixmanager/*", "/muzixrecommendation/*");
   return registrationBean;
}

@Bean
public CorsFilter corsFilter () {
final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
final CorsConfiguration config = new CorsConfiguration();
config.setAllowCredentials(true);
config.addAllowedOrigin("*");
config.addAllowedHeader("*");
config.addAllowedMethod("GET");
config.addAllowedMethod("POST");
config.addAllowedMethod("PUT");
config.addAllowedMethod("DELETE");
config.addAllowedMethod("OPTIONS");
config.addAllowedMethod("HEAD");
config.addAllowedMethod("PATCH");
source.registerCorsConfiguration("*", config);
return new CorsFilter(source);
}

}

