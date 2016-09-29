package de.jformchecker.criteria;

import de.jformchecker.FormCheckerElement;

/**
 * Checks if value starts with the given string.
 * 
 * Based on work of armandino (at) gmail.com
 */
public final class StartsWith extends AbstractCriterion {
	private String[] prefixes;

	StartsWith(String... prefixes) {
		this.prefixes = prefixes;
	}



	@Override
	public ValidationResult validate(FormCheckerElement value) {
		boolean isValid = false;
		for (String prefix : prefixes) {
			if (value.getValue().startsWith(prefix))
				isValid = true;
		}

		if (!isValid) {
			return ValidationResult.fail("Please enter a value starting with %s", (Object []) prefixes);
		}
		return ValidationResult.ok();
	}

}
