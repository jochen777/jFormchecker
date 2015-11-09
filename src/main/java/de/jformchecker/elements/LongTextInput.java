package de.jformchecker.elements;

import java.util.Map;

import de.jformchecker.FormCheckerElement;

public class LongTextInput extends TextInput implements FormCheckerElement {

  public static LongTextInput build(String name) {
    LongTextInput i = new LongTextInput();
    i.name = name;
    return i;
  }


  @Override
  public String getInputTag(Map<String, String> attributes) {
    return "<textarea " + getElementId() +  buildAttributes(attributes) + getTabIndexTag() + 
        buildMaxLen() + " name=\"" + name + "\" id=\""
        + name + "\" " + ">" + (value == null ? "" : getValueHtmlEncoded()) + "</textarea>";
  }

}
