package de.jformchecker.elements;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import de.jformchecker.FormCheckerElement;
import de.jformchecker.Validator;

public class CheckboxInput extends AbstractInput implements FormCheckerElement {


  public static CheckboxInput build(String name) {
    CheckboxInput ci = new CheckboxInput();
    ci.name = name;
    return ci;
  }


  @Override
  public String getInputTag(Map<String, String> attributes) {
    String style = "";
    return "<input " + getElementId() + buildAttributes(attributes)  + " type=\"checkbox\" name=\"" + name
        + "\" id=\"" + name + "\" value=\"" + name + "\" " + style + " " + getCheckedStatus(name)
        + ">";
  }


  private String getCheckedStatus(String name) {
    if ("true".equals(value)) {
      return "checked";
    } else {
      return "";
    }
  }
  
  @Override
  public void init(HttpServletRequest request, boolean firstRun) {
    if (firstRun) {
      this.setValue(this.getPreSetValue());
    } else {
      // TODO: check this again!
      if (name.equals(request.getParameter(this.getName()))) {
        this.value = "true";
      } else {
        this.value = "false";
      }
      
      Validator v = new Validator();
      String errMsg = v.validate(this);
      if (errMsg != null) {
        this.valid = false;
        this.setErrorMessage(errMsg);
      }
    }
  }

  public boolean getBoolValue() {
    if ("true".equals(this.valid)) {
      return true;
    } else {
      return false;
    }
  }
}
