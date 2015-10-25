package de.jformchecker;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class FormChecker {
  Map<String, FormCheckerElement> elements = new LinkedHashMap<String, FormCheckerElement>();
  HttpServletRequest req;
  boolean firstRun = true;
  boolean isMultipart = false;
  boolean isValid = true;

  public static final String SUBMIT_KEY = "submitted";
  public static final String SUBMIT_VALUE_PREFIX = "FORMCHECKER_";

  public boolean isValid() {
    return isValid;
  }

  GenericFormBuilder formBuilder = new GenericFormBuilder();

  public GenericFormBuilder getFormBuilder() {
    return formBuilder;
  }

  public void setFormBuilder(GenericFormBuilder formBuilder) {
    this.formBuilder = formBuilder;
  }

  // where the form is submitted to
  private String formAction = "#";

  public Map<String, FormCheckerElement> getElements() {
    return elements;
  }

  public String getValue(String elementName) {
    return elements.get(elementName).getValue();
  }

  public String getSubmitTag() {
    return formBuilder.getSubmittedTag(id);
  }

  public String getLabelTag(String elementName) {
    return formBuilder.getLabelForElement(elements.get(elementName), "", "", firstRun);
  }

  public String getLabelTag(String elementName, String tagAddition, String classes) {
    return formBuilder.getLabelForElement(elements.get(elementName), tagAddition, classes,
        firstRun);
  }


  String id;

  public FormChecker(String _id, HttpServletRequest _req) {
    id = _id;
    req = _req;
  }

  public FormCheckerElement add(FormCheckerElement element) {
    element.setFormChecker(this);
    elements.put(element.getName(), element);
    return element;
  }

  public void addForm(FormCheckerForm form) {
    for (FormCheckerElement element : form.getElements()) {
      add(element);
    }
  }

  public String getGenericForm() {
    return formBuilder.getGenericForm(id, formAction, elements, isMultipart, firstRun);
  }

  public String getLabelForElement(FormCheckerElement e, String style, String classes) {
    return this.formBuilder.getLabelForElement(e, style, classes, firstRun);
  }

  public void run() {
    boolean first = true;
    for (Map.Entry<String, FormCheckerElement> entry : elements.entrySet()) {
      if (first) {
        if ((FormChecker.SUBMIT_VALUE_PREFIX + id)
            .equals(req.getParameter(FormChecker.SUBMIT_KEY))) {
          firstRun = false;
        }
        first = false;
      }
      FormCheckerElement elem = entry.getValue();
      elem.init(req, firstRun);
      if (!elem.isValid()) {
        isValid = false;
      }
    }

  }
}
