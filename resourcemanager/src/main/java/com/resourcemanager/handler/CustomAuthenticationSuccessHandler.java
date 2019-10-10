/*
 *
 */
package com.resourcemanager.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.resourcemanager.model.User;
import com.resourcemanager.service.UserService;

/**
 * Custom AuthenticationSuccessHandler that upon successful login gets the authenticated user and puts it on the session as an
 * attribute.
 */
@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	/** The user service. */
	@Autowired
	private UserService userService;

	/*
	 * (non-Javadoc)
	 * @see
	 * org.springframework.security.web.authentication.AuthenticationSuccessHandler#onAuthenticationSuccess(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.security.core.Authentication)
	 */
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
		throws IOException, ServletException {

		// get the currently logged in username
		Object principal = authentication.getPrincipal();
		String username;
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		} else {
			username = principal.toString();
		}
		// get currently logged in user
		User loggedInUser = userService.getUserByUserName(username);
		HttpSession session = request.getSession();
		session.setAttribute("loggedInUser", loggedInUser);
		response.sendRedirect("/");
	}
}