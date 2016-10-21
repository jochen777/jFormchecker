package de.jformchecker.criteria;

import de.jformchecker.Criterion;
import de.jformchecker.FormCheckerElement;

/**
 * Checks that value is not less than the specified minimum.
 * 
 * Based on work of armandino (at) gmail.com
 */
public final class MinLength implements Criterion {
	private int minLength;

	MinLength(int minLength) {
		this.minLength = minLength;
	}

	@Override
	public ValidationResult validate(FormCheckerElement value) {
		boolean isValid = value.getValue().length() >= minLength;
		if (!isValid) {
			return ValidationResult.fail("min_len", minLength);
		}
		return ValidationResult.ok();
	}

}
