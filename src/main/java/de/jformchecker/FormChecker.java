package de.jformchecker;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringEscapeUtils;

import de.jformchecker.criteria.Criteria;
import de.jformchecker.criteria.MaxLength;

/**
 * FormChecker handles the initialisation, error- and submit status. This object should be stored to
 * be accessed from the template-system.
 */
public class FormChecker {
  Map<String, FormCheckerElement> elements = new LinkedHashMap<String, FormCheckerElement>();
  HttpServletRequest req;
  boolean firstRun = true;
  boolean isMultipart = false;
  boolean isValid = true;
  boolean protectedAgainstCSRF = false; // TBD: Default no protection, because the normal case is
                                        // not logged in?!?
  String completeForm;
  int defaultMaxLenElements = 1000; // override this for each element, if you want longer vals!
  private final SecureRandom random = new SecureRandom();

  public static final String SUBMIT_KEY = "submitted";
  public static final String SUBMIT_VALUE_PREFIX = "FORMCHECKER_";

  public boolean isValid() {
    return isValid;
  }

  public boolean isValidAndNotFirstRun() {
    return isValid && !firstRun;
  }


  public static FormChecker build(String _id, HttpServletRequest _req, FormCheckerForm form) {
    FormChecker fc = new FormChecker(_id, _req);
    fc.addForm(form);
    return fc;
  }

  public FormChecker setProtectAgainstCSRF() {
    protectedAgainstCSRF = true;
    return this;
  }


  // TODO: place this in separate class.
  String buildCSRFTokens() {
    // is firstrun - then generate a complete new token
    StringBuilder tags = new StringBuilder();
    String name = "";
    String xsrfVal = "";

    String tokenName = "tokenname";
    String tokenVal = "tokenVal";

    if (!firstRun) {
      name = req.getParameter(tokenName);
      xsrfVal = req.getParameter(tokenVal);
      System.out.println("xsrf-check: " + xsrfVal + "::" + req.getSession().getAttribute(name));
      if (xsrfVal == null || !xsrfVal.equals(req.getSession().getAttribute(name))) {
        throw new RuntimeException("Security Problem!");
      }

    }
    name = "token_" + Math.random();
    xsrfVal = getRandomValue();
    req.getSession().setAttribute(name, xsrfVal);


    tags.append("<input type=\"hidden\" name=\"" + tokenName + "\" value=\""
        + StringEscapeUtils.escapeHtml4(name) + "\">");
    tags.append("<input type=\"hidden\" name=\"" + tokenVal + "\" value=\""
        + StringEscapeUtils.escapeHtml4(xsrfVal) + "\">");
    return tags.toString();
  }

  String getRandomValue() {
    final byte[] bytes = new byte[32];
    random.nextBytes(bytes);
    return Base64.getEncoder().encodeToString(bytes);
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

    // check, if maxLen is set. If not, add default-max-len
    // defaultMaxLenElements;
    boolean maxLenAvail = false;

    for (Criterion criterion : element.getCriteria()) {
      if (criterion instanceof MaxLength) {
        maxLenAvail = true;
      }
    }
    if (!maxLenAvail) {
      element.getCriteria().add(Criteria.maxLength(defaultMaxLenElements));
    }

    elements.put(element.getName(), element);
    return element;
  }

  public void addForm(FormCheckerForm form) {
    for (FormCheckerElement element : form.getElements()) {
      add(element);
    }
  }

  public String getGenericForm() {
    return formBuilder.getGenericForm(id, formAction, elements, isMultipart, firstRun, this);
  }

  public String getLabelForElement(FormCheckerElement e, String style, String classes) {
    return this.formBuilder.getLabelForElement(e, style, classes, firstRun);
  }

  public FormChecker run() {

    sortTabIndexes();

    checkIfFirstRun();



    for (Map.Entry<String, FormCheckerElement> entry : elements.entrySet()) {
      FormCheckerElement elem = entry.getValue();
      elem.init(req, firstRun);
      if (!elem.isValid()) {
        isValid = false;
      }
    }
    // build complete Form here!
    completeForm = this.getGenericForm();
    return this;
  }

  // resort tab-indexes
  private void sortTabIndexes() {
    int tabIndex = 100;
    for (Map.Entry<String, FormCheckerElement> entry : elements.entrySet()) {
      FormCheckerElement elem = entry.getValue();
      System.out.println("tabindex: " + elem.getName() + ": " + tabIndex);
      elem.setTabIndex(tabIndex);
      tabIndex = elem.getLastTabIndex();
      tabIndex++;
    }
  }

  private void checkIfFirstRun() {
    if ((FormChecker.SUBMIT_VALUE_PREFIX + id).equals(req.getParameter(FormChecker.SUBMIT_KEY))) {
      firstRun = false;
    }
  }

  public String getCompleteForm() {
    return completeForm;
  }
}
