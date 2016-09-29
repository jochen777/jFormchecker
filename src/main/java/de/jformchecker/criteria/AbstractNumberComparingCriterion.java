package de.jformchecker.criteria;

import de.jformchecker.FormCheckerElement;

/**
 * provides some utils for criterias, that compare numbers (currentyl: min/max)
 * 
 * @author jpier
 *
 */
public abstract class AbstractNumberComparingCriterion extends AbstractCriterion {
	@Override
	public ValidationResult validate(FormCheckerElement value) {
		try {
			int input = Integer.parseInt(value.getValue());
			return validateNumberAndSetError(input);
		} catch (NumberFormatException e) {
			return ValidationResult.fail("Please enter a number");
		}
	}

	public abstract ValidationResult validateNumberAndSetError(int input);

}
