package com.resourcemanager.dao;

import java.util.List;

import com.resourcemanager.model.User;

public interface UserDAO {

	void addUser(User user);

	void deleteUser(Long userID);

	User findUserByEmail(String email);

	User findUserByID(long id);

	List<User> listUsers();

	void updateUser(User user);

}
