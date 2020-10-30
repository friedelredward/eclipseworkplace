package com.sbtutorial.proxy.filters;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.protocol.RequestContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

public class PreFilter extends ZuulFilter{
	
	private static Logger logger= LoggerFactory.getLogger(PreFilter.class) ;

	@Override
	public boolean shouldFilter() {
		// apply or not filter
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		// TODO. here happens
		/**we can retrieve request from context; then we pef log it*/
		RequestContext rContext= RequestContext.getCurrentContext();
		HttpServletRequest request= rContext.getRequest();
		
		logger.info("PreFilter_: "+String.format("%s request to %s",
				request.getMethod(), request.getRequestURI().toString()));
		
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
