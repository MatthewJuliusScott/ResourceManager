package com.resourcemanager.dao.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
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
		UserDetails skill = null;
		CriteriaBuilder builder = sessionFactory.getCriteriaBuilder();
		CriteriaQuery<UserDetails> criteria = builder.createQuery(UserDetails.class);
		Root<UserDetails> root = criteria.from(UserDetails.class);
		criteria.select(root).where(builder.equal(root.get("email"), email));
		Query<UserDetails> query = sessionFactory.getCurrentSession().createQuery(criteria);
		List<UserDetails> entityList = query.getResultList();
		if (!entityList.isEmpty()) {
			skill = entityList.get(0);
		}
		return skill;
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