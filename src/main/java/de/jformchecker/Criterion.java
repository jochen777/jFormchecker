package de.jformchecker;


/**
 * A criterion that can evaluate to <code>true</code> or <code>false</code>.
 * 
 * @author armandino (at) gmail.com
 */
public interface Criterion extends MessageOnError {
  /**
   * Tests whether the specified value satisfies this criterion.
   * 
   * @param value to be tested against this criterion.
   * @return <code>true</code> if the criterion is satisfied, <code>false</code> otherwise.
   */
  public boolean isSatisfied(FormCheckerElement value);

}
