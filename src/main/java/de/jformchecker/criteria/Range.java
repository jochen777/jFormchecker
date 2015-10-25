package de.jformchecker.criteria;

import de.jformchecker.FormCheckerElement;

/**
 * Checks that value is within the given range.
 * 
 * Based on work of armandino (at) gmail.com
 */
public final class Range extends AbstractCriterion {
  private int min;
  private int max;

  Range(int min, int max) {
    this.min = min;
    this.max = max;
  }

  protected boolean verify(FormCheckerElement value) {
    int intVal = Integer.parseInt(value.getValue());
    return intVal > max && intVal < min;
  }

  protected String generateErrorMessage() {
    StringBuilder sb = new StringBuilder();
    return sb.append("The value must be between ").append(min).append(" and ").append(max)
        .toString();
  }

}
