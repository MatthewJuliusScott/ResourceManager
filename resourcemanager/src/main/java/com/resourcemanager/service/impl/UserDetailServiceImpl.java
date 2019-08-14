package com.resourcemanager.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.resourcemanager.dao.UserDAO;
import com.resourcemanager.service.UserService;

@Component(value = "userDetailService")
public class UserDetailServiceImpl implements UserDetailsService, UserService {

	@Autowired
	private UserDAO userDao;

	@Override
	public void addUserDetails(com.resourcemanager.model.UserDetails userDetails) {
		userDao.addUserDetails(userDetails);
	}

	@Override
	public void deleteUserDetails(Long userID) {
		userDao.deleteUserDetails(userID);
	}

	@Override
	public List<com.resourcemanager.model.UserDetails> getUserDetails() {
		return userDao.getUserDetails();
	}

	@Override
	public com.resourcemanager.model.UserDetails getUserDetailsByID(Long userID) {
		return userDao.findUserDetailsByID(userID);
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		com.resourcemanager.model.UserDetails user = userDao.findUserDetailsByEmail(email);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), user.getAuthorities());
	}

	@Override
	public void updateUserDetails(com.resourcemanager.model.UserDetails userDetails) {
		userDao.updateUserDetails(userDetails);
	}
}