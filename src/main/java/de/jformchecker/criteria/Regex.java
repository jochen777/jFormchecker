package de.jformchecker.criteria;

import java.util.regex.Pattern;

import de.jformchecker.Criterion;
import de.jformchecker.FormCheckerElement;

/**
 * Checks if a string matches a regular expression.
 * 
 * Based on work of armandino (at) gmail.com
 */
public class Regex implements Criterion {
	private Pattern pattern;
	private String errorMsg = "The value must match the required format";

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	Regex(String pattern) {
		this.pattern = Pattern.compile(pattern);
	}

	@Override
	public ValidationResult validate(FormCheckerElement value) {
		boolean isValid = pattern.matcher(value.getValue()).find();
		if (!isValid) {
			ValidationResult.fail(errorMsg);
		}
		return ValidationResult.ok();
	}

}
