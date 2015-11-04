package de.jformchecker;

import java.util.ArrayList;
import java.util.List;

import de.jformchecker.elements.TextInput;

// holds a collection of form-Elements that can be rendered by formchecker
public class FormCheckerForm {

  List<FormCheckerElement> elements = new ArrayList<>();

  public FormCheckerForm add(FormCheckerElement elem) {
    // RFE: Exception, if elem is added twice!
    elements.add(elem);
    return this;
  }

  public List<FormCheckerElement> getElements() {
    return elements;
  }

  public void setPlaceholderMode() {
    for (FormCheckerElement formCheckerElement : elements) {
      if (formCheckerElement instanceof TextInput) {
        ((TextInput)formCheckerElement).setPlaceHolderMode();
      }
    }
  }

}
