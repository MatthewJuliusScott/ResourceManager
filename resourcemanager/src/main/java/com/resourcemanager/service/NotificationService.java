/*
 * 
 */
package com.resourcemanager.service;

import java.util.List;

import org.springframework.security.access.annotation.Secured;

import com.resourcemanager.model.Notification;

/**
 * The Interface NotificationService.
 */
public interface NotificationService {

	/**
	 * Adds the notification.
	 *
	 * @param notification the notification
	 */
	@Secured("ROLE_USER")
	public void addNotification(Notification notification);

	/**
	 * Delete notification.
	 *
	 * @param notificationID the notification ID
	 */
	@Secured("ROLE_USER")
	public void deleteNotification(Long notificationID);

	/**
	 * Gets the notification by ID.
	 *
	 * @param notificationID the notification ID
	 * @return the notification by ID
	 */
	@Secured("ROLE_USER")
	public Notification getNotificationByID(Long notificationID);

	/**
	 * List notifications.
	 *
	 * @return the list
	 */
	@Secured("ROLE_USER")
	public List<Notification> listNotifications();

	/**
	 * Update notification.
	 *
	 * @param notification the notification
	 */
	@Secured("ROLE_USER")
	public void updateNotification(Notification notification);

}
