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

import com.resourcemanager.dao.SkillDAO;
import com.resourcemanager.model.Skill;
import com.resourcemanager.dao.ResourceDAO;

@Repository
public class SkillDAOImpl implements SkillDAO {

	private static final Logger		logger	= LoggerFactory.getLogger(SkillDAOImpl.class);

	@Autowired
	private EntityManagerFactory	entityManager;

	@Override
	public void addSkill(Skill skill) {
		getCurrentSession().persist(skill);
		logger.info("Skill saved successfully, Skill details=" + skill);
	}

	@Override
	public void deleteSkill(Long id) {
		Skill skill = getCurrentSession().find(Skill.class, id);
		getCurrentSession().remove(skill);
		logger.info("Skill deleted successfully, skill details=" + skill);
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
	public Skill getSkillByID(Long id) {
		Skill skill = getCurrentSession().find(Skill.class, id);
		logger.info("Skill retrieved successfully, skill details=" + skill);
		return skill;
	}

	@Override
	public Skill getSkillByName(String name) {
		Skill skill = null;
		CriteriaBuilder builder = getCurrentSessionFactory().getCriteriaBuilder();
		CriteriaQuery<Skill> criteria = builder.createQuery(Skill.class);
		Root<Skill> root = criteria.from(Skill.class);
		criteria.select(root).where(builder.equal(root.get("name"), name));
		Query<Skill> query = getCurrentSession().createQuery(criteria);
		List<Skill> entityList = query.getResultList();
		if (!entityList.isEmpty()) {
			skill = entityList.get(0);
		}
		return skill;
	}

	@Override
	public List<Skill> listSkills() {
		CriteriaBuilder builder = getCurrentSessionFactory().getCriteriaBuilder();
		CriteriaQuery<Skill> criteria = builder.createQuery(Skill.class);
		Root<Skill> root = criteria.from(Skill.class);
		criteria.select(root);
		List<Skill> skillsList = getCurrentSession().createQuery(criteria).getResultList();
		for (Skill skill : skillsList) {
			logger.info("Skill List::" + skill);
		}
		return skillsList;
	}

	@Override
	public void updateSkill(Skill skill) {
		getCurrentSession().merge(skill);
		logger.info("Skill updated successfully, Skill details=" + skill);
	}



}
