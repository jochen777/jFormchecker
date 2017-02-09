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
import de.jformchecker.message.CommonSelects;
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

	// please use setPresetValue
	@Deprecated
	public DateInputSelectCompound presetValue(LocalDate t) {
		internalDate = t;
		day.setPreSetValue(Integer.toString(t.getDayOfMonth()));
		month.setPreSetValue(Integer.toString(t.getMonthValue()));
		year.setPreSetValue(Integer.toString(t.getYear()));
		return this;
	}
	
	public DateInputSelectCompound setPresetValue(LocalDate t) {
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
		CommonSelects commonSelects = new CommonSelects(parent.getConfig().getProperties());
		day = SelectInput.build("day_" + name);
		day.setPossibleValues(commonSelects.buildDays());
		
		month = SelectInput.build("month_" + name);
		month.setPossibleValues(commonSelects.buildMonths());
		
		year = SelectInput.build("year_" + name);
		year.setPossibleValues(commonSelects.getYears(this.yearStart, this.yearEnd));

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
