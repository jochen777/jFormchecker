package de.jformchecker.criteria;

import de.jformchecker.Criterion;
import de.jformchecker.FormCheckerElement;


/**
 * An abstract criterion that verifies itself and sets an error message if validation fails.
 * 
 * @author armandino (at) gmail.com
 */
public abstract class AbstractCriterion implements Criterion {
  private String messageOnError;

  /**
   * Verifies that the argument satisfies this criterion.
   */
  protected abstract boolean verify(FormCheckerElement value);

  /**
   * Generates an error message for this criterion.
   */
  protected abstract String generateErrorMessage();

  public final boolean isSatisfied(FormCheckerElement value) {
    boolean isSatisfied = value != null && verify(value);

    if (value == null) {
      setOnError("Please enter a valid value");
    } else if (!isSatisfied) {
      setOnError(generateErrorMessage());
    }

    return isSatisfied;
  }

  public final String getOnError() {
    return messageOnError;
  }

  public final void setOnError(String messageOnError) {
    this.messageOnError = messageOnError;
  }

}
