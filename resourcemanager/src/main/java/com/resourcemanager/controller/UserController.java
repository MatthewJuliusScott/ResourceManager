/*
 *
 */

package com.resourcemanager.controller;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

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

import com.resourcemanager.model.Notification;
import com.resourcemanager.model.Resource;
import com.resourcemanager.model.User;
import com.resourcemanager.service.NotificationService;
import com.resourcemanager.service.ResourceService;
import com.resourcemanager.service.UserService;

/**
 * This controller responds to the user input and uses the service layer to create, read, update or delete the User data model
 * objects. This controller receives the input, optionally validates it and then passes the input to the model and directs the
 * user back to a view to display the model and accept further user input.
 */
@Controller
public class UserController {

	/** The user service. */
	@Autowired
	private UserService			userService;

	/** The resource service. */
	@Autowired
	private ResourceService		resourceService;

	/** The notification service. */
	@Autowired
	private NotificationService	notificationService;

	/** The encoder. */
	@Autowired
	private PasswordEncoder		encoder;

	/**
	 * Adds the user.
	 *
	 * @return the string
	 */
	@RequestMapping("users/add")
	public String addUser() {
		return "redirect:/users/edit/0";
	}

	/**
	 * Delete notification.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the string
	 */
	@RequestMapping(value = {
			"/users/notifications/delete/{id}" },
		method = RequestMethod.POST)
	public String deleteNotification(@PathVariable("id") Long id, Model model) {
		User user = getLoggedInUser();
		Iterator<Notification> i = user.getNotifications().iterator();
		while (i.hasNext()) {
			Notification notification = i.next();
			if (notification.getId() == id.longValue()) {
				notificationService.deleteNotification(notification.getId());
				i.remove();
			}
		}
		model.addAttribute("user", user);
		return "users/notifications";
	}

	/**
	 * Edits the my profile.
	 *
	 * @param model
	 *            the model
	 * @return the string
	 */
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

		User user = this.userService.getUserByUserName(username);
		return "redirect:/users/edit/" + user.getId();
	}

	/**
	 * Edits the user.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the string
	 */
	@RequestMapping(value = { "/users/edit/{id}" }, method = RequestMethod.GET)
	public String editUser(@PathVariable("id") Long id, Model model) {
		if (id > 0) {
			model.addAttribute("user", this.userService.getUserByID(id));
		} else {
			model.addAttribute("user", new User());
		}

		User loggedInUser = getLoggedInUser();
		if (loggedInUser.getAuthorityStrings().contains("ROLE_ADMIN")) {
			model.addAttribute("listResources",
				this.resourceService.listResources());
		}
		return "users/edit";
	}

	/**
	 * Gets the logged in user.
	 *
	 * @return the logged in user
	 */
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

	/**
	 * Gets the notifications.
	 *
	 * @param model
	 *            the model
	 * @return the notifications
	 */
	@RequestMapping(value = {
			"/users/notifications" },
		method = RequestMethod.GET)
	public String getNotifications(Model model) {
		User user = getLoggedInUser();
		model.addAttribute("user", user);
		return "users/notifications";
	}

	/**
	 * List users.
	 *
	 * @param model
	 *            the model
	 * @return the string
	 */
	@RequestMapping(value = { "/users" }, method = RequestMethod.GET)
	public String listUsers(Model model) {
		model.addAttribute("listUsers", this.userService.listUsers());
		return "users";
	}

	/**
	 * Mark notification as seen.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the string
	 */
	@RequestMapping(value = {
			"/users/notifications/seen/{id}" },
		method = RequestMethod.POST)
	public String markNotificationAsSeen(@PathVariable("id") Long id,
		Model model) {
		User user = getLoggedInUser();
		Iterator<Notification> i = user.getNotifications().iterator();
		while (i.hasNext()) {
			Notification notification = i.next();
			if (notification.getId() == id.longValue()) {
				notification.setSeen(true);
				notificationService.updateNotification(notification);
			}
		}

		model.addAttribute("user", user);
		return "users/notifications";
	}

	/**
	 * Removes the user.
	 *
	 * @param id
	 *            the id
	 * @return the string
	 */
	@RequestMapping(value = { "/users/delete/{id}" }, method = RequestMethod.GET)
	public String removeUser(@PathVariable("id") Long id) {

		this.userService.deleteUser(id);
		return "redirect:/users";
	}

	/**
	 * Save user.
	 *
	 * @param user
	 *            the user
	 * @param result
	 *            the result
	 * @param request
	 *            the request
	 * @return the string
	 */
	// For add and update user both
	@RequestMapping(value = "/users/save", method = RequestMethod.POST)
	public String saveUser(@ModelAttribute("user") User user,
		BindingResult result, HttpServletRequest request) {
		if (result.hasErrors()) {
			System.err.println(result.toString());
		}

		HttpServletRequestDecorator req = new HttpServletRequestDecorator(
			request);

		// extract extra parameters
		String oldPassword = request.getParameter("oldPassword") != null
			? request.getParameter("oldPassword")
			: "";
		String newPassword = request.getParameter("password") != null
			? request.getParameter("password")
			: "";
		String confirmPassword = request.getParameter("confirmPassword") != null
			? request.getParameter("confirmPassword")
			: "";

		User loggedInUser = getLoggedInUser();

		User oldUser = userService.getUserByID(user.getId());
		if (oldUser != null) {
			user.setNotifications(oldUser.getNotifications());
		}

		// When changing our own password only change the password if the old
		// password field matches the old password.
		if (user.getId() != 0 || user.getId() == loggedInUser.getId()) {

			user.setPassword(oldUser.getPassword());
			user.setEmail(oldUser.getEmail());

			if (user.getId() == loggedInUser.getId()
				&& newPassword.length() > 0) {
				// if old password is correct
				if (encoder.matches(oldPassword, user.getPassword())) {
					if (!newPassword.equals(confirmPassword)) {
						req.addMessage("Passwords did not match, password not saved");
						return "redirect:/users/myprofile";
					}
					// if a new password has been set, encrypt it and assign to
					// user object
					if (newPassword.length() > 0) {
						String encryptedPassword = encoder.encode(newPassword);
						user.setPassword(encryptedPassword);
						req.addMessage("Password was updated sucessfully");
					}

				} else {
					req.addMessage("Old password was incorrect.");
					return "redirect:/users/myprofile";
				}

			}

			// If it is not an existing user, or if editing another user and the
			// logged in user has admin privileges we can just update their
			// password.
		} else if (user.getId() == 0 || (user.getId() != loggedInUser.getId()
			&& loggedInUser.getAuthorityStrings().contains("ROLE_ADMIN"))) {
			if (!newPassword.equals(confirmPassword)) {
				req.addMessage("Passwords did not match, password not saved");
				return "redirect:/users";
			}
			// The front end validation will already have checked a password
			// field matches a re-enter your password field, just set value
			String encryptedPassword = encoder.encode(newPassword);
			user.setPassword(encryptedPassword);
			req.addMessage("User was created sucessfully.");
		}

		// this will need to be set on the front end, can't pass back whole
		// POJO, but we can send back just the resourceId for this user, and
		// then extract it and assign back to the object here
		String resourceId = request.getParameter("resourceId");

		Resource resource = null;
		if (resourceId != null && !resourceId.equals("")
			&& !resourceId.equals("0")) {
			resource = resourceService
				.getResourceByID(Long.parseLong(resourceId));

		}
		user.setResource(resource);

		if (loggedInUser.getAuthorityStrings().contains("ROLE_ADMIN")) {
			String authority = request.getParameter("authority");
			Set<String> authorities = new HashSet<String>();
			if (authority.equals("ROLE_USER")) {
				authorities.add(authority);
			} else if (authority.equals("ROLE_ADMIN")) {
				authorities.add("ROLE_USER");
				authorities.add(authority);
			}
			user.setAuthorityStrings(authorities);

		} else {
			user.setResource(oldUser.getResource());
			user.setAuthorityStrings(oldUser.getAuthorityStrings());
		}

		if (user.getId() == 0) {
			// new user, add it
			this.userService.addUser(user);

			// add welcome notification
			Notification notification = new Notification("Welcome "
				+ user.getName()
				+ "! Your account has now been created, you may now start managing resources.");
			notificationService.addNotification(notification);
			user.addNotification(notification);
			userService.updateUser(user);

		} else {
			// existing user, call update
			this.userService.updateUser(user);
		}

		if (resource != null) {
			resource.setUser(user);
			resourceService.updateResource(resource);
		}

		if (loggedInUser.getId() == user.getId()) {
			return "redirect:/users/myprofile";
		} else {
			return "redirect:/users";
		}

	}
}
