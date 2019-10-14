package com.resourcemanager.util;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Set;

/**
 * Abstract Utils class to centralize where utility methods are kept.
 */
public class Utils {

	/**
	 * Gets the month names in between two dates.
	 *
	 * @param startDate
	 *            the start date
	 * @param endDate
	 *            the end date
	 * @return the month names inbetween dates
	 */
	public static Set<String> getMonthNamesInbetweenDates(LocalDate startDate, LocalDate endDate) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM yyyy", Locale.ENGLISH);
		Set<String> outputs = new LinkedHashSet<String>();
		YearMonth yearMonth = YearMonth.from(startDate);

		// Repeat while the current year-month is *not* after (is before or is equal) the ending year-month.
		while (!yearMonth.isAfter(YearMonth.from(endDate))) {
			// String output = yearMonth.toString (); // ISO 8601 format.
			String output = yearMonth.format(formatter);
			outputs.add(output);
			// Prepare for next loop.
			yearMonth = yearMonth.plusMonths(1);
		}
		return outputs;
	}

}
