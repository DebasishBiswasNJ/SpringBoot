package com.deb.microservices.netflixzuulapigatewayserver;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;


@Component
public class ZuulLoggingFilter extends ZuulFilter {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * We write main logic in the run() method
	 * @return
	 * @throws ZuulException
	 */
	@Override
	public Object run() throws ZuulException {
		HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
		logger.info("request -> {} request uri -> {}", request, request.getRequestURI());
		return null;
	}

	/**
	 * Here returning true means, always do the filter
	 */
	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public int filterOrder() {
		return 1;
	}

	/**
	 * Here you need to define, when you need to do the filter. 
	 * "pre" (before method), "post", "error" (OR), any others
	 * 
	 * [ctrl + d] == to delete the generated lines
	 */
	@Override
	public String filterType() {
		return "pre";
	}

}
