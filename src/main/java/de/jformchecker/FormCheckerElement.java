package de.jformchecker;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * Interface for Input-Elements handled by formchecker
 * 
 * @author jochen
 *
 */
public interface FormCheckerElement {

  // RFE: check, if some methods can be protected

  // get internal name of this input-element
  public String getName();

  // get the value that the user entered
  public String getValue();

  // get the value that the user entered, but html-encoded
  public String getValueHtmlEncoded();

  public void setValue(String value);

  public String getPreSetValue();

  // set an initial value to the element, before the user edited it.
  public FormCheckerElement setPreSetValue(String value);

  
  public FormCheckerElement setTabIndex(int tabIndex);

  public int getTabIndex();
  
  public int getLastTabIndex();

  // set the test in the label (builder pattern)
  public FormCheckerElement setDescription(String desc);

  // as "setDescription" but does not return anything (no builder pattern)
  public void changeDescription(String desc);

  public String getDescription();

  // returns true if element is valid
  public boolean isValid();
  
  public void setInvalid();

  // inits the value with the current http-reques
  public void init(HttpServletRequest request, boolean firstrun);

  // if the element is not valid, return an error-message
  public String getErrorMessage();

  // override the "normal" error messages in case you need something special
  public void setErrorMessage(String errorMessage);

  public String getInputTag();

  public String getInputTag(Map<String, String> attributes);

  public boolean isRequired();

  public FormCheckerElement setRequired();

  public List<Criterion> getCriteria();

  public void setFormChecker(FormChecker fc);

  // returns the complete label-html tag
  public String getLabel();

  public String getLabelParam(String additionalTags, String classes);

  // returns the label-html and the input-html
  public String getCompleteInput(); // RFE: Perhaps toString makes this even
                                    // more convenient?!

}
