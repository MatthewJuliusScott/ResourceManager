package com.resourcemanager.dao.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.resourcemanager.dao.UserDAO;
import com.resourcemanager.model.UserDetails;

@Repository
public class UserDAOImpl implements UserDAO {

	private static final Logger	logger	= LoggerFactory.getLogger(UserDAOImpl.class);

	@Autowired
	private SessionFactory		sessionFactory;

	@Override
	public void addUserDetails(UserDetails user) {
		getCurrentSession().persist(user);
		logger.info("UserDetails saved successfully, UserDetails details=" + user);
	}

	@Override
	public void deleteUserDetails(Long userID) {
		UserDetails userDetails = getCurrentSession().find(UserDetails.class, userID);
		getCurrentSession().remove(userDetails);
		logger.info("UserDetails deleted successfully, UserDetails details=" + userDetails);
	}

	@Override
	public UserDetails findUserDetailsByEmail(String email) {
		UserDetails userDetails = null;
		CriteriaBuilder builder = sessionFactory.getCriteriaBuilder();
		CriteriaQuery<UserDetails> criteria = builder.createQuery(UserDetails.class);
		Root<UserDetails> root = criteria.from(UserDetails.class);
		criteria.select(root).where(builder.equal(root.get("email"), email));
		Query<UserDetails> query = getCurrentSession().createQuery(criteria);
		List<UserDetails> entityList = query.getResultList();
		if (!entityList.isEmpty()) {
			userDetails = entityList.get(0);
			logger.info("UserDetails retrieved successfully, UserDetails details=" + userDetails);
		}
		return userDetails;
	}

	@Override
	public UserDetails findUserDetailsByID(long id) {
		UserDetails userDetails = null;
		CriteriaBuilder builder = sessionFactory.getCriteriaBuilder();
		CriteriaQuery<UserDetails> criteria = builder.createQuery(UserDetails.class);
		Root<UserDetails> root = criteria.from(UserDetails.class);
		criteria.select(root).where(builder.equal(root.get("id"), id));
		Query<UserDetails> query = getCurrentSession().createQuery(criteria);
		List<UserDetails> entityList = query.getResultList();
		if (!entityList.isEmpty()) {
			userDetails = entityList.get(0);
			logger.info("UserDetails retrieved successfully, UserDetails details=" + userDetails);
		}
		return userDetails;
	}

	protected Session getCurrentSession() {
		Session session;
		try {
			session = sessionFactory.unwrap(SessionFactory.class).getCurrentSession();
		} catch (HibernateException e) {
			session = sessionFactory.unwrap(SessionFactory.class).openSession();
		}
		return session;
	}

	protected SessionFactory getCurrentSessionFactory() {
		return sessionFactory.unwrap(SessionFactory.class);
	}

	@Override
	public List<UserDetails> getUserDetails() {
		CriteriaBuilder builder = sessionFactory.getCriteriaBuilder();
		CriteriaQuery<UserDetails> criteria = builder.createQuery(UserDetails.class);
		Root<UserDetails> root = criteria.from(UserDetails.class);
		criteria.select(root);
		Query<UserDetails> query = getCurrentSession().createQuery(criteria);
		return query.getResultList();
	}

	@Override
	public void updateUserDetails(UserDetails userDetails) {
		getCurrentSession().merge(userDetails);
		logger.info("Skill updated successfully, UserDetails details=" + userDetails);
	}

}