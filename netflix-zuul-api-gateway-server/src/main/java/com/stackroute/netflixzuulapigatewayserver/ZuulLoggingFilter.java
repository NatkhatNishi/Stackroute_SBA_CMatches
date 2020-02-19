package com.stackroute.netflixzuulapigatewayserver;


import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

/*
 * Implement zuul logging filter by extending zuul filter
 */
@Component
public class ZuulLoggingFilter extends ZuulFilter{

	private static final Logger logger = LoggerFactory.getLogger(ZuulLoggingFilter.class);

	@Override
	public boolean shouldFilter() {
		
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		RequestContext ctx = RequestContext.getCurrentContext();
	    HttpServletRequest request = ctx.getRequest();
	 
	    logger.debug("Request Method : " + request.getMethod() + " Request URL : " + request.getRequestURL().toString());
	    return null;
	}

	@Override
	public String filterType() {

		return "pre";
	}

	@Override
	public int filterOrder() {

		return 1;
	}

	
}
