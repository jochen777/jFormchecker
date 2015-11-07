package de.jformchecker;

import java.util.LinkedHashMap;
import java.util.Map;

public class BasicBootstrapFormBuilder extends GenericFormBuilder{

  public Map<String, String> getFormAttributes() {
    Map<String, String> attributes = new LinkedHashMap<>();
    return attributes;
  }

  public String getBeforeInput(FormCheckerElement elem) {
    return "";
  }

  public String getAfterInput(FormCheckerElement elem) {
    return "";
  }

  public TagAtrributes getLabelAttributes(FormCheckerElement elem) {
    return new TagAtrributes();
  }
  
}
