/*
 *
 */

package com.resourcemanager.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderColumn;
import javax.persistence.PreRemove;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalIdCache;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

/**
 * User data model. Represents an authority with some set of permissions and access levels.
 */
@Entity(name = "User")
@Table(name = "user")
@NaturalIdCache
@AttributeOverrides({
		@AttributeOverride(name = "id", column = @Column(name = "user_id")) })
public class User implements Cloneable {

	/** The id. */
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long				id;

	/** The name. */
	@Column
	private String				name;

	/** The email. */
	@Column
	private String				email;

	/** The password. */
	@Column
	private String				password;

	/** The authority strings. */
	@ElementCollection(fetch = FetchType.EAGER)
	private Set<String>			authorityStrings	= new HashSet<String>();

	/**
	 * The resource associated with this user. Usually only for a ROLE_USER authority.
	 */
	@OneToOne(fetch = FetchType.EAGER, optional = true)
	@JoinColumn(name = "resource_id", nullable = true)
	@OrderColumn(name = "order_col")
	private Resource			resource;

	/** The notifications. */
	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	@OrderColumn(name = "order_col")
	private List<Notification>	notifications;

	/**
	 * Adds the notification.
	 *
	 * @param notification
	 *            the notification
	 */
	public void addNotification(Notification notification) {
		if (notifications == null) {
			notifications = new ArrayList<Notification>();
		}
		notifications.add(notification);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	@Override
	public Object clone() throws CloneNotSupportedException {
		User clone = (User) super.clone();
		return clone;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		User other = (User) obj;
		if (email == null) {
			if (other.email != null) {
				return false;
			}
		} else if (!email.equals(other.email)) {
			return false;
		}
		if (id != other.id) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (password == null) {
			if (other.password != null) {
				return false;
			}
		} else if (!password.equals(other.password)) {
			return false;
		}
		return true;
	}

	/**
	 * Gets the granted authorities for this user.
	 *
	 * @return the authorities
	 */
	public List<GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for (String authority : authorityStrings) {
			authorities.add(new SimpleGrantedAuthority(authority));
		}
		return authorities;
	}

	/**
	 * Gets the authority strings.
	 *
	 * @return the authority strings
	 */
	public Set<String> getAuthorityStrings() {
		return authorityStrings;
	}

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the notifications.
	 *
	 * @return the notifications
	 */
	public List<Notification> getNotifications() {
		return notifications;
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Gets the resource.
	 *
	 * @return the resource
	 */
	public Resource getResource() {
		return resource;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
			+ ((password == null) ? 0 : password.hashCode());
		return result;
	}

	/**
	 * Pre remove.
	 */
	@PreRemove
	public void preRemove() {
		if (resource != null) {
			resource.setUser(null);
		}
	}

	/**
	 * Sets the authority strings.
	 *
	 * @param authorityStrings
	 *            the new authority strings
	 */
	public void setAuthorityStrings(Set<String> authorityStrings) {
		this.authorityStrings = authorityStrings;
	}

	/**
	 * Sets the email.
	 *
	 * @param email
	 *            the new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Sets the id.
	 *
	 * @param id
	 *            the new id
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Sets the name.
	 *
	 * @param name
	 *            the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Sets the notifications.
	 *
	 * @param notifications
	 *            the new notifications
	 */
	public void setNotifications(List<Notification> notifications) {
		this.notifications = notifications;
	}

	/**
	 * Sets the password.
	 *
	 * @param password
	 *            the new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Sets the resource.
	 *
	 * @param resource
	 *            the resource to set
	 */
	public void setResource(Resource resource) {
		this.resource = resource;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", authorityStrings="
			+ authorityStrings + ", resource=" + (resource != null ? String.valueOf(resource.getId())
				: "null")
			+ ", notifications=" + notifications + "]";
	}
}
