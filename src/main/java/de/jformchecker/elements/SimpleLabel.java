package de.jformchecker.elements;

import javax.servlet.http.HttpServletRequest;

import de.jformchecker.FormCheckerElement;

public class SimpleLabel extends AbstractInput implements FormCheckerElement {

  public static SimpleLabel build(String name) {
    SimpleLabel i = new SimpleLabel();
    i.name = name;
    return i;
  }
  
  @Override
  public String getValue() {
    return this.getDescription();
  }


  @Override
  public String getInputTag(String additionalTag, String classes) {
    return ""; // output nothing, because headline will be outputted as label
  }

  @Override
  public void init(HttpServletRequest request, boolean firstRun) {}

}
