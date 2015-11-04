package de.jformchecker.elements;

import java.util.Map;

import de.jformchecker.FormCheckerElement;

public class HiddenInput extends AbstractInput implements FormCheckerElement {


  public static HiddenInput build(String name) {
    HiddenInput i = new HiddenInput();
    i.name = name;
    return i;
  }


  @Override
  public String getInputTag(Map<String, String> attributes) {
    return "<input " + buildAttributes(attributes) + " type=\"hidden\"  name=\"" + name
        + "\"  id=\"" + name + "\" value=\"" + (value == null ? "" : getValueHtmlEncoded()) + "\">";
  }

  public boolean displayLabel() {
    return false;
  }



}
