package com.resourcemanager.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.resourcemanager.dao.UserDAO;
import com.resourcemanager.model.User;
import com.resourcemanager.service.UserService;

@Component(value = "userDetailService")
public class UserServiceImpl implements UserService, UserDetailsService {

	@Autowired
	private UserDAO userDao;

	@Override
	public void addUser(User user) {
		userDao.addUser(user);
	}

	@Override
	public void deleteUser(Long userID) {
		userDao.deleteUser(userID);
	}

	@Override
	public User getUserByID(Long userID) {
		return userDao.findUserByID(userID);
	}

	@Override
	public User getUserByUserName(String userName) {
		return userDao.findUserByEmail(userName);
	}

	@Override
	public List<User> listUsers() {
		return userDao.listUsers();
	}

	@Override
	public org.springframework.security.core.userdetails.User loadUserByUsername(String email) throws UsernameNotFoundException {
		com.resourcemanager.model.User user = userDao.findUserByEmail(email);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), user.getAuthorities());
	}

	@Override
	public void updateUser(User user) {
		userDao.updateUser(user);
	}
}