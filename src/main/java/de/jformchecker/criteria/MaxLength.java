package de.jformchecker.criteria;

import de.jformchecker.Criterion;
import de.jformchecker.FormCheckerElement;

/**
 * Checks that value is not greater than the specified maximum.
 * 
 * Based on work of armandino (at) gmail.com
 */
public final class MaxLength implements Criterion {
	private int maxLength;

	MaxLength(int maxLength) {
		this.maxLength = maxLength;
	}

	public int getMaxLength() {
		return maxLength;
	}

	@Override
	public ValidationResult validate(FormCheckerElement value) {
		boolean isValid = value.getValue().length() <= maxLength;
		if (!isValid) {
			return ValidationResult.fail("The value must not be greater than %d characters long", maxLength);
		}
		return ValidationResult.ok();
	}

}
