package de.jformchecker.elements;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;

import de.jformchecker.FormCheckerElement;
import de.jformchecker.StringUtils;
import de.jformchecker.criteria.ValidationResult;
import de.jformchecker.request.Request;
import de.jformchecker.validator.Validator;

// DateInput Compound Element
// Good example for elements that consists of several sub-elements
public class DateInputCompound extends AbstractInput<DateInputCompound> implements FormCheckerElement {

	TextInput day;
	TextInput month;
	TextInput year;

	public static DateInputCompound build(String name) {
		DateInputCompound i = new DateInputCompound();
		i.day = TextInput.build("day_" + name);
		i.month = TextInput.build("month_" + name);
		i.year = TextInput.build("year_" + name);
		i.name = name;
		return i;
	}

	Date internalDate = null;

	@Override
	public String getInputTag(Map<String, String> attributes) {
		return "<div>" + day.getInputTag(attributes) + " " + month.getInputTag(attributes) + " "
				+ year.getInputTag(attributes) + "</div>";

	}

	public DateInputCompound presetValue(Date t) {
		internalDate = t;
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(t);
		day.setPreSetValue("" + gc.get(Calendar.DAY_OF_MONTH));
		month.setPreSetValue("" + (gc.get(Calendar.MONTH) + 1));
		year.setPreSetValue("" + gc.get(Calendar.YEAR));
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

			// dayVal = request.getParameter(name + "_day_"+name);
			// yearVal = request.getParameter(name + "_year");
			// monthVal = request.getParameter(name + "_month");
			// if empty and not required, everything is fine
			if (!isRequired() && StringUtils.isEmpty(day.getValue()) && StringUtils.isEmpty(month.getValue())
					&& StringUtils.isEmpty(year.getValue())) {
				this.valid = true;
			} else { // check date format
				String dateVal = year.getValue() + "-" + month.getValue() + "-" + day.getValue();
				this.setValue(dateVal);

				SimpleDateFormat formater = new SimpleDateFormat("yy-MM-dd");
				try {
					formater.setLenient(false);
					internalDate = formater.parse(dateVal);
					this.valid = true;
					ValidationResult vr = validator.validate(this);
					this.setValidationResult(vr);
				} catch (ParseException e) {
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

	public Date getDateValue() {
		return internalDate;
	}

}
