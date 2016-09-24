package de.jformchecker.criteria;

import de.jformchecker.FormCheckerElement;

/**
 * Checks that the value is less than or equal to the given {@link Comparable}.
 * 
 * Based on work of armandino (at) gmail.com
 */
public final class Max extends AbstractNumberComparingCriterion {
  private int max;

  Max(int max) {
    this.max = max;
  }

  @Override
  public boolean validateNumberAndSetError(int input) {
    boolean result = input < max;
    if (!result) {
      this.setErrMsg("The value must be smaller than " + max);
    }
    return result;
  }


}
