package com.resourcemanager.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.resourcemanager.dao.NotificationDAO;
import com.resourcemanager.model.Notification;
import com.resourcemanager.service.NotificationService;

@Service
public class NofiticationServiceImpl implements NotificationService {

	@Autowired
	private NotificationDAO notificationDao;

	@Override
	public void addNotification(Notification notification) {
		notificationDao.addNotification(notification);
	}

	@Override
	public void deleteNotification(Long notificationID) {
		notificationDao.deleteNotification(notificationID);
	}

	@Override
	public Notification getNotificationByID(Long notificationID) {
		return notificationDao.findNotificationByID(notificationID);
	}

	@Override
	public List<Notification> listNotifications() {
		return notificationDao.listNotifications();
	}

	@Override
	public void updateNotification(Notification notification) {
		notificationDao.updateNotification(notification);
	}
}