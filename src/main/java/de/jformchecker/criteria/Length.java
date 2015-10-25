package de.jformchecker.criteria;

import de.jformchecker.FormCheckerElement;

/**
 * Checks that the length of the value is within the given range.
 * 
 * @author armandino (at) gmail.com
 */
public final class Length extends AbstractCriterion {
  private int min;
  private int max;

  Length(int min, int max) {
    this.min = min;
    this.max = max;
  }

  protected boolean verify(FormCheckerElement value) {
    return value.getValue().length() <= max && value.getValue().length() >= min;
  }

  protected String generateErrorMessage() {
    return "The value must be between " + min + " and " + max + " characters long";
  }

}
