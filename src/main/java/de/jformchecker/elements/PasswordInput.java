package de.jformchecker.elements;

import de.jformchecker.FormCheckerElement;

public class PasswordInput extends AbstractInput implements FormCheckerElement {

  public static PasswordInput build(String name) {
    PasswordInput i = new PasswordInput();
    i.name = name;
    return i;
  }


  @Override
  public String getInputTag(String additionalTag, String classes) {
    return String.format(
        "<input " + getElementId() + " " + additionalTag + " class=\"" + classes + "\" tabindex=\"" + getTabIndex()
            + "\" type=\"password\" name=\"%s\" value=\"%s\">",
        name, (value == null ? "" : getValueHtmlEncoded()));
  }



}
