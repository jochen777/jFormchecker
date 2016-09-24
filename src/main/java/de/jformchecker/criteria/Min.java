package de.jformchecker.criteria;

/**
 * Checks that value is greater than or equal to the given {@link Comparable}.
 * 
 * Based on work of armandino (at) gmail.com
 */
public final class Min extends AbstractNumberComparingCriterion {
  private int min;

  Min(int min) {
    this.min = min;
  }

  @Override
  public boolean validateNumberAndSetError(int input) {
    boolean result = input > min;
    if (!result) {
      this.setErrMsg("The value must not be larger than " + min);
    }
    return result;
  }

}
