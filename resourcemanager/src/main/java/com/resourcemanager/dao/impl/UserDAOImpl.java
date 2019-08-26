package com.resourcemanager.dao.impl;

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

@Repository
@Transactional
public class UserDAOImpl implements UserDAO {

	private static final Logger		logger	= LoggerFactory.getLogger(UserDAOImpl.class);

	@Autowired
	private EntityManagerFactory	entityManager;

	@Override
	public void addUser(User user) {
		getCurrentSession().persist(user);
		logger.info("User saved successfully, User details=" + user);
	}

	@Override
	public void deleteUser(Long userID) {
		User user = getCurrentSession().find(User.class, userID);
		getCurrentSession().remove(user);
		logger.info("User deleted successfully, User details=" + user);
	}

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

	@Override
	public User findUserByID(long id) {
		User user = getCurrentSession().find(User.class, id);
		logger.info("User retrieved successfully, user details=" + user);
		return user;
	}

	protected Session getCurrentSession() {
		Session session;
		try {
			session = entityManager.unwrap(SessionFactory.class).getCurrentSession();
		} catch (HibernateException e) {
			session = entityManager.unwrap(SessionFactory.class).openSession();
		}
		return session;
	}

	protected SessionFactory getCurrentSessionFactory() {
		return entityManager.unwrap(SessionFactory.class);
	}

	@Override
	public List<User> listUsers() {
		CriteriaBuilder builder = getCurrentSessionFactory().getCriteriaBuilder();
		CriteriaQuery<User> criteria = builder.createQuery(User.class);
		Root<User> root = criteria.from(User.class);
		criteria.select(root);
		Query<User> query = getCurrentSession().createQuery(criteria);
		return query.getResultList();
	}

	@Override
	public void updateUser(User user) {
		getCurrentSession().merge(user);
		logger.info("User updated successfully, User details=" + user);
	}

}