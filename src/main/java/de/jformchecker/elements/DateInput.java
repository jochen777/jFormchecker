package de.jformchecker.elements;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;

import com.coverity.security.Escape;

import de.jformchecker.AttributeUtils;
import de.jformchecker.FormCheckerElement;
import de.jformchecker.StringUtils;
import de.jformchecker.criteria.ValidationResult;
import de.jformchecker.request.Request;
import de.jformchecker.validator.Validator;

public class DateInput extends AbstractInput<DateInput> implements FormCheckerElement {

	public static DateInput build(String name) {
		DateInput i = new DateInput();
		i.name = name;
		return i;
	}

	public static final int MONTH = 1;
	public static final int DAY = 2;
	public static final int YEAR = 3;

	String dayVal = "";
	String monthVal = "";
	String yearVal = "";

	Date internalDate = null;

	@Override
	public String getInputTag(Map<String, String> attributes) {
		return "<div>" + getDatePartTag(DateInput.DAY, attributes) + " " + getDatePartTag(DateInput.MONTH, attributes)
				+ " " + getDatePartTag(DateInput.YEAR, attributes) + "</div>";

	}

	public DateInput presetValue(Date t) {
		internalDate = t;
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(t);
		yearVal = "" + gc.get(Calendar.YEAR);
		dayVal = "" + gc.get(Calendar.DAY_OF_MONTH);
		monthVal = "" + (gc.get(Calendar.MONTH) + 1);
		return this;
	}

	public String getDatePartTag(int field, Map<String, String> attributes) {
		String inputField = "wrong field desc!";
		switch (field) {
		case DAY:
			inputField = "<input type=\"text\" " + AttributeUtils.buildAttributes(attributes) + getTabIndexTag() + " id=\""
					+ name + "_day\" name=\"" + name + "_day\" size=\"2\" value=\""
					+ (Escape.htmlText(dayVal)) + "\" maxlength=\"2\" placeholder=\"day\">";

			break;
		case MONTH:
			inputField = "<input " + getElementId() + AttributeUtils.buildAttributes(attributes) + getTabIndexTagIncreaseBy(1)
					+ " type=\"text\" id=\"" + name + "_month\" size=\"2\" name=\"" + name + "_month\"  value=\""
					+ (Escape.htmlText(monthVal)) + "\" maxlength=\"2\" placeholder=\"month\">";
			break;
		case YEAR:
			inputField = "<input type=\"text\" " + AttributeUtils.buildAttributes(attributes) + getTabIndexTagIncreaseBy(2)
					+ " id=\"" + name + "_year\" name=\"" + name + "_year\" size=\"4\" value=\""
					+ (Escape.htmlText(yearVal)) + "\" maxlength=\"4\" placeholder=\"year\">";
			break;
		}
		return inputField;
	}

	@Override
	public void init(Request request, boolean firstRun, Validator validator) {
		if (firstRun) {
			this.setValue(this.getPreSetValue());
		} else {
			dayVal = request.getParameter(name + "_day");
			yearVal = request.getParameter(name + "_year");
			monthVal = request.getParameter(name + "_month");
			// if empty and not required, everything is fine
			if (!isRequired() && StringUtils.isEmpty(dayVal) && StringUtils.isEmpty(yearVal)
					&& StringUtils.isEmpty(monthVal)) {
				this.valid = true;
			} else { // check date format
				String dateVal = yearVal + "-" + monthVal + "-" + dayVal;
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
					this.setValidationResult(ValidationResult.fail("Wrong date format"));

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
