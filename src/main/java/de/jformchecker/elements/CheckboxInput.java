package de.jformchecker.elements;

import de.jformchecker.FormCheckerElement;

public class CheckboxInput extends AbstractInput implements FormCheckerElement {


  public static CheckboxInput build(String name) {
    CheckboxInput ci = new CheckboxInput();
    ci.name = name;
    return ci;
  }


  @Override
  public String getInputTag(String additionalTag, String classes) {
    String style = "";
    return "<input " + additionalTag + " class=\"" + classes + "\" type=\"checkbox\" name=\"" + name
        + "\" id=\"" + name + "\" value=\"" + name + "\" " + style + " " + getCheckedStatus(name)
        + ">";
  }


  private String getCheckedStatus(String name) {
    if (value != null && value.equals(name)) {
      return "checked";
    } else {
      return "";
    }
  }


}
