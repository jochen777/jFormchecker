package de.jformchecker.elements;

import java.util.Map;

import de.jformchecker.FormCheckerElement;

public class TextInput extends AbstractInput implements FormCheckerElement {


  public static TextInput build(String name) {
    TextInput i = new TextInput();
    i.name = name;
    return i;
  }


  @Override
  public String getInputTag(Map<String, String> attributes) {
    return String.format(
        "<input " + getElementId() + buildAttributes(attributes) + " tabindex=\"" + getTabIndex()
            + "\" type=\"text\" name=\"%s\" value=\"%s\">",
        name, (value == null ? "" : getValueHtmlEncoded()));
  }


}
