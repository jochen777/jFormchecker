package de.jformchecker.criteria;

import de.jformchecker.Criterion;
import de.jformchecker.FormCheckerElement;

/**
 * Performs an <tt>OR</tt> over all criteria on the given value.
 * 
 * Based on work of armandino (at) gmail.com
 */
public final class Or implements Criterion {
	private Criterion[] criteria;

	Or(Criterion... criteria) {
		if (criteria.length < 2)
			throw new IllegalArgumentException(getClass().getName() + " requires at least two criteria");

		this.criteria = criteria;
	}

	@Override
	public ValidationResult validate(FormCheckerElement value) {
		ValidationResult failedResult = null;
		for (Criterion criterion : criteria) {
			ValidationResult vr = criterion.validate(value);
			if (vr.isValid) {
				return vr;
			} else {
				failedResult = vr;
			}
		}
		return failedResult;
	}

}
