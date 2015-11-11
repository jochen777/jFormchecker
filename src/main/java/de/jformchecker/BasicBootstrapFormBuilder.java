package de.jformchecker;

import java.util.LinkedHashMap;
import java.util.Map;

public class BasicBootstrapFormBuilder extends GenericFormBuilder{

  @Override
  public Map<String, String> getFormAttributes() {
    Map<String, String> attributes = new LinkedHashMap<>();
    return attributes;
  }

  @Override
  public Wrapper getWrapperForInput(FormCheckerElement elem) {
    return new Wrapper("", "");
  }

  @Override
  public TagAtrributes getLabelAttributes(FormCheckerElement elem) {
    return new TagAtrributes();
  }
  
}
