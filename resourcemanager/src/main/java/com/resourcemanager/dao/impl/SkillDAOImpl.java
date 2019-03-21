package com.resourcemanager.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
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

import com.resourcemanager.dao.SkillDAO;
import com.resourcemanager.model.Skill;

@Repository
public class SkillDAOImpl implements SkillDAO {

	private static final Logger		logger	= LoggerFactory.getLogger(SkillDAOImpl.class);

	@Autowired
	private EntityManagerFactory	entityManagerFactory;

	@Override
	public void addSkill(Skill skill) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(skill);
		entityManager.getTransaction().commit();
		entityManager.close();
		logger.info("Skill saved successfully, Skill Details=" + skill);
	}

	@Override
	public Skill getSkillById(int id) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		Skill skill = entityManager.find(Skill.class, id);
		entityManager.getTransaction().commit();
		entityManager.close();
		return skill;
	}

	@Override
	public List<Skill> listSkills() {
		Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery criteria = builder.createQuery(Skill.class);
		Root contactRoot = criteria.from(Skill.class);
		criteria.select(contactRoot);
		List<Skill> skillsList = session.createQuery(criteria).getResultList();
		for (Skill skill : skillsList) {
			logger.info("Skill List::" + skill);
		}
		return skillsList;
	}

	@Override
	public void removeSkill(int id) {
		Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Skill skill = entityManager.find(Skill.class, id);
		entityManager.getTransaction().begin();
		entityManager.remove(skill);
		entityManager.getTransaction().commit();
		entityManager.close();
		logger.info("Skill deleted successfully, skill details=" + skill);
	}

	@Override
	public void updateSkill(Skill skill) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.merge(skill);
		entityManager.getTransaction().commit();
		entityManager.close();
		logger.info("Skill updated successfully, Skill Details=" + skill);
	}

}
