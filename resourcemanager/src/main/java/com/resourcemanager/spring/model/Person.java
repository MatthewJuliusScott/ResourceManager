
package com.resourcemanager.spring.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

/**
 * The Class Person.
 */
@Entity
@Table(name = "PERSON")
public class Person {

	/** The id. */
	@Id
	@Column(name = "person_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int		id;

	/** The first name. */
	private String	firstName;

	/** The last name. */
	private String	lastName;

	/** The skills. */
	@ManyToMany(cascade = { CascadeType.ALL })
	@OrderColumn(name = "order")
	@JoinTable(
        name = "person_skill", 
        joinColumns = { @JoinColumn(name = "person_id") }, 
        inverseJoinColumns = { @JoinColumn(name = "skill_id") }
    )
	private Skill[]	skills;

	/**
	 * Gets the last name.
	 *
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets the last name.
	 *
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Gets the first name.
	 *
	 * @return the first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets the first name.
	 *
	 * @param firstName the new first name
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "id=" + id + ", firstName=" + firstName + ", lastName="
		        + lastName;
	}

	/**
	 * Gets the skills.
	 *
	 * @return the skills
	 */
	public Skill[] getSkills() {
		return skills;
	}

	/**
	 * Sets the skills.
	 *
	 * @param skills the new skills
	 */
	public void setSkills(Skill[] skills) {
		this.skills = skills;
	}
}
