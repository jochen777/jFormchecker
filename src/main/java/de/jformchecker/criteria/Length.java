package de.jformchecker.criteria;

import de.jformchecker.Criterion;
import de.jformchecker.FormCheckerElement;

/**
 * Checks that the length of the value is within the given range.
 * 
 * Based on work of armandino (at) gmail.com
 */
public final class Length implements Criterion {
	private int min;
	private int max;

	Length(int min, int max) {
		this.min = min;
		this.max = max;
	}

	@Override
	public ValidationResult validate(FormCheckerElement value) {
		boolean isValid = value.getValue().length() <= max && value.getValue().length() >= min;
		if (!isValid) {
			return ValidationResult.fail("length", Integer.valueOf(min),
					Integer.valueOf(max));
		}
		return ValidationResult.ok();

	}

}
