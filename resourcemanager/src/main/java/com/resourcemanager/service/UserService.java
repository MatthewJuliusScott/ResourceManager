package com.resourcemanager.service;

import java.util.List;

import org.springframework.security.access.annotation.Secured;

import com.resourcemanager.model.User;

public interface UserService {

	@Secured("ROLE_ADMIN")
	public void addUser(User user);

	@Secured("ROLE_ADMIN")
	public void deleteUser(Long userID);

	public User getUserByID(Long userID);

	public User getUserByUserName(String userName);

	@Secured("ROLE_ADMIN")
	public List<User> listUsers();

	public void updateUser(User user);

}
