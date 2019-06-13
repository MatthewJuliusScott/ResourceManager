package com.resourcemanager.dao;

import java.util.List;

import com.resourcemanager.model.UserDetails;

public interface UserDAO {

	UserDetails findUserByEmail(String email);

	UserDetails findUserById(long id);

	List<UserDetails> getUserDetails();

}
