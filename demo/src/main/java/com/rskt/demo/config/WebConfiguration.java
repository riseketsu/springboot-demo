/**
 * Copyright © 2018 RSKT. All rights reserved. 
 */
package com.rskt.demo.config;

import org.apache.catalina.filters.RemoteIpFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Class:WebConfiguration.java<br>
 *
 * Class Summary：<br>
 * 
 * Class Feature:<br>
 *
 * @Author lishijie
 * @CreateDate 2018-07-05
 *
 */
@Configuration
public class WebConfiguration {

	@Bean
    public RemoteIpFilter remoteIpFilter() {
        return new RemoteIpFilter();
    }
	
	@Bean
    public FilterRegistrationBean<WebFilter> testFilterRegistration() {

        FilterRegistrationBean<WebFilter> registration = new FilterRegistrationBean<WebFilter>();
        registration.setFilter(new WebFilter());
        registration.addUrlPatterns("/*");
        registration.addInitParameter("paramName", "paramValue");
        registration.setName("WebFilter");
        registration.setOrder(1);
        return registration;
    }
}
