package de.jformchecker.criteria;

/**
 * Checks that value is greater than or equal to the given {@link Comparable}.
 * 
 * Based on work of armandino (at) gmail.com
 */
public final class Min extends AbstractNumberComparingCriterion {
	private int min;

	Min(int min) {
		this.min = min;
	}

	@Override
	public ValidationResult validateNumberAndSetError(int input) {
		boolean isValid = input > min;
		if (!isValid) {
			return ValidationResult.fail("min", min);
		}
		return ValidationResult.ok();
	}

}
