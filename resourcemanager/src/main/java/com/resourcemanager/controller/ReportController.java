/*
 *
 */

package com.resourcemanager.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.resourcemanager.model.Allocation;
import com.resourcemanager.model.Report;
import com.resourcemanager.model.Resource;
import com.resourcemanager.model.Skill;
import com.resourcemanager.service.AllocationService;
import com.resourcemanager.service.ProjectService;
import com.resourcemanager.service.ResourceService;
import com.resourcemanager.service.SkillService;
import com.resourcemanager.util.Utils;

/**
 * The Class ReportController.
 */
@Controller
public class ReportController {

	/** The allocation service. */
	@Autowired
	private AllocationService	allocationService;

	/** The resource service. */
	@Autowired
	private ResourceService		resourceService;

	/** The skill service. */
	@Autowired
	private SkillService		skillService;

	/** The project service. */
	@Autowired
	private ProjectService		projectService;

	/**
	 * List reports.
	 *
	 * @param model
	 *            the model
	 * @return the string
	 */
	@RequestMapping(value = { "/reports/{type}" }, method = RequestMethod.GET)
	public String listReports(Model model, @PathVariable("type") int type, HttpServletRequest request) {
		Report report = new Report();

		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/uuuu");
		LocalDate startDate;
		LocalDate endDate;
		try {
			startDate = LocalDate.parse(request.getParameter("startDate"), dateTimeFormatter);
			endDate = LocalDate.parse(request.getParameter("endDate"), dateTimeFormatter);
		} catch (Exception e) {
			startDate = LocalDate.now();
			endDate = LocalDate.now().plusDays(28);
		}

		if (type == Report.HOURS_PER_PROJECT_REQUIRED_PER_MONTH) {
			report.setName("Number of hours per project, per month");
			List<Allocation> allocations = allocationService.listAllocations();

			HashMap<String, HashMap<String, Integer>> tempData =
				new HashMap<String, HashMap<String, Integer>>();
			for (LocalDate date = startDate; date.isBefore(endDate); date = date.plusMonths(1)) {
				for (Allocation allocation : allocations) {

					String month = date.getMonth().name();
					String key = allocation.getProject().getName();
					if (tempData.get(key) == null) {
						tempData.put(key, new HashMap<String, Integer>());
					}
					Integer value =
						tempData.get(key) != null && tempData.get(key).get(month) != null ? tempData.get(key).get(month)
							: new Integer(0);

					if ((allocation.getStartDate().isAfter(date) || allocation.getStartDate().isEqual(date))
						&& (allocation.getEndDate().isBefore(date.plusMonths(1))
							|| allocation.getEndDate().isEqual(date.plusMonths(1)))) {
						value += allocation.getHours();
					}
					tempData.get(key).put(month, value);
				}
			}
			ArrayList<Entry<String, ArrayList<Integer>>> data = new ArrayList<Entry<String, ArrayList<Integer>>>();
			for (Entry<String, HashMap<String, Integer>> mapEntry : tempData.entrySet()) {
				SimpleEntry<String, ArrayList<Integer>> entry =
					new SimpleEntry<String, ArrayList<Integer>>(mapEntry.getKey(), new ArrayList<>(mapEntry.getValue().values()));
				data.add(entry);
			}
			report.setData(data);
			report.setLabels(Utils.getMonthNamesInbetweenDates(startDate, endDate));

		} else if (type == Report.HOURS_PER_SKILL_REQUIRED_PER_MONTH) {
			report.setName("Number of hours per skill, per month");
			List<Allocation> allocations = allocationService.listAllocations();

			HashMap<String, HashMap<String, Integer>> tempData = new HashMap<String, HashMap<String, Integer>>();
			for (LocalDate date = startDate; date.isBefore(endDate); date = date.plusMonths(1)) {
				for (Allocation allocation : allocations) {

					String month = date.getMonth().name();
					String key = allocation.getSkill().getName();
					if (tempData.get(key) == null) {
						tempData.put(key, new HashMap<String, Integer>());
					}
					Integer value =
						tempData.get(key) != null && tempData.get(key).get(month) != null ? tempData.get(key).get(month)
							: new Integer(0);

					if ((allocation.getStartDate().isAfter(date) || allocation.getStartDate().isEqual(date))
						&& (allocation.getEndDate().isBefore(date.plusMonths(1))
							|| allocation.getEndDate().isEqual(date.plusMonths(1)))) {
						value += allocation.getHours();
					}
					tempData.get(key).put(month, value);
				}
			}
			ArrayList<Entry<String, ArrayList<Integer>>> data = new ArrayList<Entry<String, ArrayList<Integer>>>();
			for (Entry<String, HashMap<String, Integer>> mapEntry : tempData.entrySet()) {
				SimpleEntry<String, ArrayList<Integer>> entry =
					new SimpleEntry<String, ArrayList<Integer>>(mapEntry.getKey(), new ArrayList<>(mapEntry.getValue().values()));
				data.add(entry);
			}
			report.setData(data);
			report.setLabels(Utils.getMonthNamesInbetweenDates(startDate, endDate));

		} else if (type == Report.POTENTIAL_HOURS_PER_SKILL_FROM_RESOURCES) {
			report.setName("Potential number of hours per skill from resources");
			List<Resource> resources = resourceService.listResources();
			HashMap<String, Integer> tempData = new HashMap<String, Integer>();

			for (Resource resource : resources) {
				Set<Skill> skills = resource.getSkills();
				for (Skill skill : skills) {
					String key = skill.getName();
					if (tempData.get(key) == null) {
						tempData.put(key, new Integer(0));
					}
					Integer value = tempData.get(key) != null ? tempData.get(key) : new Integer(0);
					value += resource.getHours();
					tempData.put(key, value);
				}
			}

			ArrayList<Entry<String, ArrayList<Integer>>> data = new ArrayList<Entry<String, ArrayList<Integer>>>();
			for (Entry<String, Integer> mapEntry : tempData.entrySet()) {
				SimpleEntry<String, ArrayList<Integer>> entry =
					new SimpleEntry<String, ArrayList<Integer>>(mapEntry.getKey(),
						new ArrayList<>(Arrays.asList(mapEntry.getValue())));
				data.add(entry);
			}
			report.setData(data);
			report.setLabels(tempData.keySet());

		}

		model.addAttribute("report", report);
		request.setAttribute("startDate", dateTimeFormatter.format(startDate));
		request.setAttribute("endDate", dateTimeFormatter.format(endDate));
		request.setAttribute("type", type);
		return "reports";
	}
}
