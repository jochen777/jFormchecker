package de.jformchecker.elements;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

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

  Date internalDate = null;

  @Override
  public String getInputTag(Map<String, String> attributes) {
    return getDatePartTag(DateInput.DAY, attributes) + " "
        + getDatePartTag(DateInput.MONTH, attributes) + " "
        + getDatePartTag(DateInput.YEAR, attributes);

  }

  public void presetValue(Date t) {
    internalDate = t;
    GregorianCalendar gc = new GregorianCalendar();
    gc.setTime(t);
    yearVal = "" + gc.get(Calendar.YEAR);
    dayVal = "" + gc.get(Calendar.DAY_OF_MONTH);
    monthVal = "" + (gc.get(Calendar.MONTH) + 1);
  }


  public String getDatePartTag(int field, Map<String, String> attributes) {
    String inputField = "wrong field desc!";
    switch (field) {
      case DAY:
        inputField = "<input type=\"text\" " +  buildAttributes(attributes) 
            + getTabIndexTag() + " id=\""
            + name + "_day\" name=\"" + name + "_day\"  value=\""
            + (StringEscapeUtils.escapeHtml4(dayVal)) + "\"  placeholder=\"day\">";
          
        break;
      case MONTH:
        setTabIndex(getTabIndex()+1);
        inputField = "<input " + getElementId() + buildAttributes(attributes) + getTabIndexTag()
            + " type=\"text\" id=\"" + name + "_month\" name=\"" + name + "_month\"  value=\""
            + (StringEscapeUtils.escapeHtml4(monthVal)) + "\" placeholder=\"month\">";
        break;
      case YEAR:
        setTabIndex(getTabIndex()+1);
        inputField = "<input type=\"text\" " +  buildAttributes(attributes) + getTabIndexTag()+
            " id=\""
            + name + "_year\" name=\"" + name + "_year\"  value=\""
            + (StringEscapeUtils.escapeHtml4(yearVal)) + "\"  placeholder=\"year\">";
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
      // if empty and not required, everything is fine
      if (!isRequired() && StringUtils.isEmpty(dayVal) && 
          StringUtils.isEmpty(yearVal) &&
          StringUtils.isEmpty(monthVal)) {
        this.valid = true;
      } else {  // check date format
        String dateVal = yearVal + "-" + monthVal + "-" + dayVal;
        this.setValue(dateVal);
  
        SimpleDateFormat formater = new SimpleDateFormat("yy-MM-dd");
        try {
          formater.setLenient(false);
          internalDate = formater.parse(dateVal);
          this.valid = true;
          Validator v = new Validator();
          String errMsg = v.validate(this);
          if (errMsg != null) {
            this.valid = false;
            this.setErrorMessage(errMsg);
          }
        } catch (ParseException e) {
          this.valid = false;
          this.setErrorMessage("Wrong Date format...");
  
        }
      }
    }
  }

  @Override
  public int getLastTabIndex() {
    return this.getTabIndex() + 3;
  }

  
  public String getValue() {
    return value;
  }

  public Date getDateValue() {
    return internalDate;
  }

}
