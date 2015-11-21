package de.jformchecker;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

// holds a collection of form-Elements that can be rendered by formchecker
public abstract class FormCheckerForm {

  List<FormCheckerElement> elements = new ArrayList<>();
  List<FormValidator> validators = new ArrayList<>();
  private Map<String, FormCheckerElement> fastAccess = new LinkedHashMap<>();
  
  private TagAttributes formTagAttributes = new TagAttributes();
  
  public TagAttributes getFormTagAttributes() {
    return formTagAttributes;
  }

  public void setFormTagAttributes(LinkedHashMap<String, String> formTagAttributes) {
    this.formTagAttributes = new TagAttributes(formTagAttributes);
  }

  public Map<String, FormCheckerElement> getElementsAsMap() {
    return fastAccess;
  }

  boolean html5Validation = true;
  
  {
    init();
  }

  // Should be overriden
  public abstract void init();
  
  public void disableHtml5Validation() {
    html5Validation = false;
  }
  
  public List<FormValidator> getValidators() {
    return validators;
  }

  public FormCheckerForm add(FormCheckerElement elem) {
    // RFE: Exception, if elem is added twice!
    elements.add(elem);
    fastAccess.put(elem.getName(), elem);
    return this;
  }

  public List<FormCheckerElement> getElements() {
    return elements;
  }

  public FormCheckerForm addFormValidator(FormValidator formValidator) {
    validators.add(formValidator);
    return this;
  }

  public FormCheckerElement getElement(String name) {
    return fastAccess.get(name);
  }
  
}
