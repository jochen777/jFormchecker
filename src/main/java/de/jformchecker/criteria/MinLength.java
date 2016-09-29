package de.jformchecker.criteria;

import de.jformchecker.FormCheckerElement;

/**
 * Checks that value is not less than the specified minimum.
 * 
 * Based on work of armandino (at) gmail.com
 */
public final class MinLength extends AbstractCriterion {
	private int minLength;

	MinLength(int minLength) {
		this.minLength = minLength;
	}

	@Override
	public ValidationResult validate(FormCheckerElement value) {
		boolean isValid = value.getValue().length() >= minLength;
		if (!isValid) {
			return ValidationResult.fail("The value must not be less than %d characters long", minLength);
		}
		return ValidationResult.ok();
	}

}
