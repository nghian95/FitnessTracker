package com.nghianguyen.fitnesstracker.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import com.nghianguyen.fitnesstracker.controller.ActivityController;

/*
 * Filter class to log the HTTP requests and responses
 */
@Component
public class LoggingFilter extends OncePerRequestFilter{
	private static final Logger log = LoggerFactory.getLogger(LoggingFilter.class);
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		ContentCachingRequestWrapper contentCachingRequestWrapper = new ContentCachingRequestWrapper(request);
		ContentCachingResponseWrapper contentCachingResponseWrapper = new ContentCachingResponseWrapper(response);
		
		long startTime = System.currentTimeMillis();
		
		filterChain.doFilter(contentCachingRequestWrapper, contentCachingResponseWrapper);
		
		long timeTaken = System.currentTimeMillis() - startTime;
//		String requestBody = getStringValue(contentCachingRequestWrapper.getContentAsByteArray(), request.getCharacterEncoding());
//		String responseBody = getStringValue(contentCachingResponseWrapper.getContentAsByteArray(), response.getCharacterEncoding());
//	
//		log.info("Filter Logs : METHOD = {}; \nREQUESTURI = {}; \nREQUEST BODY = {}; \nRESPONSE CODE = {}; \nRESPONSE BODY = {}; \nTIME TAKEN = {}",
//				request.getMethod(), request.getRequestURI(), requestBody, response.getStatus(), responseBody, timeTaken);
		
		log.info("Filter Logs : METHOD = {}; REQUESTURI = {};  RESPONSE CODE = {}; TIME TAKEN = {}",
				request.getMethod(), request.getRequestURI(), response.getStatus(), timeTaken);
		
		contentCachingResponseWrapper.copyBodyToResponse();
	}

	private String getStringValue(byte[] contentAsByteArray, String characterEncoding) throws UnsupportedEncodingException {
		// TODO Auto-generated method stub
		return new String(contentAsByteArray, 0, contentAsByteArray.length, characterEncoding);
	}
	
}
