/*
 * 
 */
package com.resourcemanager.service.impl;

import java.sql.Array;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.resourcemanager.dao.UserDAO;
import com.resourcemanager.model.User;
import com.resourcemanager.service.UserService;

/**
 * The Class UserServiceImpl.
 */
@Component(value = "userDetailService")
public class UserServiceImpl implements UserService, UserDetailsService {

	/** The user dao. */
	@Autowired
	private UserDAO userDao;

	/* (non-Javadoc)
	 * @see com.resourcemanager.service.UserService#addUser(com.resourcemanager.model.User)
	 */
	@Override
	public void addUser(User user) {
		userDao.addUser(user);
	}

	/* (non-Javadoc)
	 * @see com.resourcemanager.service.UserService#deleteUser(java.lang.Long)
	 */
	@Override
	public void deleteUser(Long userID) {
		userDao.deleteUser(userID);
	}

	/* (non-Javadoc)
	 * @see com.resourcemanager.service.UserService#getUserByID(java.lang.Long)
	 */
	@Override
	public User getUserByID(Long userID) {
		return userDao.findUserByID(userID);
	}

	/* (non-Javadoc)
	 * @see com.resourcemanager.service.UserService#getUserByUserName(java.lang.String)
	 */
	@Override
	public User getUserByUserName(String userName) {
		return userDao.findUserByEmail(userName);
	}

	/* (non-Javadoc)
	 * @see com.resourcemanager.service.UserService#listUsers()
	 */
	@Override
	public List<User> listUsers() {
		return userDao.listUsers();
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
	 */
	@Override
	public org.springframework.security.core.userdetails.User loadUserByUsername(String email) throws UsernameNotFoundException {
		com.resourcemanager.model.User user = userDao.findUserByEmail(email);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), user.getAuthorities());
	}

	/* (non-Javadoc)
	 * @see com.resourcemanager.service.UserService#updateUser(com.resourcemanager.model.User)
	 */
	@Override
	public void updateUser(User user) {
		userDao.updateUser(user);
	}
}