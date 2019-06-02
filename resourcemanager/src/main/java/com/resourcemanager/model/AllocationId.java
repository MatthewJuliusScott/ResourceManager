package com.resourcemanager.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The Class AllocationId.
 */
@Embeddable
public class AllocationId implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long	serialVersionUID	= 736845938224283625L;

	/** The project id. */
	@Column(name = "project_id")
	private Long				projectId;

	/** The skill id. */
	@Column(name = "skill_id")
	private Long				skillId;

	/**
	 * Instantiates a new allocation id.
	 */
	public AllocationId() {
	}

	/**
	 * Instantiates a new allocation id.
	 *
	 * @param projectId the project id
	 * @param skillId  the skill id
	 */
	public AllocationId(Long projectId, Long skillId) {
		this.projectId = projectId;
		this.skillId = skillId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;

		if (o == null || getClass() != o.getClass())
			return false;

		AllocationId that = (AllocationId) o;
		return Objects.equals(projectId, that.projectId)
		        && Objects.equals(skillId, that.skillId);
	}

	/**
	 * Gets the project id.
	 *
	 * @return the projectId
	 */
	public Long getProjectId() {
		return projectId;
	}

	/**
	 * Gets the skill id.
	 *
	 * @return the skillId
	 */
	public Long getSkillId() {
		return skillId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return Objects.hash(projectId, skillId);
	}

	/**
	 * Sets the project id.
	 *
	 * @param projectId the projectId to set
	 */
	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	/**
	 * Sets the skill id.
	 *
	 * @param skillId the skillId to set
	 */
	public void setSkillId(Long skillId) {
		this.skillId = skillId;
	}
}