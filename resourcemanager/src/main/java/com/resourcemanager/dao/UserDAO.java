package com.resourcemanager.dao;

import java.util.List;

import com.resourcemanager.model.UserDetails;

public interface UserDAO {

	void addUserDetails(UserDetails user);

	void deleteUserDetails(Long userID);

	UserDetails findUserDetailsByEmail(String email);

	UserDetails findUserDetailsByID(long id);

	List<UserDetails> getUserDetails();

	void updateUserDetails(UserDetails userDetails);

}
