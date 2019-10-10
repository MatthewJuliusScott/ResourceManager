/*
 *
 */
package com.resourcemanager.service;

import java.util.List;

import org.springframework.security.access.annotation.Secured;

import com.resourcemanager.model.Notification;

/**
 * Provides an abstract interface for a single service layer for Notifications. The services do all the logic and data
 * manipulation on the data model and pass the updated model to the persistence layer.
 */
public interface NotificationService {

	/**
	 * Persists a new Notification in the data source.
	 *
	 * @param notification
	 *            the notification
	 */
	@Secured("ROLE_USER")
	public void addNotification(Notification notification);

	/**
	 * Removes the Notification from the data source.
	 *
	 * @param notificationID
	 *            the notification ID
	 */
	@Secured("ROLE_USER")
	public void deleteNotification(Long notificationID);

	/**
	 * Optionally retrieves a Notification from the data source by Id if it exists.
	 *
	 * @param notificationID
	 *            the notification ID
	 * @return the notification by ID
	 */
	@Secured("ROLE_USER")
	public Notification getNotificationByID(Long notificationID);

	/**
	 * Retrieves all Notifications from the data source.
	 *
	 * @return the list
	 */
	@Secured("ROLE_USER")
	public List<Notification> listNotifications();

	/**
	 * Updates an existing Notification in the data source.
	 *
	 * @param notification
	 *            the notification
	 */
	@Secured("ROLE_USER")
	public void updateNotification(Notification notification);

}
