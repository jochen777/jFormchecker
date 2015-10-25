package de.jformchecker;

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

  // get the value that the user entered
  public String getValueHtmlEncoded();

  public void setValue(String value);

  public String getPreSetValue();

  public FormCheckerElement setPreSetValue(String value);

  public FormCheckerElement setTabIndex(int tabIndex);

  public int getTabIndex();

  public boolean displayLabel();

  // set the label
  public FormCheckerElement setDescription(String desc);

  public void changeDescription(String desc);

  public String getDescription();

  // returns true if element is valid
  public boolean isValid();

  public void init(HttpServletRequest request, boolean firstrun);

  public String getErrorMessage();

  public void setErrorMessage(String errorMessage);

  public String getInputTag();

  public String getInputTag(String additionalTags, String classes);

  public boolean isRequired();

  public FormCheckerElement setRequired();

  public Criterion[] getCriteria();

  public void setFormChecker(FormChecker fc);

  public String getLabel();

  public String getLabelParam(String additionalTags, String classes);

  public String getCompleteInput(); // RFE: Perhaps toString makes this even
                                    // more convenient?!

}
