/*
 *
 */

package com.resourcemanager.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
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
import com.resourcemanager.service.AllocationService;
import com.resourcemanager.util.Utils;

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

		List<Allocation> allocations = allocationService.listAllocations();

		if (type == Report.HOURS_PER_PROJECT) {
			report.setName("# of hours per project");
			for (LocalDate date = startDate; date.isBefore(endDate); date = date.plusMonths(1)) {
				HashMap<String, Integer> tempMap = new HashMap<String, Integer>();
				for (Allocation allocation : allocations) {
					if ((allocation.getStartDate().isAfter(startDate) || allocation.getStartDate().isEqual(startDate))
						&& (allocation.getEndDate().isBefore(endDate) || allocation.getEndDate().isEqual(endDate))) {
						String key = allocation.getProject().getName();
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
				report.addData(data);
			}
		} else if (type == Report.HOURS_PER_SKILL) {
			report.setName("# of hours per skill");
			for (LocalDate date = startDate; date.isBefore(endDate); date = date.plusMonths(1)) {
				HashMap<String, Integer> tempMap = new HashMap<String, Integer>();
				for (Allocation allocation : allocations) {
					if ((allocation.getStartDate().isAfter(startDate) || allocation.getStartDate().isEqual(startDate))
						&& (allocation.getEndDate().isBefore(endDate) || allocation.getEndDate().isEqual(endDate))) {
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
				report.addData(data);
			}
		}

		report.setDataLabels(Utils.getMonthNamesInbetweenDates(startDate, endDate));
		model.addAttribute("report", report);
		request.setAttribute("startDate", dateTimeFormatter.format(startDate));
		request.setAttribute("endDate", dateTimeFormatter.format(endDate));
		request.setAttribute("type", type);
		return "reports";
	}
}
