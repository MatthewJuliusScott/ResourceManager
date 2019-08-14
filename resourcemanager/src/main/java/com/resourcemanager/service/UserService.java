package com.resourcemanager.service;

import java.util.List;

import org.springframework.security.access.annotation.Secured;

import com.resourcemanager.model.UserDetails;

public interface UserService {

	@Secured("ROLE_ADMIN")
	public void addUserDetails(UserDetails user);

	@Secured("ROLE_ADMIN")
	public void deleteUserDetails(Long userID);

	List<UserDetails> getUserDetails();

	@Secured("ROLE_ADMIN")
	public UserDetails getUserDetailsByID(Long userID);

	@Secured("ROLE_ADMIN")
	public void updateUserDetails(UserDetails userDetails);
}
