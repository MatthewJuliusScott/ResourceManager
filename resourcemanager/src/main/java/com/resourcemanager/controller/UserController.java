
package com.resourcemanager.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.resourcemanager.config.SpringSecurityConfig;
import com.resourcemanager.model.User;
import com.resourcemanager.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService		userService;

	@Autowired
	SpringSecurityConfig	securityConfig;

	@RequestMapping("users/add")
	public String addUser() {
		return "redirect:/users/edit/0";
	}

	@RequestMapping(value = { "/users/myprofile" }, method = RequestMethod.GET)
	public String editMyProfile(Model model) {

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username;
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		} else {
			username = principal.toString();
		}

		model.addAttribute("user", this.userService.getUserByUserName(username));
		return "users/edit";
	}

	@RequestMapping(value = { "/users/edit/{id}" }, method = RequestMethod.GET)
	public String editUser(@PathVariable("id") Long id, Model model) {
		if (id > 0) {
			model.addAttribute("user", this.userService.getUserByID(id));
		} else {
			model.addAttribute("user", new User());
		}
		return "users/edit";
	}

	@RequestMapping(value = { "/users" }, method = RequestMethod.GET)
	public String listUsers(Model model) {
		model.addAttribute("listUsers", this.userService.listUsers());
		return "users";
	}

	@RequestMapping(value = { "/users/delete/{id}" }, method = RequestMethod.GET)
	public String removeUser(@PathVariable("id") Long id) {

		this.userService.deleteUser(id);
		return "redirect:/users";
	}

	// For add and update user both
	@RequestMapping(value = "/users/save", method = RequestMethod.POST)
	public String saveUser(@ModelAttribute("user") User user,
		BindingResult result, HttpServletRequest request) {
		if (result.hasErrors()) {
			System.err.println(result.toString());
		}

		// if a new password has been set, encrypt it and assign to user object
		if (request.getParameter("password") != null && request.getParameter("password").length() > 0) {
			user.setPassword(securityConfig.encoder().encode(request.getParameter("password")));
		}

		if (user.getId() == 0) {
			// new user, add it
			this.userService.addUser(user);
		} else {
			// existing user, call update
			this.userService.updateUser(user);
		}

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username;
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		} else {
			username = principal.toString();
		}

		if (username.equals(user.getEmail())) {
			return "redirect:/users/myprofile";
		} else {
			return "redirect:/users";
		}

	}
}
