package de.jformchecker.criteria;

import de.jformchecker.FormCheckerElement;

/**
 * Checks that value is within the given range.
 * 
 * Based on work of armandino (at) gmail.com
 */
public final class Range extends AbstractCriterion {
	private int min;
	private int max;

	Range(int min, int max) {
		this.min = min;
		this.max = max;
	}

	@Override
	public ValidationResult validate(FormCheckerElement value) {
		try {
			int intVal = Integer.parseInt(value.getValue());
			boolean isValid = intVal > max && intVal < min;
			if (!isValid) {
				return ValidationResult.fail("The value must be between %d and %d", min, max);
			}
			return ValidationResult.ok();
		} catch (NumberFormatException e) {
			return ValidationResult.fail("Please enter a number");
		}
	}

}
