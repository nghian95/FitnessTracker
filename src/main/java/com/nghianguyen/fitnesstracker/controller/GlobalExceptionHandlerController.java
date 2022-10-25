package com.nghianguyen.fitnesstracker.controller;

import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.nghianguyen.fitnesstracker.exception.IncorrectUserException;

@ControllerAdvice
@Controller
public class GlobalExceptionHandlerController implements ErrorController{
	public static final String DEFAULT_ERROR_VIEW = "error";
	private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandlerController.class);
	
	/*
	 * Handles any data integrity issues. Whenever an update or insert violates some constraint.
	 */
	@ResponseStatus(HttpStatus.CONFLICT)
	@ExceptionHandler(DataIntegrityViolationException.class)
	public void handleConflict(HttpServletRequest req, Exception ex) {
		log.error("Request: " + req.getRequestURL() + " raised " + ex);
	}
	
	/*
	 * Handles any other database related issues like access or wrong SQL format
	 */
	@ExceptionHandler({SQLException.class, DataAccessException.class})
	public String databaseError(HttpServletRequest req, Exception ex, Model model) {
		log.error("Request: " + req.getRequestURL() + " raised " + ex);
		model.addAttribute("msg", "Sorry, there was a database error that occurred. Please try again.");
		return "error";
	}
	
	/*
	 * Handles any exceptions related to invalid input (missing input, wrong format)
	 */
	@ExceptionHandler({BindException.class})
	public String invalidInput(HttpServletRequest req, Exception ex, Model model) {
		log.error("Request: " + req.getRequestURL() + " raised " + ex);
		model.addAttribute("msg", "Please re-enter valid input for the fields as there was a binding exception that occurred.");
		return "error";
	}
	
	/*
	 * Handles any exceptions that occur when the page is supposed to have more parameters but 
	 * is missing them. i.e. Update pages that have values passed to them. 
	 */
	@ExceptionHandler(value = MissingServletRequestParameterException.class)
	public String missingParameter(HttpServletRequest req, Exception ex, Model model) {
		log.error("Request: " + req.getRequestURL() + " raised " + ex);
		model.addAttribute("msg", "Please use the user interface to access the destination page.");
		return "error";
	}
	
	@ExceptionHandler(value = IncorrectUserException.class)
	public String incorrectUser(HttpServletRequest req, Exception ex, Model model) {
		log.error("Request: " + req.getRequestURL() + " raised " + ex);
		model.addAttribute("msg", "Sorry! You can't perform that action as it belongs to another user.");
		return "error";
	}
	
	/*
	 * Meant to be the default error method handler for anything that hasn't been handled already. 
	 * Includes the 404 error where there's no mapping for a specific URL. 
	 */
	@ExceptionHandler(value = Exception.class)
	@GetMapping("/error")
	public String handleError(HttpServletRequest req, Exception ex, Model model) throws Exception {
//		for custom errors with @ResponseStatus
//	    if (AnnotationUtils.findAnnotation
//                (ex.getClass(), ResponseStatus.class) != null) {
//	    	throw ex;
//	    }
		Object status = req.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		if (status != null) {
			Integer statusCode = Integer.valueOf(status.toString());
			
			if (statusCode == HttpStatus.NOT_FOUND.value()) {
				model.addAttribute("msg", "Sorry, that page was not found.");
			} else {
				model.addAttribute("msg", "Sorry, there was an error that occurred. Please try again.");
			}
		}
		log.error("Request: " + req.getRequestURL() + " raised " + ex);
	    return "error";
	}
}
