package de.jformchecker.elements;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringEscapeUtils;

import de.jformchecker.FormCheckerElement;
import de.jformchecker.Validator;

public class DateInput extends AbstractInput implements FormCheckerElement {

  public static DateInput build(String name) {
    DateInput i = new DateInput();
    i.name = name;
    return i;
  }


  public static final int MONTH = 1;
  public static final int DAY = 2;
  public static final int YEAR = 3;

  String dayVal = "";
  String monthVal = "";
  String yearVal = "";


  @Override
  public String getInputTag(String additionalTag, String classes) {
    String style = classes;
    return getDatePartTag(DateInput.DAY, additionalTag, style) + " "
        + getDatePartTag(DateInput.MONTH, additionalTag, style) + " "
        + getDatePartTag(DateInput.YEAR, additionalTag, style);

  }


  public String getDatePartTag(int field, String additionalTag, String classes) {
    String inputField = "wrong field desc!";
    switch (field) {
      case MONTH:
        inputField = "<input " + additionalTag + " class=\"" + classes + "\" type=\"text\" id=\""
            + name + "_month\" name=\"" + name + "_month\"  value=\""
            + (StringEscapeUtils.escapeHtml4(monthVal)) + "\" >";
        break;
      case YEAR:
        inputField = "<input type=\"text\" " + additionalTag + " class=\"" + classes + "\" id=\""
            + name + "_year\" name=\"" + name + "_year\"  value=\""
            + (StringEscapeUtils.escapeHtml4(yearVal)) + "\" >";
        break;
      case DAY:
        inputField = "<input type=\"text\" " + additionalTag + " class=\"" + classes + "\" id=\""
            + name + "_day\" name=\"" + name + "_day\"  value=\""
            + (StringEscapeUtils.escapeHtml4(dayVal)) + "\" >";
        break;
    }
    return inputField;
  }

  @Override
  public void init(HttpServletRequest request, boolean firstRun) {
    if (firstRun) {
      this.setValue(this.getPreSetValue());
    } else {
      dayVal = request.getParameter(name + "_day");
      yearVal = request.getParameter(name + "_year");
      monthVal = request.getParameter(name + "_month");


      this.setValue(yearVal + "." + monthVal + "." + dayVal);
      Validator v = new Validator();
      String errMsg = v.validate(this);
      if (errMsg != null) {
        this.valid = false;
        this.setErrorMessage(errMsg);
      }
    }
  }

}
