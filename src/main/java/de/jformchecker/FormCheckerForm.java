package de.jformchecker;

import java.util.ArrayList;
import java.util.List;

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


}
