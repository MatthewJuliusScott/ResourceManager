package com.resourcemanager.util;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Utils {

	public static List<String> getMonthNamesInbetweenDates(LocalDate startDate, LocalDate endDate) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM yyyy", Locale.ENGLISH);
		List<String> outputs = new ArrayList<>();
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
