package de.jformchecker.criteria;

import de.jformchecker.Criterion;
import de.jformchecker.FormCheckerElement;

/**
 * An abstract criterion that verifies itself 
 * 
 * Based on work of armandino (at) gmail.com
 */
public abstract class AbstractCriterion implements Criterion {

	@Override
	public abstract ValidationResult validate(FormCheckerElement value);
}
