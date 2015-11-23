package de.jformchecker;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringEscapeUtils;

import de.jformchecker.criteria.Criteria;
import de.jformchecker.criteria.MaxLength;
import de.jformchecker.themes.BasicFormBuilder;
import de.jformchecker.validator.DefaultValidator;
import de.jformchecker.validator.Validator;

/**
 * FormChecker handles the initialisation, error- and submit status. This object should be stored to
 * be accessed from the template-system.
 */
public class FormChecker {
  HttpServletRequest req;
  boolean firstRun = true;
  boolean isMultipart = false;
  boolean isValid = true;
  FormCheckerForm form = null;
  Validator validator = new DefaultValidator();

  boolean protectedAgainstCSRF = false; // TBD: Default no protection, because the normal case is
                                        // not logged in?!?
  String completeForm;
  int defaultMaxLenElements = 1000; // override this for each element, if you want longer vals!

  GenericFormBuilder formBuilder = new BasicFormBuilder();
  String id;
  private String formAction = "#";

  public static final String SUBMIT_KEY = "submitted";
  public static final String SUBMIT_VALUE_PREFIX = "FORMCHECKER_";

  public FormChecker(String _id, HttpServletRequest _req) {
    id = _id;
    req = _req;
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

  public void setValidator(Validator validator) {
    this.validator = validator;
  }

  
  public boolean isValid() {
    return isValid;
  }

  public boolean isValidAndNotFirstRun() {
    return isValid && !firstRun;
  }

  public FormCheckerForm getForm() {
    return form;
  }


  public GenericFormBuilder getFormBuilder() {
    return formBuilder;
  }

  public FormChecker setFormBuilder(GenericFormBuilder formBuilder) {
    this.formBuilder = formBuilder;
    return this;
  }

  public static String getHelpBlockId(FormCheckerElement elem) {
    return "helpBlock_" + elem.getName();
  }


  public String getValue(String elementName) {
    return form.getElement(elementName).getValue();
  }

  public String getSubmitTag() {
    return formBuilder.getSubmittedTag(id);
  }

  public String getLabelTag(String elementName) {
    return formBuilder.getLabelForElement(form.getElement(elementName), new TagAttributes(), firstRun);
  }

  public String getLabelTag(String elementName, Map<String, String> map) {
    return formBuilder.getLabelForElement(form.getElement(elementName), new TagAttributes(map),
        firstRun);
  }

  public void setFormAction(String formAction) {
    this.formAction = formAction;
  }


  private void prepareElement(FormCheckerElement element) {
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
  }

  public void addForm(FormCheckerForm form) {
    for (FormCheckerElement element : form.getElements()) {
      prepareElement(element);
    }
    this.form = form;
  }

  private String getGenericForm() {
    return formBuilder.generateGenericForm(id, formAction, form.elements, isMultipart, firstRun, this, req);
  }

  //TODO: is neeeded?
  public String getLabelForElement(FormCheckerElement e, Map<String, String> attribs) {
    return this.formBuilder.getLabelForElement(e, new TagAttributes(attribs), firstRun);
  }

  public FormChecker run() {

    sortTabIndexes();

    checkIfFirstRun();



    // process and validate each field
    for(FormCheckerElement elem : form.getElements()){
      elem.init(req, firstRun, validator);
      if (!elem.isValid()) {
        isValid = false;
      }
    }
    
    // validate the complete form
    if (form != null) {
      for (FormValidator formValidator : form.getValidators()) {
        formValidator.validate(form);
      }
    }
    
    
    
    // build complete Form here!
    completeForm = this.getGenericForm();
    return this;
  }

  // resort tab-indexes
  private void sortTabIndexes() {
    int tabIndex = 100;
    for(FormCheckerElement elem : form.getElements()){
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
