package de.jformchecker.criteria;

import de.jformchecker.Criterion;
import de.jformchecker.FormCheckerElement;

/**
 * Checks that value is a number.
 * 
 * Based on work of armandino (at) gmail.com
 */
public final class Number implements Criterion {

	Number() {
	}

	@Override
	public ValidationResult validate(FormCheckerElement value) {
		try {
			Integer.parseInt(value.getValue());
			return ValidationResult.ok();
		} catch (NumberFormatException e) {
			return ValidationResult.fail("not_a_number");
		}
	}

}
