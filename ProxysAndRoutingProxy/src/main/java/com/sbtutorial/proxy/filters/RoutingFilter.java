package com.sbtutorial.proxy.filters;


import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

public class RoutingFilter extends ZuulFilter{
	
	private static Logger logger= LoggerFactory.getLogger(RoutingFilter.class) ;

	@Override
	public boolean shouldFilter() {
		// apply or not filter
		return true;
	}
	
	@Override
	public String filterType() {
		return "post";
	}

	@Override
	public int filterOrder() {
		return 1;
	}

	@Override
	public Object run() throws ZuulException {
		// TODO. here happens after the response has been sent to API
		/**we can retrieve RESPONSE from context; then we pef log it*/
		HttpServletResponse response= RequestContext.getCurrentContext().getResponse();
		
		logger.info("PostFilter_: "+String.format("Response status pej::%s", response.getStatus()));
		
		return null;
	}



}
