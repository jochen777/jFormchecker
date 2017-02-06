package de.jformchecker.message;

import java.util.LinkedHashMap;

/**
 * Common selects. Like gender, months...
 * @author jochen
 *
 */
public class CommonSelects {
	
	MessageSource messageSource;
	
	public CommonSelects(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
	
	public static CommonSelects build(MessageSource messageSource) {
		return new CommonSelects(messageSource);
	}

	static String months [] = {"january", "february", "march", "april", "may", "june", "july"
			, "august", "september", "october", "november", "december"};

	
	public  LinkedHashMap<String, String> buildDays() { 
		return buildMap(1, 31, "jformchecker.select.day");
	}

	public  LinkedHashMap<String, String> buildMonths() { 
		LinkedHashMap<String, String> monthMap = buildMap(1, 12, "jformchecker.select.month");
		for (int i=1; i<=12; i++) {
			monthMap.put(Integer.toString(i), messageSource.getSafeMessage("jformchecker.select."+ months[i-1]));
		}
		return monthMap;
	}

	public LinkedHashMap<String, String> getYears(int startYear, int endYear) { 
		return buildMap(startYear, endYear, "jformchecker.select.year");
	}

	
	public LinkedHashMap<String, String> getGenderSelect() { 
		LinkedHashMap<String, String> genderSelect = new LinkedHashMap<>();
		genderSelect.put("f", messageSource.getSafeMessage("jformchecker.select.female"));
		genderSelect.put("m", messageSource.getSafeMessage("jformchecker.select.male"));
		return genderSelect;
	}
	
	
	private LinkedHashMap<String, String> buildMap(int start, int end, String first) {
		LinkedHashMap<String, String> dayMap = new LinkedHashMap<>();
		dayMap.put("", messageSource.getSafeMessage(first));
		for (int i = start; i <= end; i++) {
			dayMap.put(Integer.toString(i), Integer.toString(i));
		}
		return dayMap;
	}
	
}
