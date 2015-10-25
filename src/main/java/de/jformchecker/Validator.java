package de.jformchecker;

public class Validator {
  String errorMessage = null;

  // RFE: Use optional!
  public String validate(FormCheckerElement elem) {
    boolean isValid = false;
    String value = elem.getValue();
    if (value != null && !"".equals(value)) {
      isValid = allCriteriaSatisfied(elem);
    } else {
      // blank input is valid if it's not required
      isValid = !elem.isRequired();
    }

    if (!isValid && errorMessage == null)
      errorMessage = "Invalid or missing value";

    return errorMessage;
  }

  private boolean allCriteriaSatisfied(FormCheckerElement elem) {
    String parsedValue = elem.getValue();
    if (parsedValue == null)
      return false;
    for (Criterion criterion : elem.getCriteria()) {
      if (!criterion.isSatisfied(elem)) {
        errorMessage = criterion.getOnError();
        return false;
      }
    }

    return true;
  }
}
