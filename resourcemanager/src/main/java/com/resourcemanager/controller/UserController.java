
package com.resourcemanager.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestDecorator;

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

	@RequestMapping(value = { "/users/myprofile" }, method = RequestMethod.GET)
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

	@RequestMapping(value = { "/users/edit/{id}" }, method = RequestMethod.GET)
	public String editUser(@PathVariable("id") Long id, Model model) {
		if (id > 0) {
			model.addAttribute("user", this.userService.getUserByID(id));
		} else {
			model.addAttribute("user", new User());
		}
		return "users/edit";
	}

	@RequestMapping(value = { "/users/notifications" }, method = RequestMethod.GET)
	public String getNotifications(Model model) {
		User user = getLoggedInUser();
		model.addAttribute("user", user);
		return "users/notifications";
	}

	private User getLoggedInUser() {
		// get the currently logged in username
		Object principal = SecurityContextHolder.getContext()
			.getAuthentication().getPrincipal();
		String username;
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		} else {
			username = principal.toString();
		}
		// get currently logged in user
		User loggedInUser = userService.getUserByUserName(username);
		return loggedInUser;
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

		System.out.println(request.getAttribute("messages"));

		// extract extra parameters
		String oldPassword = request.getParameter("oldPassword") != null
			? request.getParameter("oldPassword")
			: "";
		String newPassword = request.getParameter("password") != null
			? request.getParameter("password")
			: "";

		User loggedInUser = getLoggedInUser();

		// When changing our own password only change the password if the old
		// password field matches the old password.
		if (user.getId() != 0 || user.getId() == loggedInUser.getId()) {

			User oldUser = userService.getUserByID(user.getId());
			user.setPassword(oldUser.getPassword());
			user.setEmail(oldUser.getEmail());

			if (user.getId() == loggedInUser.getId()) {
				// if old password is correct
				if (encoder.matches(oldPassword, user.getPassword())) {
					// if a new password has been set, encrypt it and assign to
					// user
					// object
					if (newPassword.length() > 0) {
						String encryptedPassword = encoder.encode(newPassword);
						user.setPassword(encryptedPassword);
					}
				} else {
					HttpServletRequestDecorator req = new HttpServletRequestDecorator(request);
					req.addMessage("Old password was incorrect.");
				}
			}

			// If it is not an existing user, or if editing another user and the
			// logged in user has admin privileges we can just update their
			// password.
		} else if (user.getId() == 0 || (user.getId() != loggedInUser.getId()
			&& loggedInUser.getAuthorityStrings().contains("ROLE_ADMIN"))) {

			// The front end validation will already have checked a password
			// field matches a re-enter your password field, just set value
			String encryptedPassword = encoder.encode(newPassword);
			user.setPassword(encryptedPassword);
		}

		// this will need to be set on the front end, can't pass back whole
		// POJO, but we can send back just the resourceId for this user, and
		// then extract it and assign back to the object here
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

		if (loggedInUser.getId() == user.getId()) {
			return "redirect:/users/myprofile";
		} else {
			return "redirect:/users";
		}

	}
}
