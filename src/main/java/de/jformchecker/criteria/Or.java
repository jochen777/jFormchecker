package de.jformchecker.criteria;

import de.jformchecker.Criterion;
import de.jformchecker.FormCheckerElement;


/**
 * Performs an <tt>OR</tt> over all criteria on the given value.
 * 
 * Based on work of armandino (at) gmail.com
 */
public final class Or extends AbstractCriterion {
  private Criterion[] criteria;

  Or(Criterion... criteria) {
    if (criteria.length < 2)
      throw new IllegalArgumentException(getClass().getName() + " requires at least two criteria");

    this.criteria = criteria;
  }

  protected boolean verify(FormCheckerElement value) {
    for (Criterion criterion : criteria) {
      if (criterion.isSatisfied(value))
        return true;
    }

    return false;
  }

  protected String generateErrorMessage() {
    return "Invalid input value";
  }

}
