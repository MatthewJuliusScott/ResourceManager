package com.resourcemanager.service;

import java.util.List;

import org.springframework.security.access.annotation.Secured;

import com.resourcemanager.model.UserDetails;

public interface UserService {

	@Secured("ROLE_ADMIN")
	public void addUser(UserDetails user);

	@Secured("ROLE_ADMIN")
	public void deleteUser(Long userID);

	@Secured("ROLE_ADMIN")
	public UserDetails getUserById(Long userID);

	List<UserDetails> getUsers();

	@Secured("ROLE_ADMIN")
	public void updateUser(UserDetails user);

}
