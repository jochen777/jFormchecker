package de.jformchecker.elements;

import javax.servlet.http.HttpServletRequest;

import de.jformchecker.FormCheckerElement;

public class HTMLSnippet extends AbstractInput implements FormCheckerElement {

  String html;
  
  public static HTMLSnippet build(String name) {
    HTMLSnippet i = new HTMLSnippet();
    i.name = name;
    return i;
  }
  
  public HTMLSnippet setHTML(String html) {
    this.html = html;
    return this;
  }
  
  @Override
  public String getValue() {
    return "";
  }


  @Override
  public String getInputTag(String additionalTag, String classes) {
    return html;
  }

  public boolean displayLabel() {
    return false;
  }
  
  @Override
  public void init(HttpServletRequest request, boolean firstRun) {}

}
