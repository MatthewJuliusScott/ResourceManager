/*
 * 
 */
package com.resourcemanager.dao;

import java.util.List;

import com.resourcemanager.model.Notification;

/**
 * The Interface NotificationDAO.
 */
public interface NotificationDAO {

	/**
	 * Adds the notification.
	 *
	 * @param notification the notification
	 */
	void addNotification(Notification notification);

	/**
	 * Delete notification.
	 *
	 * @param notificationID the notification ID
	 */
	void deleteNotification(Long notificationID);

	/**
	 * Find notification by ID.
	 *
	 * @param id the id
	 * @return the notification
	 */
	Notification findNotificationByID(long id);

	/**
	 * List notifications.
	 *
	 * @return the list
	 */
	List<Notification> listNotifications();

	/**
	 * Update notification.
	 *
	 * @param notification the notification
	 */
	void updateNotification(Notification notification);

}
