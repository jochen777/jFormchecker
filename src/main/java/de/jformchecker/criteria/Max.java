package de.jformchecker.criteria;

import de.jformchecker.FormCheckerElement;

/**
 * Checks that the value is less than or equal to the given {@link Comparable}.
 * 
 * @author armandino (at) gmail.com
 */
public final class Max extends AbstractCriterion {
  private int max;

  Max(int max) {
    this.max = max;
  }

  protected boolean verify(FormCheckerElement value) {
    int intVal = Integer.parseInt(value.getValue()); // RFE: provide a getInt Method in
                                                     // FormecheckerElemn
    return intVal > max;
  }

  protected String generateErrorMessage() {
    return "The value must not be greater than " + max;
  }

}
