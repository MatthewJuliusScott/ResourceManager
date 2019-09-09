/*
 * 
 */
package com.resourcemanager.dao.impl;

import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.resourcemanager.dao.ProjectDAO;
import com.resourcemanager.model.Project;

/**
 * The Class ProjectDAOImpl.
 */
@Repository
@Transactional
public class ProjectDAOImpl implements ProjectDAO {

	/** The Constant logger. */
	private static final Logger		logger	= LoggerFactory.getLogger(ProjectDAOImpl.class);

	/** The entity manager. */
	@Autowired
	private EntityManagerFactory	entityManager;

	/* (non-Javadoc)
	 * @see com.resourcemanager.dao.ProjectDAO#addProject(com.resourcemanager.model.Project)
	 */
	@Override
	public void addProject(Project project) {
		getCurrentSession().persist(project);
		logger.info("Project saved successfully, Project Details=" + project);
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
	 * @see com.resourcemanager.dao.ProjectDAO#getProjectByID(java.lang.Long)
	 */
	@Override
	public Project getProjectByID(Long id) {
		Project project = getCurrentSession().find(Project.class, id);
		logger.info("Project retrieved successfully, project details=" + project);
		return project;
	}

	/* (non-Javadoc)
	 * @see com.resourcemanager.dao.ProjectDAO#listProjects()
	 */
	@Override
	public List<Project> listProjects() {
		CriteriaBuilder builder = getCurrentSessionFactory().getCriteriaBuilder();
		CriteriaQuery<Project> criteria = builder.createQuery(Project.class);
		Root<Project> root = criteria.from(Project.class);
		criteria.select(root);
		List<Project> projectsList = getCurrentSession().createQuery(criteria).getResultList();
		for (Project project : projectsList) {
			logger.info("Project List::" + project);
		}

		return projectsList;
	}

	/* (non-Javadoc)
	 * @see com.resourcemanager.dao.ProjectDAO#removeProject(java.lang.Long)
	 */
	@Override
	public void removeProject(Project project) {
		getCurrentSession().remove(project);
		logger.info("Project deleted successfully, project details=" + project);
	}

	/* (non-Javadoc)
	 * @see com.resourcemanager.dao.ProjectDAO#updateProject(com.resourcemanager.model.Project)
	 */
	@Override
	public void updateProject(Project project) {
		getCurrentSession().merge(project);
		logger.info("Project updated successfully, Project Details=" + project);
	}

}
