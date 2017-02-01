package de.jformchecker.elements;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import de.jformchecker.FormCheckerElement;
import de.jformchecker.StringUtils;
import de.jformchecker.criteria.ValidationResult;
import de.jformchecker.message.MessageSource;
import de.jformchecker.request.Request;
import de.jformchecker.validator.Validator;

// DateInput Compound Element offering the date-inputs as select-boxes
// TODO: allow individual order (for internationalisation)
public class DateInputSelectCompound extends AbstractInput<DateInputSelectCompound> implements FormCheckerElement {

	SelectInput day;
	SelectInput month;
	SelectInput year;

	static String months [] = {"january", "february", "march", "april", "may", "june", "july"
			, "august", "september", "october", "november", "december"};
	
	int yearStart;
	int yearEnd;
	
	public static DateInputSelectCompound build(String name, int yearStart, int yearEnd) {
		DateInputSelectCompound i = new DateInputSelectCompound();
		i.yearStart = yearStart;
		i.yearEnd = yearEnd;
		i.name = name;
		return i;
	}

	LocalDate internalDate = null;

	@Override
	public String getInputTag(Map<String, String> attributes) {
		return  day.getInputTag(attributes) +  month.getInputTag(attributes) 
				+ year.getInputTag(attributes);

	}

	public DateInputSelectCompound presetValue(LocalDate t) {
		internalDate = t;
		day.setPreSetValue(Integer.toString(t.getDayOfMonth()));
		month.setPreSetValue(Integer.toString(t.getMonthValue()));
		year.setPreSetValue(Integer.toString(t.getYear()));
		return this;
	}

	public static final int MONTH = 1;
	public static final int DAY = 2;
	public static final int YEAR = 3;

	public String getDatePartTag(int field, Map<String, String> attributes) {
		String inputField = "wrong field desc!";
		switch (field) {
		case DAY:
			inputField = day.getInputTag(attributes);
			break;
		case MONTH:
			inputField = month.getInputTag(attributes);
			break;
		case YEAR:
			inputField = year.getInputTag(attributes);
			break;
		}
		return inputField;
	}

	@Override
	public void init(Request request, boolean firstRun, Validator validator) {
		day = SelectInput.build("day_" + name);
		MessageSource translates = parent.getConfig().getProperties();
		day.setPossibleValues(buildDays(translates.getMessage("formchecker.jformchecker.select.day")));
		
		month = SelectInput.build("month_" + name);
		month.setPossibleValues(buildMonths(translates.getMessage("formchecker.jformchecker.select.month"), translates));
		
		year = SelectInput.build("year_" + name);
		year.setPossibleValues(buildYears(translates.getMessage("formchecker.jformchecker.select.year")));

		if (firstRun) {
			this.setValue(this.getPreSetValue());
		} else {
			day.init(request, firstRun, validator);
			month.init(request, firstRun, validator);
			year.init(request, firstRun, validator);

			// if empty and not required, everything is fine
			if (!isRequired() && StringUtils.isEmpty(day.getValue()) && StringUtils.isEmpty(month.getValue())
					&& StringUtils.isEmpty(year.getValue())) {
				this.valid = true;
			} else { // check date format
				String dateVal = year.getValue() + "-" + month.getValue() + "-" + day.getValue();
				this.setValue(dateVal);

				try {
					internalDate = LocalDate.of(Integer.parseInt(year.getValue()),Integer.parseInt( month.getValue()), 
							Integer.parseInt(day.getValue())); 
					this.valid = true;
					ValidationResult vr = validator.validate(this);
					this.setValidationResult(vr);
				} catch (DateTimeException | NumberFormatException e) {
					this.valid = false;
					this.setValidationResult(ValidationResult.fail("jformchecker.wrong_date_format"));

				}
			}
		}
	}

	private  LinkedHashMap<String, String> buildDays(String dayDesc) { 
		return buildMap(1, 31, dayDesc);
	}

	private  LinkedHashMap<String, String> buildMonths(String monthDesc, MessageSource translates) { 
		LinkedHashMap<String, String> monthMap = buildMap(1, 12, monthDesc);
		for (int i=1; i<=12; i++) {
			monthMap.put(Integer.toString(i), translates.getMessage("formchecker.jformchecker.select."+ months[i-1]));
		}
		return monthMap;
	}

	private  LinkedHashMap<String, String> buildYears(String yearDesc) { 
		return buildMap(this.yearStart, this.yearEnd, yearDesc);
	}

	
	private static LinkedHashMap<String, String> buildMap(int start, int end, String first) {
		LinkedHashMap<String, String> dayMap = new LinkedHashMap<>();
		dayMap.put("", first);
		for (int i = start; i <= end; i++) {
			dayMap.put(Integer.toString(i), Integer.toString(i));
		}
		return dayMap;
	}

	@Override
	public int getLastTabIndex() {
		return this.getTabIndex() + 2;
	}

	public String getValue() {
		return value;
	}

	public LocalDate getDateValue() {
		return internalDate;
	}
	
	/**
	 * Get internal Date as Date (Please avoid this, because LocalDate is far better.)
	 * @return
	 */
	public Date getLegacyDateValue() {
		return Date.from(internalDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}


}
