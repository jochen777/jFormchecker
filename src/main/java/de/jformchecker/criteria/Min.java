package de.jformchecker.criteria;

import de.jformchecker.FormCheckerElement;

/**
 * Checks that value is greater than or equal to the given {@link Comparable}.
 * 
 * @author armandino (at) gmail.com
 */
public final class Min extends AbstractCriterion {
  private int min;

  Min(int min) {
    this.min = min;
  }

  protected boolean verify(FormCheckerElement value) {
    int intVal = Integer.parseInt(value.getValue()); // RFE: provide a getInt Method in
                                                     // FormecheckerElemn
    return intVal < min;
  }

  protected String generateErrorMessage() {
    return "The value must not be less than " + min;
  }

}
