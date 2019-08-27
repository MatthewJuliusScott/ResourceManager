package com.resourcemanager.service;

import java.util.List;

import org.springframework.security.access.annotation.Secured;

import com.resourcemanager.model.Notification;

public interface NotificationService {

	@Secured("ROLE_ADMIN")
	public void addNotification(Notification notification);

	@Secured("ROLE_ADMIN")
	public void deleteNotification(Long notificationID);

	@Secured("ROLE_USER")
	public Notification getNotificationByID(Long notificationID);

	@Secured("ROLE_USER")
	public List<Notification> listNotifications();

	@Secured("ROLE_USER")
	public void updateNotification(Notification notification);

}
