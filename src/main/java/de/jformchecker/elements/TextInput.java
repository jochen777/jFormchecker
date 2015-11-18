package de.jformchecker.elements;

import java.util.Map;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

import de.jformchecker.FormCheckerElement;

public class TextInput extends AbstractInput implements FormCheckerElement {

  private String placeholderText = "";
  
  
  public static TextInput build(String name) {
    TextInput i = new TextInput();
    i.name = name;
    return i;
  }

  public TextInput setPlaceholerText(String placeholderText) {
    this.placeholderText = placeholderText;
    return this;
  }

  @Override
  public String getInputTag(Map<String, String> attributes) {
    return String.format(
        "<input " + buildAllAttributes(attributes)  + buildMaxLen()
            + "type=\"text\" name=\"%s\" value=\"%s\""+getPlaceholder()+">",
        name, (value == null ? "" : getValueHtmlEncoded()));
  }

  private String getPlaceholder() {
    return StringUtils.isEmpty(placeholderText)?"":" placeholder=\""+
        StringEscapeUtils.escapeHtml4(placeholderText)+"\"";
  }


}
