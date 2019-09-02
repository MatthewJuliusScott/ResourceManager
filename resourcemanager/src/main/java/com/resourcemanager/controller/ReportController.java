/*
 *
 */

package com.resourcemanager.controller;

import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.resourcemanager.model.Report;

/**
 * The Class ReportController.
 */
@Controller
public class ReportController {

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

		if (type == Report.HOURS_PER_MONTH) {
			report.setName("# of hours per month");
		} else if (type == Report.HOURS_PER_PROJECT) {
			report.setName("# of hours per project");
		} else if (type == Report.HOURS_PER_SKILL) {
			report.setName("# of hours per skill");
		}

		Set<String> labels = new LinkedHashSet<String>();
		Set<String> data = new LinkedHashSet<String>();
		labels.add("January");
		labels.add("February");
		labels.add("March");
		labels.add("April");
		labels.add("May");
		labels.add("June");
		labels.add("July");
		labels.add("August");
		labels.add("September");
		labels.add("October");
		labels.add("November");
		labels.add("December");
		data.add(String.valueOf(new Random().nextInt(100)));
		data.add(String.valueOf(new Random().nextInt(100)));
		data.add(String.valueOf(new Random().nextInt(100)));
		data.add(String.valueOf(new Random().nextInt(100)));
		data.add(String.valueOf(new Random().nextInt(100)));
		data.add(String.valueOf(new Random().nextInt(100)));
		data.add(String.valueOf(new Random().nextInt(100)));
		data.add(String.valueOf(new Random().nextInt(100)));
		data.add(String.valueOf(new Random().nextInt(100)));
		data.add(String.valueOf(new Random().nextInt(100)));
		data.add(String.valueOf(new Random().nextInt(100)));
		data.add(String.valueOf(new Random().nextInt(100)));
		report.setLabels(labels);
		report.setData(data);
		model.addAttribute("report", report);
		return "reports";
	}
}
