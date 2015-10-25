package de.jformchecker.elements;

import de.jformchecker.FormCheckerElement;

public class LongTextInput extends TextInput implements FormCheckerElement {

  public static LongTextInput build(String name) {
    LongTextInput i = new LongTextInput();
    i.name = name;
    return i;
  }


  @Override
  public String getInputTag(String additionalTag, String classes) {
    return "<textarea " + additionalTag + " class=\"" + classes + "\" name=\"" + name + "\" id=\""
        + name + "\" " + ">" + (value == null ? "" : getValueHtmlEncoded()) + "</textarea>";
  }

}
