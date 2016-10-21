package de.jformchecker.criteria;

import de.jformchecker.FormCheckerElement;

/**
 * Checks that the value is less than or equal to the given {@link Comparable}.
 * 
 * Based on work of armandino (at) gmail.com
 */
public final class Max extends AbstractNumberComparingCriterion {
	private int max;

	Max(int max) {
		this.max = max;
	}

	@Override
	public ValidationResult validateNumberAndSetError(int input) {
		boolean isValid = input < max;
		if (!isValid) {
			return ValidationResult.fail("max", max);
		}
		return ValidationResult.ok();
	}

}
