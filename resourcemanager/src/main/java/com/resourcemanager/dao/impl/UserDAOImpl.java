/*
 * 
 */
package com.resourcemanager.dao.impl;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.persistence.EntityManagerFactory;
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
import org.springframework.transaction.annotation.Transactional;

import com.resourcemanager.dao.UserDAO;
import com.resourcemanager.model.User;

/**
 * The Class UserDAOImpl.
 */
@Repository
@Transactional
public class UserDAOImpl implements UserDAO {

	/** The Constant logger. */
	private static final Logger		logger	= LoggerFactory.getLogger(UserDAOImpl.class);

	/** The entity manager. */
	@Autowired
	private EntityManagerFactory	entityManager;

	/* (non-Javadoc)
	 * @see com.resourcemanager.dao.UserDAO#addUser(com.resourcemanager.model.User)
	 */
	@Override
	public void addUser(User user) {
		getCurrentSession().persist(user);
		logger.info("User saved successfully, User details=" + user);
	}

	/* (non-Javadoc)
	 * @see com.resourcemanager.dao.UserDAO#deleteUser(java.lang.Long)
	 */
	@Override
	public void deleteUser(Long userID) {
		User user = getCurrentSession().find(User.class, userID);
		getCurrentSession().remove(user);
		logger.info("User deleted successfully, User details=" + user);
	}

	/* (non-Javadoc)
	 * @see com.resourcemanager.dao.UserDAO#findUserByEmail(java.lang.String)
	 */
	@Override
	public User findUserByEmail(String email) {
		User user = null;
		CriteriaBuilder builder = getCurrentSessionFactory().getCriteriaBuilder();
		CriteriaQuery<User> criteria = builder.createQuery(User.class);
		Root<User> root = criteria.from(User.class);
		criteria.select(root).where(builder.equal(root.get("email"), email));
		Query<User> query = getCurrentSession().createQuery(criteria);
		List<User> entityList = query.getResultList();
		if (!entityList.isEmpty()) {
			user = entityList.get(0);
			logger.info("User retrieved successfully, User details=" + user);
		}
		return user;
	}

	/* (non-Javadoc)
	 * @see com.resourcemanager.dao.UserDAO#findUserByID(long)
	 */
	@Override
	public User findUserByID(long id) {
		User user = getCurrentSession().find(User.class, id);
		logger.info("User retrieved successfully, user details=" + user);
		return user;
	}

	/**
	 * Gets the current session.
	 *
	 * @return the current session
	 */
	protected Session getCurrentSession() {
		Session session;
		try {
			session = entityManager.unwrap(SessionFactory.class).getCurrentSession();
		} catch (HibernateException e) {
			session = entityManager.unwrap(SessionFactory.class).openSession();
		}
		return session;
	}

	/**
	 * Gets the current session factory.
	 *
	 * @return the current session factory
	 */
	protected SessionFactory getCurrentSessionFactory() {
		return entityManager.unwrap(SessionFactory.class);
	}

	/* (non-Javadoc)
	 * @see com.resourcemanager.dao.UserDAO#listUsers()
	 */
	@Override
	public List<User> listUsers() {
		CriteriaBuilder builder = getCurrentSessionFactory().getCriteriaBuilder();
		CriteriaQuery<User> criteria = builder.createQuery(User.class);
		Root<User> root = criteria.from(User.class);
		criteria.select(root);
		Query<User> query = getCurrentSession().createQuery(criteria);
		List<User> users = query.getResultList();  
		Collections.sort(users, new Comparator<User>() {
			public int compare(User u1, User u2) {
				return u1.getName().compareTo(u2.getName());
			}
		});
		
		return users;
	}

	/* (non-Javadoc)
	 * @see com.resourcemanager.dao.UserDAO#updateUser(com.resourcemanager.model.User)
	 */
	@Override
	public void updateUser(User user) {
		getCurrentSession().merge(user);
		logger.info("User updated successfully, User details=" + user);
	}

}