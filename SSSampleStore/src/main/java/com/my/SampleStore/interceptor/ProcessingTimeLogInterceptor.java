package com.my.SampleStore.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class ProcessingTimeLogInterceptor extends HandlerInterceptorAdapter {
	
	public static final Logger LOGGER = Logger.getLogger(ProcessingTimeLogInterceptor.class);
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) {
		long startTime = System.currentTimeMillis();
		request.setAttribute("startTime", startTime);
		return true;
	}
	
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object ibj, ModelAndView mav) {
		
	}

}
