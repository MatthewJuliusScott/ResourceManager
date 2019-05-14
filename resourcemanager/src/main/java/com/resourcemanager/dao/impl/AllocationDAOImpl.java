package com.resourcemanager.dao.impl;

import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.resourcemanager.dao.AllocationDAO;
import com.resourcemanager.model.Allocation;

@Repository
public class AllocationDAOImpl implements AllocationDAO {

	private static final Logger		logger	= LoggerFactory.getLogger(AllocationDAOImpl.class);

	@Autowired
	private EntityManagerFactory	entityManager;

	@Override
	public void addAllocation(Allocation allocation) {
		getCurrentSession().persist(allocation);
		logger.info("Allocation saved successfully, Allocation Details=" + allocation);
	}

	@Override
	public void deleteAllocation(int id) {
		Allocation allocation = getCurrentSession().find(Allocation.class, id);
		getCurrentSession().remove(allocation);
		logger.info("Allocation deleted successfully, allocation details=" + allocation);
	}

	@Override
	public Allocation getAllocationById(int id) {
		Allocation allocation = getCurrentSession().find(Allocation.class, id);
		logger.info("Allocation retrieved successfully, allocation details=" + allocation);
		return allocation;
	}

	protected Session getCurrentSession() {
		return entityManager.unwrap(SessionFactory.class).getCurrentSession();
	}

	protected SessionFactory getCurrentSessionFactory() {
		return entityManager.unwrap(SessionFactory.class);
	}

	@Override
	public List<Allocation> listAllocations() {
		CriteriaBuilder builder = getCurrentSessionFactory().getCriteriaBuilder();
		CriteriaQuery criteria = builder.createQuery(Allocation.class);
		Root contactRoot = criteria.from(Allocation.class);
		criteria.select(contactRoot);
		List<Allocation> allocationsList = getCurrentSession().createQuery(criteria).getResultList();
		for (Allocation allocation : allocationsList) {
			logger.info("Allocation List::" + allocation);
		}
		return allocationsList;
	}

	@Override
	public void updateAllocation(Allocation allocation) {
		getCurrentSession().merge(allocation);
		logger.info("Allocation updated successfully, Allocation Details=" + allocation);
	}

}
