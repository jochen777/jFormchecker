package de.jformchecker.themes;

import de.jformchecker.FormCheckerElement;
import de.jformchecker.GenericFormBuilder;
import de.jformchecker.TagAttributes;
import de.jformchecker.Wrapper;

public class BasicBootstrapFormBuilder extends BasicFormBuilder{

  @Override
  public TagAttributes getFormAttributes() {
    TagAttributes attributes = new TagAttributes();
    return attributes;
  }

  @Override
  public Wrapper getWrapperForInput(FormCheckerElement elem) {
    return new Wrapper("", "");
  }

  public TagAttributes getLabelAttributes(FormCheckerElement elem) {
    TagAttributes attributes = new TagAttributes();
    attributes.put("class", "control-label");
    return attributes;

  }
  
}
