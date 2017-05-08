package de.jformchecker.elements;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;

import de.jformchecker.FormCheckerElement;
import de.jformchecker.StringUtils;
import de.jformchecker.TagAttributes;
import de.jformchecker.criteria.ValidationResult;
import de.jformchecker.message.CommonSelects;
import de.jformchecker.message.MessageSource;
import de.jformchecker.message.MinimalMessageSource;
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
	
	@Deprecated
	public static DateInputSelectCompound build(String name, int yearStart, int yearEnd) {
		return DateInputSelectCompound.build(name, yearStart, yearEnd, new MinimalMessageSource());
	}

	public static DateInputSelectCompound build(String name, int yearStart, int yearEnd, MessageSource messageSource) {
		DateInputSelectCompound i = new DateInputSelectCompound();
		i.yearStart = yearStart;
		i.yearEnd = yearEnd;
		i.name = name;
		i.createSelectInputs(messageSource);
		return i;
	}

	
	@Deprecated
	public static DateInputSelectCompound build(String name, YearRange yearRange) {
		return DateInputSelectCompound.build(name, yearRange.start, yearRange.end);
	}

	
	public static DateInputSelectCompound build(String name, YearRange yearRange, MessageSource messageSource) {
		return DateInputSelectCompound.build(name, yearRange.start, yearRange.end, messageSource);
	}

	
	LocalDate internalDate = null;

	@Override
	public String getInputTag(Map<String, String> attributes) {
		TagAttributes tagAttributes = new TagAttributes(attributes);
		tagAttributes.add(this.inputAttributes);

		return  day.getInputTag(tagAttributes.getAttributes()) +  month.getInputTag(tagAttributes.getAttributes()) 
				+ year.getInputTag(tagAttributes.getAttributes());

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

	private void createSelectInputs(MessageSource messageSource) {
		CommonSelects commonSelects = new CommonSelects(messageSource);
		day = SelectInput.build("day-" + name);
		day.setPossibleValues(commonSelects.buildDays());
		
		month = SelectInput.build("month-" + name);
		month.setPossibleValues(commonSelects.buildMonths());
		
		year = SelectInput.build("year-" + name);
		// iterate from large to small for years
		if (yearStart < yearEnd) {
			int temp = yearStart;
			yearStart = yearEnd;
			yearEnd = temp;
		}
		year.setPossibleValues(commonSelects.getYears(this.yearStart, this.yearEnd));
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
		if (internalDate == null) {
			return null;
		}
		return Date.from(internalDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}


	/**
	 * a range of years - that can be shown in the year-dropdown
	 * Use the static convenience methods
	 * 
	 * @author jochen
	 *
	 */
	public static class YearRange {
		int start;
		int end;
		
		
		/**
		 * A range of year. 
		 * @param start The smaller value!
		 * @param end The bigger value!
		 */
		public YearRange(int start, int end) {
			this.start = start;
			this.end = end;
		}
		
		/**
		 * year around now. 
		 * Example: AroundNow(2) will return for the currentYear=2017 : 2015,2016,2017,2018,2019
		 * @param offset
		 * @return
		 */
		public static YearRange aroundNow(int offset) {
			return new YearRange(LocalDate.now().getYear()-offset, LocalDate.now().getYear()+offset);
		}
		
		/**
		 * Years in the past from today
		 * 
		 * @param numberOfYears
		 * @return
		 */
		public static YearRange past(int numberOfYears) {
			return new YearRange(LocalDate.now().getYear()-numberOfYears, LocalDate.now().getYear());
		}


		/**
		 * Just the current year
		 * @return
		 */
		public static YearRange currentYear() {
			return new YearRange(LocalDate.now().getYear(), LocalDate.now().getYear());
		}

		
		/**
		 * Years from now in the future.
		 * @param numberOfYears
		 * @return
		 */
		public static YearRange future(int numberOfYears) {
			return new YearRange(LocalDate.now().getYear(), 
					LocalDate.now().getYear()+numberOfYears);
		}

		/**
		 * typical Birthday - : 110years in the past - today
		 * @return
		 */
		public static YearRange birthday() {
			return new YearRange(LocalDate.now().getYear()-110, LocalDate.now().getYear());
		}

	}
	
	@Override
	public String getType() {
		return "date-select";
	}

	
}
