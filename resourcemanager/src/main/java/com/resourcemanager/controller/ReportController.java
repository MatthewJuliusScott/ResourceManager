/*
 *
 */

package com.resourcemanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
	@RequestMapping(value = { "/reports" }, method = RequestMethod.GET)
	public String listReports(Model model) {
		return "reports";
	}
}
