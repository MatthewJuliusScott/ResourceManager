package com.resourcemanager.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.resourcemanager.dao.UserDAO;
import com.resourcemanager.model.UserDetails;

@Component
public class UserDAOImpl implements UserDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public UserDetails findUserByEmail(String email) {
		UserDetails userDetails = null;
		Criteria criteria = sessionFactory.openSession().createCriteria(UserDetails.class);
		criteria.add(Restrictions.eq("email", email));
		List<UserDetails> entityList = criteria.list();
		if (!entityList.isEmpty()) {
			userDetails = entityList.get(0);
		}
		return userDetails;
	}

	@Override
	public UserDetails findUserById(long id) {
		UserDetails userDetails = null;
		Criteria criteria = sessionFactory.openSession().createCriteria(UserDetails.class);
		criteria.add(Restrictions.eq("id", id));
		List<UserDetails> entityList = criteria.list();
		if (!entityList.isEmpty()) {
			userDetails = entityList.get(0);
		}
		return userDetails;
	}

	@Override
	public List<UserDetails> getUserDetails() {
		Criteria criteria = sessionFactory.openSession().createCriteria(UserDetails.class);
		return criteria.list();
	}

}