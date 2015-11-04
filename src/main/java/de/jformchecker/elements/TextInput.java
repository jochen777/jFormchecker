package de.jformchecker.elements;

import java.util.Map;

import org.apache.commons.lang3.StringEscapeUtils;

import de.jformchecker.FormCheckerElement;

public class TextInput extends AbstractInput implements FormCheckerElement {

  boolean placeholderMode = false;
  
  public static TextInput build(String name) {
    TextInput i = new TextInput();
    i.name = name;
    return i;
  }
  
  public TextInput setPlaceHolderMode() {
    placeholderMode = true;
    return this;
  }
  
  @Override
  public boolean displayLabel() {
    if (placeholderMode) {
      return false;
    }
    return true;
  }
  

  @Override
  public String getInputTag(Map<String, String> attributes) {
    return String.format(
        "<input " + getElementId() + buildAttributes(attributes) + " tabindex=\"" + getTabIndex()
            + "\" type=\"text\" name=\"%s\" value=\"%s\""+getPlaceholder()+">",
        name, (value == null ? "" : getValueHtmlEncoded()));
  }

  private String getPlaceholder() {
    return placeholderMode?" placeholder=\""+
        StringEscapeUtils.escapeHtml4(getDescription())+"\"":"";
  }


}
