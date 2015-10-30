package de.jformchecker.elements;

import de.jformchecker.FormCheckerElement;

public class TextInput extends AbstractInput implements FormCheckerElement {


  public static TextInput build(String name) {
    TextInput i = new TextInput();
    i.name = name;
    return i;
  }


  @Override
  public String getInputTag(String additionalTag, String classes) {
    return String.format(
        "<input " + getElementId() + " " + additionalTag + " class=\"" + classes + "\" tabindex=\"" + getTabIndex()
            + "\" type=\"text\" name=\"%s\" value=\"%s\">",
        name, (value == null ? "" : getValueHtmlEncoded()));
  }


}
