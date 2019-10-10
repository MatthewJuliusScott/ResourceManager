/*
 *
 */
package com.resourcemanager.dao.impl;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.resourcemanager.dao.AllocationDAO;
import com.resourcemanager.model.Allocation;

/**
 * Concrete implementation of AllocationDAO. Uses Hibernate and JPA to provide a data source agnostic implementation, not specific
 * to any particular relational database technology or dialect.
 */
@Repository
@Transactional
public class AllocationDAOImpl implements AllocationDAO {

	/** The Constant logger. */
	private static final Logger		logger	= LoggerFactory.getLogger(AllocationDAOImpl.class);

	/** The entity manager. */
	@Autowired
	private EntityManagerFactory	entityManager;

	/*
	 * (non-Javadoc)
	 * @see com.resourcemanager.dao.AllocationDAO#addAllocation(com.resourcemanager.model.Allocation)
	 */
	@Override
	public void addAllocation(Allocation allocation) {
		getCurrentSession().persist(allocation);
		logger.info("Allocation saved successfully, Allocation Details=" + allocation);
	}

	/*
	 * (non-Javadoc)
	 * @see com.resourcemanager.dao.AllocationDAO#deleteAllocation(java.lang.Long)
	 */
	@Override
	public void deleteAllocation(Allocation allocation) {
		getCurrentSession().remove(allocation);
		logger.info("Allocation deleted successfully, allocation details=" + allocation);
	}

	/*
	 * (non-Javadoc)
	 * @see com.resourcemanager.dao.AllocationDAO#getAllocationByID(java.lang.Long)
	 */
	@Override
	public Allocation getAllocationByID(Long id) {
		Allocation allocation = getCurrentSession().find(Allocation.class, id);
		logger.info("Allocation retrieved successfully, allocation details=" + allocation);
		return allocation;
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
	 * @see com.resourcemanager.dao.AllocationDAO#listAllocations()
	 */
	@Override
	public List<Allocation> listAllocations() {
		CriteriaBuilder builder = getCurrentSessionFactory().getCriteriaBuilder();
		CriteriaQuery<Allocation> criteria = builder.createQuery(Allocation.class);
		Root<Allocation> root = criteria.from(Allocation.class);
		criteria.select(root);
		List<Allocation> allocationsList = getCurrentSession().createQuery(criteria).getResultList();
		for (Allocation allocation : allocationsList) {
			logger.info("Allocation List::" + allocation);
		}
		return allocationsList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.resourcemanager.dao.AllocationDAO#listRequiredAllocations(java.time.LocalDate, java.time.LocalDate)
	 */
	@Override
	public List<Allocation> listRequiredAllocations(LocalDate startDate, LocalDate endDate) {
		CriteriaBuilder builder = getCurrentSessionFactory().getCriteriaBuilder();
		CriteriaQuery<Allocation> criteria = builder.createQuery(Allocation.class);
		Root<Allocation> root = criteria.from(Allocation.class);
		Predicate startInTimePeriod = builder.between(root.get("startDate"), startDate, endDate);
		Predicate endInTimePeriod = builder.between(root.get("endDate"), startDate, endDate);
		Predicate notAllocation = builder.isNull(root.get("resource"));
		criteria.select(root).where(builder.and(startInTimePeriod, endInTimePeriod, notAllocation));
		// .and(startInTimePeriod, endInTimePeriod)
		List<Allocation> allocationsList = getCurrentSession().createQuery(criteria).getResultList();
		for (Allocation allocation : allocationsList) {
			logger.info("Allocation List::" + allocation);
		}
		return allocationsList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.resourcemanager.dao.AllocationDAO#updateAllocation(com.resourcemanager.model.Allocation)
	 */
	@Override
	public void updateAllocation(Allocation allocation) {
		getCurrentSession().merge(allocation);
		logger.info("Allocation updated successfully, Allocation Details=" + allocation);
	}

}
