/*
 *
 */

package com.resourcemanager.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.resourcemanager.model.Allocation;
import com.resourcemanager.model.Report;
import com.resourcemanager.service.AllocationService;

/**
 * The Class ReportController.
 */
@Controller
public class ReportController {

	/** The allocation service. */
	@Autowired
	private AllocationService allocationService;

	/**
	 * List reports.
	 *
	 * @param model
	 *            the model
	 * @return the string
	 */
	@RequestMapping(value = { "/reports/{type}" }, method = RequestMethod.GET)
	public String listReports(Model model, @PathVariable("type") int type) {
		Report report = new Report();

		HashMap<String, Integer> tempMap = new HashMap<String, Integer>();
		List<Allocation> allocations = allocationService.listAllocations();

		if (type == Report.HOURS_PER_PROJECT) {
			report.setName("# of hours per project");
			for (Allocation allocation : allocations) {
				String key = allocation.getProject().getName();
				Integer value =
					tempMap.get(key) != null ? tempMap.get(key)
						: new Integer(0);
				value += allocation.getHours();
				tempMap.put(key, value);
			}
		} else if (type == Report.HOURS_PER_SKILL) {
			report.setName("# of hours per skill");
			for (Allocation allocation : allocations) {
				String key = allocation.getSkill().getName();
				Integer value =
					tempMap.get(key) != null ? tempMap.get(key)
						: new Integer(0);
				value += allocation.getHours();
				tempMap.put(key, value);
			}
		}

		Set<String> labels = new LinkedHashSet<String>();
		labels.addAll(tempMap.keySet());

		/* Specify the size of the list up front to prevent resizing. */
		List<String> data = new ArrayList<String>(tempMap.size());
		for (Integer myInt : tempMap.values()) {
			data.add(String.valueOf(myInt));
		}

		report.setLabels(labels);
		report.setData(data);
		model.addAttribute("report", report);
		return "reports";
	}
}
