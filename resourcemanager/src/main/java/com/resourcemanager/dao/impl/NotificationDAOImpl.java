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

import com.resourcemanager.dao.NotificationDAO;
import com.resourcemanager.model.Notification;

@Repository
@Transactional
public class NotificationDAOImpl implements NotificationDAO {

	private static final Logger		logger	= LoggerFactory.getLogger(NotificationDAOImpl.class);

	@Autowired
	private EntityManagerFactory	entityManager;

	@Override
	public void addNotification(Notification notification) {
		getCurrentSession().persist(notification);
		logger.info("Notification saved successfully, Notification details=" + notification);
	}

	@Override
	public void deleteNotification(Long notificationID) {
		Notification notification = getCurrentSession().find(Notification.class, notificationID);
		getCurrentSession().remove(notification);
		logger.info("Notification deleted successfully, Notification details=" + notification);
	}

	@Override
	public Notification findNotificationByID(long id) {
		Notification notification = getCurrentSession().find(Notification.class, id);
		logger.info("Notification retrieved successfully, notification details=" + notification);
		return notification;
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
	public List<Notification> listNotifications() {
		CriteriaBuilder builder = getCurrentSessionFactory().getCriteriaBuilder();
		CriteriaQuery<Notification> criteria = builder.createQuery(Notification.class);
		Root<Notification> root = criteria.from(Notification.class);
		criteria.select(root);
		Query<Notification> query = getCurrentSession().createQuery(criteria);
		return query.getResultList();
	}

	@Override
	public void updateNotification(Notification notification) {
		getCurrentSession().merge(notification);
		logger.info("Notification updated successfully, Notification details=" + notification);
	}

}