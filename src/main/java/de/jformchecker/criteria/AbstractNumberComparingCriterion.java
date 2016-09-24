package de.jformchecker.criteria;

import de.jformchecker.FormCheckerElement;

/**
 * provides some utils for criterias, that compare numbers (currentyl: min/max)
 * 
 * @author jpier
 *
 */
public abstract class AbstractNumberComparingCriterion extends AbstractCriterion {
  private String errMsg; 

  protected boolean verify(FormCheckerElement value) {
    try {
      int input = Integer.parseInt(value.getValue());
      return validateNumberAndSetError(input);
    } catch (NumberFormatException e) {
      errMsg = "Please enter a number";
      return false;
    }
  }

  public abstract boolean validateNumberAndSetError(int input);
  
  protected String generateErrorMessage() {
    return errMsg;
  }

  public void setErrMsg(String errMsg) {
    this.errMsg = errMsg;
  }

}
