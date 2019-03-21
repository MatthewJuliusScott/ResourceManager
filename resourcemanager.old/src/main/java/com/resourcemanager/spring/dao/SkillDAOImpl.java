package com.resourcemanager.spring.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.resourcemanager.spring.model.Skill;

@Repository
public class SkillDAOImpl implements SkillDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(SkillDAOImpl.class);

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}

	@Override
	public void addSkill(Skill skill) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(skill);
		logger.info("Skill saved successfully, Skill Details="+skill);
	}

	@Override
	public void updateSkill(Skill skill) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(skill);
		logger.info("Skill updated successfully, Skill Details="+skill);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Skill> listSkills() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Skill> skillsList = session.createQuery("from Skill").list();
		for(Skill skill : skillsList){
			logger.info("Skill List::"+skill);
		}
		return skillsList;
	}

	@Override
	public Skill getSkillById(int id) {
		Session session = this.sessionFactory.getCurrentSession();		
		Skill skill = (Skill) session.load(Skill.class, new Integer(id));
		logger.info("Skill loaded successfully, Skill details="+skill);
		return skill;
	}

	@Override
	public void removeSkill(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Skill skill = (Skill) session.load(Skill.class, new Integer(id));
		if(null != skill){
			session.delete(skill);
		}
		logger.info("Skill deleted successfully, skill details="+skill);
	}

}
