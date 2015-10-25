package de.jformchecker.elements;

import javax.servlet.http.HttpServletRequest;

import de.jformchecker.FormCheckerElement;

public class Headline extends AbstractInput implements FormCheckerElement {

  public static Headline build(String name) {
    Headline i = new Headline();
    i.name = name;
    return i;
  }


  @Override
  public String getInputTag(String additionalTag, String classes) {
    return ""; // output nothing, because headline will be outputted as label
  }

  @Override
  public void init(HttpServletRequest request, boolean firstRun) {}

}
