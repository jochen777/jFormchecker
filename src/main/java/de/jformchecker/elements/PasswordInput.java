package de.jformchecker.elements;

import java.util.Map;

import de.jformchecker.FormCheckerElement;

public class PasswordInput extends AbstractInput implements FormCheckerElement {

  public static PasswordInput build(String name) {
    PasswordInput i = new PasswordInput();
    i.name = name;
    return i;
  }


  @Override
  public String getInputTag(Map<String, String> attributes) {
    return String.format(
        "<input " + buildAllAttributes(attributes) + buildMaxLen()
            + " type=\"password\" name=\"%s\" value=\"%s\">",
        name, (value == null ? "" : getValueHtmlEncoded()));
  }



}
