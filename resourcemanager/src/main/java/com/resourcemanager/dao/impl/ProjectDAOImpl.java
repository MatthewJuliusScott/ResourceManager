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
import com.resourcemanager.dao.ProjectDAO;
import com.resourcemanager.model.Project;

@Repository
public class ProjectDAOImpl implements ProjectDAO {

	private static final Logger		logger	= LoggerFactory.getLogger(ProjectDAOImpl.class);
	
	protected Session getCurrentSession() {
        return entityManager.unwrap(SessionFactory.class).getCurrentSession();
    }
	
	protected SessionFactory getCurrentSessionFactory() {
        return entityManager.unwrap(SessionFactory.class);
    }

	@Autowired
    private EntityManagerFactory entityManager;

	@Override
	public void addProject(Project project) {
		getCurrentSession().persist(project);
		logger.info("Project saved successfully, Project Details=" + project);
	}

	@Override
	public Project getProjectById(int id) {
		Project project = getCurrentSession().find(Project.class, id);
		logger.info("Project retrieved successfully, project details=" + project);
		return project;
	}

	@Override
	public List<Project> listProjects() {
		CriteriaBuilder builder = getCurrentSessionFactory().getCriteriaBuilder();
		CriteriaQuery criteria = builder.createQuery(Project.class);
		Root contactRoot = criteria.from(Project.class);
		criteria.select(contactRoot);
		List<Project> projectsList = getCurrentSession().createQuery(criteria).getResultList();
		for (Project project : projectsList) {
			logger.info("Project List::" + project);
		}
		return projectsList;
	}

	@Override
	public void removeProject(int id) {
		Project project = getCurrentSession().find(Project.class, id);
		getCurrentSession().remove(project);
		logger.info("Project deleted successfully, project details=" + project);
	}

	@Override
	public void updateProject(Project project) {
		getCurrentSession().merge(project);
		logger.info("Project updated successfully, Project Details=" + project);
	}

}
