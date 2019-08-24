
package com.resourcemanager.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.resourcemanager.model.Resource;
import com.resourcemanager.model.User;
import com.resourcemanager.service.ResourceService;
import com.resourcemanager.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService		userService;

	@Autowired
	private ResourceService	resourceService;

	@Autowired
	private PasswordEncoder	encoder;

	@RequestMapping("users/add")
	public String addUser() {
		return "redirect:/users/edit/0";
	}

	@RequestMapping(value = {"/users/myprofile"}, method = RequestMethod.GET)
	public String editMyProfile(Model model) {

		Object principal = SecurityContextHolder.getContext()
		        .getAuthentication().getPrincipal();
		String username;
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		} else {
			username = principal.toString();
		}

		model.addAttribute("user",
		        this.userService.getUserByUserName(username));
		return "users/edit";
	}

	@RequestMapping(value = {"/users/edit/{id}"}, method = RequestMethod.GET)
	public String editUser(@PathVariable("id") Long id, Model model) {
		if (id > 0) {
			model.addAttribute("user", this.userService.getUserByID(id));
		} else {
			model.addAttribute("user", new User());
		}
		return "users/edit";
	}

	@RequestMapping(value = {"/users"}, method = RequestMethod.GET)
	public String listUsers(Model model) {
		model.addAttribute("listUsers", this.userService.listUsers());
		return "users";
	}

	@RequestMapping(value = {"/users/delete/{id}"}, method = RequestMethod.GET)
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
		String oldPassword = request.getParameter("oldPassword") != null
		        ? request.getParameter("oldPassword")
		        : "";
		String newPassword = request.getParameter("password") != null
		        ? request.getParameter("password")
		        : "";

		Object principal = SecurityContextHolder.getContext()
		        .getAuthentication().getPrincipal();
		String username;
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		} else {
			username = principal.toString();
		}

		User oldUser = userService.getUserByUserName(username);
		user.setPassword(oldUser.getPassword());
		user.setEmail(oldUser.getEmail());

		// if old password is correct
		if (encoder.matches(oldPassword, user.getPassword())) {
			// if a new password has been set, encrypt it and assign to user
			// object
			if (newPassword.length() > 0) {
				String encryptedPassword = encoder.encode(newPassword);
				user.setPassword(encryptedPassword);
			}
		}

		// this will need to be set on the front end, cant pass back whole POJO,
		// but we can send back just the resourceId for this user, and then
		// extract it and assign back to the object here
		String resourceId = request.getParameter("resourceId");

		if (resourceId != null && !resourceId.equals("")
		        && !resourceId.equals("0")) {
			Resource resource = resourceService
			        .getResourceByID(Long.parseLong(resourceId));
			user.setResource(resource);
		}
		

		if (user.getId() == 0) {
			// new user, add it
			this.userService.addUser(user);
		} else {
			// existing user, call update
			this.userService.updateUser(user);
		}

		if (username.equals(user.getEmail())) {
			return "redirect:/users/myprofile";
		} else {
			return "redirect:/users";
		}

	}
}
