package com.stackroute.netflixzuulapigatewayserver;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.GenericFilterBean;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;




public class JwtFilter extends GenericFilterBean{
	
	@Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    
    final HttpServletRequest req = (HttpServletRequest) request;
	final HttpServletResponse res= (HttpServletResponse) response;
	//final String authHeader = req.getHeader("authorization");
	final String authHeader ="Bearer "+req.getHeader("Authorization");
System.out.println("inside dofilter method ");
	if ("OPTIONS".equals(req.getMethod())) {
		res.setStatus(HttpServletResponse.SC_OK);
		chain.doFilter(request, res);
	} else {
		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
		//if (authHeader == null || !authHeader.startsWith("bearerToken ")) {
			System.out.println("inside do filtererror");
			throw new ServletException("Missing or invalid Authorization header");
		}

		final String token = authHeader.substring(7);
		final Claims claims = Jwts.parser()
								  .setSigningKey("mysecretkey")
								  .parseClaimsJws(token)
								  .getBody();

		request.setAttribute("claims", claims);
		System.out.println("claims :"+claims);
		chain.doFilter(request, res);

	}
	}

}
