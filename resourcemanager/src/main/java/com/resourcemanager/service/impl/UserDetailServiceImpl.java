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
	public void addUser(com.resourcemanager.model.UserDetails user) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteUser(Long userID) {
		// TODO Auto-generated method stub

	}

	@Override
	public com.resourcemanager.model.UserDetails getUserById(Long userID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<com.resourcemanager.model.UserDetails> getUsers() {
		return userDao.getUserDetails();
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		com.resourcemanager.model.UserDetails user = userDao.findUserByEmail(email);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), user.getAuthorities());
	}

	@Override
	public void updateUser(com.resourcemanager.model.UserDetails user) {
		// TODO Auto-generated method stub

	}
}