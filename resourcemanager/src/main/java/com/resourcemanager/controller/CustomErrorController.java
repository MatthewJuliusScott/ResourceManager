/*
 * 
 */
package com.resourcemanager.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * The Class CustomErrorController.
 */
@Controller
public class CustomErrorController implements ErrorController {

	/* (non-Javadoc)
	 * @see org.springframework.boot.web.servlet.error.ErrorController#getErrorPath()
	 */
	@Override
	public String getErrorPath() {
		return "/error";
	}

	/**
	 * Handle error.
	 *
	 * @param request the request
	 * @return the string
	 */
	@RequestMapping("/error")
	public String handleError(HttpServletRequest request) {
		Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
		if (statusCode == 403) {
			return "error/403";
		} else if (statusCode == 404) {
			return "error/404";
		} else {
			return "error/error";
		}
	}
}