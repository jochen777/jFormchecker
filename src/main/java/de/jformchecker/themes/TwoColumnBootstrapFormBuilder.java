package de.jformchecker.themes;

import de.jformchecker.FormCheckerElement;
import de.jformchecker.GenericFormBuilder;
import de.jformchecker.TagAttributes;
import de.jformchecker.Wrapper;

public class TwoColumnBootstrapFormBuilder extends BasicFormBuilder{
  public TagAttributes getLabelAttributes(FormCheckerElement elem) {
    TagAttributes attributes = new TagAttributes();
    attributes.put("class", "col-sm-2 control-label");
    return attributes;
  }
  
  public Wrapper getWrapperForInput(FormCheckerElement elem) {
    return new Wrapper("<div class=\"col-sm-10\">", "</div>");
  }
  
  public TagAttributes getFormAttributes() {
    TagAttributes attributes = new TagAttributes();
    attributes.put("class", "form-horizontal");
    return attributes;
  }
}
