/**
 * Copyright © 2018 RSKT. All rights reserved. 
 */
package com.rskt.demo.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

/**
 * Class:WebFilter.java<br>
 *
 * Class Summary：<br>
 * 
 * Class Feature:<br>
 *
 * @Author lishijie
 * @CreateDate 2018-07-05
 *
 */
public class WebFilter implements Filter {

	private Logger logger = Logger.getLogger(WebFilter.class);

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest srequest, ServletResponse sresponse, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) srequest;
		logger.debug("WebFilter : URL=" + request.getRequestURI());
		filterChain.doFilter(srequest, sresponse);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
