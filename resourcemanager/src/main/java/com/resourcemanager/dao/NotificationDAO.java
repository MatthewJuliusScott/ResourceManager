/*
 *
 */
package com.resourcemanager.dao;

import java.util.List;

import com.resourcemanager.model.Notification;

/**
 * Notification Data Access Object provides an abstract interface to some type of database or other persistence mechanism. It maps
 * application calls to the persistence layer, the DAO provides some specific data operations without exposing details of the
 * database. This isolation supports the single responsibility principle. It separates what data access the application needs, in
 * terms of domain-specific objects and data types (the public interface of the DAO), from how these needs can be satisfied with a
 * specific DBMS, database schema, etc. (the implementation of the DAO).
 */
public interface NotificationDAO {

	/**
	 * Persists a new Notification in the data source.
	 *
	 * @param notification
	 *            the notification
	 */
	void addNotification(Notification notification);

	/**
	 * Removes the Notification from the data source.
	 *
	 * @param notificationID
	 *            the notification ID
	 */
	void deleteNotification(Long notificationID);

	/**
	 * Optionally retrieves a Notification from the data source by Id if it exists.
	 *
	 * @param id
	 *            the id
	 * @return the notification
	 */
	Notification findNotificationByID(long id);

	/**
	 * Retrieves all Notifications from the data source.
	 *
	 * @return the list
	 */
	List<Notification> listNotifications();

	/**
	 * Updates an existing Notification in the data source.
	 *
	 * @param notification
	 *            the notification
	 */
	void updateNotification(Notification notification);

}
