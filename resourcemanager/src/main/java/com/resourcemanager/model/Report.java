package com.resourcemanager.model;

import java.nio.ByteBuffer;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.FetchType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * The Class Report.
 */
public class Report {

	public final static int	HOURS_PER_SKILL		= 1;

	public final static int	HOURS_PER_PROJECT	= 2;

	/** The labels. */
	@ElementCollection(fetch = FetchType.EAGER)
	private Set<String>		labels				= new LinkedHashSet<String>();

	/** The data. */
	@ElementCollection(fetch = FetchType.EAGER)
	private List<String>	data				= new LinkedList<String>();

	/** The name. */
	private String			name				= "";

	/**
	 * Labels JSON.
	 *
	 * @return the string
	 * @throws JsonProcessingException
	 *             the json processing exception
	 */
	@JsonIgnore
	public String getAsJSON() throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(this);
	}

	public String[] getBorderColours() {
		String[] colours = new String[labels.size()];
		int i = 0;
		for (String label : labels) {
			byte[] bytes = ByteBuffer.allocate(4).putInt(label.hashCode()).array();
			colours[i++] = "rgba(" + String.valueOf(bytes[0] & 0xFF) + "," + String.valueOf(bytes[1] & 0xFF) + ","
				+ String.valueOf(bytes[2] & 0xFF)
				+ "," + "1" + ")";
		}
		return colours;
	}

	public String[] getColours() {
		String[] colours = new String[labels.size()];
		int i = 0;
		for (String label : labels) {
			byte[] bytes = ByteBuffer.allocate(4).putInt(label.hashCode()).array();
			colours[i++] = "rgba(" + String.valueOf(bytes[0] & 0xFF) + "," + String.valueOf(bytes[1] & 0xFF) + ","
				+ String.valueOf(bytes[2] & 0xFF)
				+ "," + "0.3" + ")";
		}
		return colours;
	}

	/**
	 * Gets the data.
	 *
	 * @return the data
	 */
	public List<String> getData() {
		return data;
	}

	/**
	 * Gets the labels.
	 *
	 * @return the labels
	 */
	public Set<String> getLabels() {
		return labels;
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
	 * Sets the data.
	 *
	 * @param data
	 *            the new data
	 */
	public void setData(List<String> data) {
		this.data = data;
	}

	/**
	 * Sets the labels.
	 *
	 * @param labels
	 *            the new labels
	 */
	public void setLabels(Set<String> labels) {
		this.labels = labels;
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
}
