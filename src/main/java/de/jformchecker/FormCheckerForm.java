package de.jformchecker;

import java.util.ArrayList;
import java.util.List;

import de.jformchecker.config.HTML5Mode;

// holds a collection of form-Elements that can be rendered by formchecker
public class FormCheckerForm {

  List<FormCheckerElement> elements = new ArrayList<>();
  List<FormValidator> validators = new ArrayList<>();
  HTML5Mode html5Mode = HTML5Mode.disableHtml;


  public HTML5Mode getHtml5Mode() {
    return html5Mode;
  }

  public void setHtml5Mode(HTML5Mode html5Mode) {
    this.html5Mode = html5Mode;
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
