package de.jformchecker.criteria;

import de.jformchecker.Criterion;
import de.jformchecker.FormCheckerElement;


/**
 * Performs an <tt>AND</tt> over all criteria on the given value.
 * 
 * Based on work of armandino (at) gmail.com
 */
public final class And extends AbstractCriterion {
  private Criterion[] criteria;
  private Criterion failedCriterion;

  And(Criterion... criteria) {
    if (criteria.length < 2)
      throw new IllegalArgumentException(getClass().getName() + " requires at least two criteria");

    this.criteria = criteria;
  }

  protected boolean verify(FormCheckerElement value) {
    for (Criterion criterion : criteria) {
      if (!criterion.isSatisfied(value)) {
        failedCriterion = criterion;
        return false;
      }
    }

    return true;
  }

  protected String generateErrorMessage() {
    return failedCriterion.getOnError();
  }

}
