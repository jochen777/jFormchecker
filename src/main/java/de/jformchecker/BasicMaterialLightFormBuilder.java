package de.jformchecker;

import java.util.LinkedHashMap;
import java.util.Map;

public class BasicMaterialLightFormBuilder extends GenericFormBuilder{

  @Override
  public TagAtrributes getFormAttributes() {
    TagAtrributes attributes = new TagAtrributes();
    return attributes;
  }

  @Override
  public Wrapper getWrapperForInput(FormCheckerElement elem) {
    return new Wrapper("<div class=\"mdl-textfield mdl-js-textfield\">", "</div>");
  }

  @Override
  public TagAtrributes getLabelAttributes(FormCheckerElement elem) {
    TagAtrributes t = new TagAtrributes();
    t.addToAttribute("class", "mdl-textfield__label ");
    return t;
  }
  
  



  public void addAttributesToInputFields(Map<String, String> attribs, FormCheckerElement elem) {
    attribs.put("class", "mdl-textfield__input ");
  }


  // returns the HTML code that should be given out, before and after an input-element is written
  public Wrapper getWrapperForElem(FormCheckerElement elem) {
    return new Wrapper("<div class=\"mdl-textfield mdl-js-textfield\">", "</div>");
  }

}
