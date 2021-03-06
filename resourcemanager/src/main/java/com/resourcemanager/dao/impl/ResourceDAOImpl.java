/*
 *
 */
package com.resourcemanager.dao.impl;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.resourcemanager.dao.ResourceDAO;
import com.resourcemanager.model.Allocation;
import com.resourcemanager.model.Resource;

/**
 * Concrete implementation of ResourceDAO. Uses Hibernate and JPA to provide a data source agnostic implementation, not specific
 * to any particular relational database technology or dialect.
 */
@Repository
@Transactional
public class ResourceDAOImpl implements ResourceDAO {

	/** The Constant logger. */
	private static final Logger		logger	= LoggerFactory.getLogger(ResourceDAOImpl.class);

	/** The entity manager. */
	@Autowired
	private EntityManagerFactory	entityManager;

	/*
	 * (non-Javadoc)
	 * @see com.resourcemanager.dao.ResourceDAO#addResource(com.resourcemanager.model.Resource)
	 */
	@Override
	public void addResource(Resource resource) {
		getCurrentSession().persist(resource);
		logger.info("Resource saved successfully, Resource Details=" + resource);
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

	/*
	 * (non-Javadoc)
	 * @see com.resourcemanager.dao.ResourceDAO#getResourceByID(java.lang.Long)
	 */
	@Override
	public Resource getResourceByID(Long id) {
		Resource resource = getCurrentSession().find(Resource.class, id);
		logger.info("Resource retrieved successfully, resource details=" + resource);
		return resource;
	}

	/*
	 * (non-Javadoc)
	 * @see com.resourcemanager.dao.ResourceDAO#listResources()
	 */
	@Override
	public List<Resource> listResources() {
		CriteriaBuilder builder = getCurrentSessionFactory().getCriteriaBuilder();
		CriteriaQuery<Resource> criteria = builder.createQuery(Resource.class);
		Root<Resource> root = criteria.from(Resource.class);
		criteria.select(root);
		List<Resource> resourcesList = getCurrentSession().createQuery(criteria).getResultList();
		for (Resource resource : resourcesList) {
			logger.info("Resource List::" + resource);
		}

		Collections.sort(resourcesList, new Comparator<Resource>() {
			@Override
			public int compare(Resource r1, Resource r2) {
				return r1.getName().compareTo(r2.getName());
			}
		});
		return resourcesList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.resourcemanager.dao.ResourceDAO#removeResource(java.lang.Long)
	 */
	@Override
	public void removeResource(Long id) {
		Resource resource = getCurrentSession().find(Resource.class, id);
		getCurrentSession().remove(resource);
		logger.info("Resource deleted successfully, resource details=" + resource);
	}

	/*
	 * (non-Javadoc)
	 * @see com.resourcemanager.dao.ResourceDAO#searchResources(long, java.time.LocalDate, java.time.LocalDate, int)
	 */
	@Override
	public List<Resource> searchResources(long skillId, LocalDate startDate, LocalDate endDate, int hours) {
		CriteriaBuilder builder = getCurrentSessionFactory().getCriteriaBuilder();
		CriteriaQuery<Resource> query = builder.createQuery(Resource.class);
		Root<Resource> root = query.from(Resource.class);

		try {

			// get resources that have the skill
			// and have greater than or equal to hours
			Predicate skillIdMatch = builder.isMember(skillId, root.get("skills"));
			Predicate greaterThanOrEqualHours = builder.ge(root.get("hours"), hours);

			// create a subquery to get resources that have allocations in the time period specified between startDate and endDate
			Subquery<Long> sub = query.subquery(Long.class);
			Root<Allocation> subRoot = sub.from(Allocation.class);
			Join<Allocation, Resource> allocations = subRoot.join("resource", JoinType.INNER);
			Predicate startNoAllocationsInTimePeriod = builder.between(subRoot.get("startDate"), startDate, endDate);
			Predicate endNoAllocationsInTimePeriod = builder.between(subRoot.get("endDate"), startDate, endDate);
			sub.select(allocations.get("id"));
			sub.where(builder.and(startNoAllocationsInTimePeriod, endNoAllocationsInTimePeriod));

			// exclude resources who have allocations during the specified time period
			Predicate priorAllocations = root.get("id").in(sub).not();

			query.select(root).where(builder.and(skillIdMatch, greaterThanOrEqualHours, priorAllocations));
			query.orderBy(builder.desc(root.get("hours")));

		} catch (Exception e) {
			e.printStackTrace();
		}

		List<Resource> resourcesList = getCurrentSession().createQuery(query).getResultList();

		for (Resource resource : resourcesList) {
			logger.info("Resource List::" + resource);
		}
		return resourcesList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.resourcemanager.dao.ResourceDAO#updateResource(com.resourcemanager.model.Resource)
	 */
	@Override
	public void updateResource(Resource resource) {
		getCurrentSession().merge(resource);
		logger.info("Resource updated successfully, Resource Details=" + resource);
	}

}
