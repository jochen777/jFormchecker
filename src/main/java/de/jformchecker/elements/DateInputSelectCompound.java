package de.jformchecker.elements;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

import de.jformchecker.FormCheckerElement;
import de.jformchecker.StringUtils;
import de.jformchecker.criteria.ValidationResult;
import de.jformchecker.request.Request;
import de.jformchecker.validator.Validator;

// DateInput Compound Element offering the date-inputs as select-boxes
public class DateInputSelectCompound extends AbstractInput<DateInputSelectCompound> implements FormCheckerElement {

	SelectInput day;
	SelectInput month;
	SelectInput year;

	public static DateInputSelectCompound build(String name) {
		DateInputSelectCompound i = new DateInputSelectCompound();
		
		i.day = SelectInput.build("day_" + name);
		i.day.setPossibleValues(buildDays());
		
		i.month = SelectInput.build("month_" + name);
		i.month.setPossibleValues(buildMonths());
		
		i.year = SelectInput.build("year_" + name);
		i.year.setPossibleValues(buildYears());
		
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
		if (firstRun) {
			this.setValue(this.getPreSetValue());
		} else {
			// TODO: set validators (
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

	// TODO: Make this translateable
	private static LinkedHashMap<String, String> buildDays() { 
		return buildMap(1, 31, "Tag");
	}

	private static LinkedHashMap<String, String> buildMonths() { 
		return buildMap(1, 12, "Monat");
	}

	private static LinkedHashMap<String, String> buildYears() { 
		return buildMap(2000, 2018, "Jahr");
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

}
