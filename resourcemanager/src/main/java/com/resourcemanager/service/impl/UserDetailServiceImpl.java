package com.resourcemanager.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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

	private List<GrantedAuthority> getAuthority() {
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"), new SimpleGrantedAuthority("ROLE_ADMIN"));
		// TODO : will need to implement different role levels and include that in our UserDetail model
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
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), getAuthority());
	}
}