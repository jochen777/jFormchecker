package de.jformchecker;

import java.util.ArrayList;
import java.util.List;

import de.jformchecker.config.HTML5Mode;

// holds a collection of form-Elements that can be rendered by formchecker
public abstract class FormCheckerForm {

  List<FormCheckerElement> elements = new ArrayList<>();
  List<FormValidator> validators = new ArrayList<>();
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
    return this;
  }

  public List<FormCheckerElement> getElements() {
    return elements;
  }

  public FormCheckerForm addFormValidator(FormValidator formValidator) {
    validators.add(formValidator);
    return this;
  }

}
