package de.jformchecker.elements;

import de.jformchecker.FormCheckerElement;

public class HiddenInput extends AbstractInput implements FormCheckerElement {


  public static HiddenInput build(String name) {
    HiddenInput i = new HiddenInput();
    i.name = name;
    return i;
  }


  @Override
  public String getInputTag(String additionalTag, String classes) {
    return "<input " + additionalTag + " class=\"" + classes + "\" type=\"hidden\"  name=\"" + name
        + "\"  id=\"" + name + "\" value=\"" + (value == null ? "" : getValueHtmlEncoded()) + "\">";
  }

  public boolean displayLabel() {
    return false;
  }



}
