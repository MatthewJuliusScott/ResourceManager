package com.resourcemanager.dao;

import java.util.List;

import com.resourcemanager.model.Notification;

public interface NotificationDAO {

	void addNotification(Notification notification);

	void deleteNotification(Long notificationID);

	Notification findNotificationByID(long id);

	List<Notification> listNotifications();

	void updateNotification(Notification notification);

}
