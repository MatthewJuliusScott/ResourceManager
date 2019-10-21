package com.resourcemanager.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Report data model. abstract set of data and labels to displayed graphically.
 */
public class Report {

	/** The Constant HOURS_PER_SKILL. */
	public final static int									HOURS_PER_SKILL		= 1;

	/** The Constant HOURS_PER_PROJECT. */
	public final static int									HOURS_PER_PROJECT	= 2;

	/** The labels. */
	private Set<String>										labels				= new HashSet<String>();

	/** The data. */
	private ArrayList<Entry<String, ArrayList<Integer>>>	data				=
		new ArrayList<Entry<String, ArrayList<Integer>>>();

	/** The name. */
	private String											name				= "";

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

	/**
	 * Gets the border colours.
	 *
	 * @return the border colours
	 */
	public String[] getBorderColours() {
		String[] colours = new String[data.size()];
		for (int i = 0; i < data.size(); i++) {
			colours[i] = "hsla(" + (i * (255 / data.size())) % 255 + "," + 60 + "%," + 60 + "%," + 1.0 + ")";
		}
		return colours;
	}

	/**
	 * Gets the colours.
	 *
	 * @return the colours
	 */
	public String[] getColours() {
		String[] colours = new String[data.size()];
		for (int i = 0; i < data.size(); i++) {
			colours[i] = "hsla(" + (i * (255 / data.size())) % 255 + "," + 60 + "%," + 60 + "%," + 0.3 + ")";
		}
		return colours;
	}

	/**
	 * Gets the data.
	 *
	 * @return the data
	 */
	public ArrayList<Entry<String, ArrayList<Integer>>> getData() {
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
	public void setData(ArrayList<Entry<String, ArrayList<Integer>>> data) {
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
