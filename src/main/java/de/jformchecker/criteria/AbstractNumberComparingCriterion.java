package de.jformchecker.criteria;

import de.jformchecker.Criterion;
import de.jformchecker.FormCheckerElement;

/**
 * provides some utils for criterias, that compare numbers (currentyl: min/max)
 * 
 * @author jpier
 *
 */
public abstract class AbstractNumberComparingCriterion implements Criterion {
	@Override
	public ValidationResult validate(FormCheckerElement value) {
		try {
			int input = Integer.parseInt(value.getValue());
			return validateNumberAndSetError(input);
		} catch (NumberFormatException e) {
			return ValidationResult.fail("not_a_number");
		}
	}

	public abstract ValidationResult validateNumberAndSetError(int input);

}
