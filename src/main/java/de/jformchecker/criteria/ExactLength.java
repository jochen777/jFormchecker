package de.jformchecker.criteria;

import de.jformchecker.FormCheckerElement;

/**
 * Checks that the length of the value is equal to the given length.
 * 
 * Based on work of armandino (at) gmail.com
 */
public final class ExactLength extends AbstractCriterion {
	private int length;

	ExactLength(int length) {
		this.length = length;
	}

	@Override
	public ValidationResult validate(FormCheckerElement value) {
		boolean isValid = value.getValue().length() == length;
		if (!isValid) {
			ValidationResult.fail("The value must be %d characters long", Integer.valueOf(length));
		}
		return ValidationResult.ok();
	}

}
