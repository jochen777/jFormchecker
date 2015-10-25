package de.jformchecker.criteria;

import de.jformchecker.FormCheckerElement;

/**
 * Checks that value is not greater than the specified maximum.
 * 
 * @author armandino (at) gmail.com
 */
public final class MaxLength extends AbstractCriterion {
  private int maxLength;

  MaxLength(int maxLength) {
    this.maxLength = maxLength;
  }

  protected boolean verify(FormCheckerElement value) {
    return value.getValue().length() <= maxLength;
  }

  protected String generateErrorMessage() {
    return "The value must not be greater than " + maxLength + " characters long";
  }

}
