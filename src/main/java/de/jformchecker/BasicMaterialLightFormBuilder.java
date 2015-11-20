package de.jformchecker;

import java.util.LinkedHashMap;
import java.util.Map;

public class BasicMaterialLightFormBuilder extends GenericFormBuilder{

  @Override
  public TagAttributes getFormAttributes() {
    TagAttributes attributes = new TagAttributes();
    return attributes;
  }

  @Override
  public Wrapper getWrapperForInput(FormCheckerElement elem) {
    return new Wrapper("<div class=\"mdl-textfield mdl-js-textfield\">", "</div>");
  }

  @Override
  public TagAttributes getLabelAttributes(FormCheckerElement elem) {
    TagAttributes t = new TagAttributes();
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
