/*
 * 
 */
package com.resourcemanager.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.resourcemanager.dao.NotificationDAO;
import com.resourcemanager.model.Notification;
import com.resourcemanager.service.NotificationService;

/**
 * The Class NofiticationServiceImpl.
 */
@Service
public class NofiticationServiceImpl implements NotificationService {

	/** The notification dao. */
	@Autowired
	private NotificationDAO notificationDao;

	/* (non-Javadoc)
	 * @see com.resourcemanager.service.NotificationService#addNotification(com.resourcemanager.model.Notification)
	 */
	@Override
	public void addNotification(Notification notification) {
		notificationDao.addNotification(notification);
	}

	/* (non-Javadoc)
	 * @see com.resourcemanager.service.NotificationService#deleteNotification(java.lang.Long)
	 */
	@Override
	public void deleteNotification(Long notificationID) {
		notificationDao.deleteNotification(notificationID);
	}

	/* (non-Javadoc)
	 * @see com.resourcemanager.service.NotificationService#getNotificationByID(java.lang.Long)
	 */
	@Override
	public Notification getNotificationByID(Long notificationID) {
		return notificationDao.findNotificationByID(notificationID);
	}

	/* (non-Javadoc)
	 * @see com.resourcemanager.service.NotificationService#listNotifications()
	 */
	@Override
	public List<Notification> listNotifications() {
		return notificationDao.listNotifications();
	}

	/* (non-Javadoc)
	 * @see com.resourcemanager.service.NotificationService#updateNotification(com.resourcemanager.model.Notification)
	 */
	@Override
	public void updateNotification(Notification notification) {
		notificationDao.updateNotification(notification);
	}
}