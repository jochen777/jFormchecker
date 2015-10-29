package de.jformchecker.elements;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringEscapeUtils;

import de.jformchecker.Criterion;
import de.jformchecker.FormChecker;
import de.jformchecker.FormCheckerElement;
import de.jformchecker.Validator;

/**
 * Parent Element for all Formchecker elements  
 * Common stuff like validation...
 * 
 * @author jochen
 *
 */
public abstract class AbstractInput implements FormCheckerElement {

  protected String name;
  protected String value;
  protected String desc;
  protected String preSetValue = "";
  private List<Criterion> criteria = new ArrayList<>();
  boolean required;
  private int tabIndex;
  String errorMessage = "";
  boolean valid = true;
  FormChecker parent;


  public String getInputTag() {
    return getInputTag("", "");
  }

  @Override
  public void setFormChecker(FormChecker fc) {
    parent = fc;
  }

  public String getValueHtmlEncoded() {
    return StringEscapeUtils.escapeHtml4(value);
  }

  @Override
  public void init(HttpServletRequest request, boolean firstRun) {
    if (firstRun) {
      this.setValue(this.getPreSetValue());
    } else {
      this.setValue(request.getParameter(this.getName()));
      Validator v = new Validator();
      String errMsg = v.validate(this);
      if (errMsg != null) {
        this.valid = false;
        this.setErrorMessage(errMsg);
      }
    }
  }

  public FormCheckerElement setRequired() {
    required = true;
    return this;
  }

  public boolean displayLabel() {
    return true;
  }

  @Override
  public String getLabel() {
    return parent.getLabelForElement(this, "", "");
  }

  @Override
  public String getLabelParam(String style, String classes) {
    return parent.getLabelForElement(this, style, classes);
  }

  @Override
  public String getPreSetValue() {
    return preSetValue;
  }

  @Override
  public AbstractInput setPreSetValue(String preSetValue) {
    this.preSetValue = preSetValue;
    this.value = preSetValue;
    return this;
  }

  @Override
  public String getCompleteInput() {
    return getLabel() + getInputTag();
  }


  @Override
  public String getName() {
    return name;
  }

  @Override
  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }


  @Override
  public AbstractInput setDescription(String desc) {
    this.desc = desc;
    return this;
  }

  @Override
  public void changeDescription(String desc) {
    this.desc = desc;
  }


  @Override
  public String getDescription() {
    return desc;
  }

  @Override
  public boolean isValid() {
    return valid;
  }

  @Override
  public String getErrorMessage() {
    return errorMessage;
  }

  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }


  public AbstractInput setCriterias(Criterion... criteria) {
    this.criteria.addAll(Arrays.asList(criteria));
    return this;
  }

  public boolean isRequired() {
    return required;
  }

  public int getTabIndex() {
    /*
     * multiply times 10 because some form-elements may contain more than one input-element ( for
     * example the date element: It can use tabIndex*10+1, +2 to use own input-types
     */
    return tabIndex * 10;
  }

  public AbstractInput setTabIndex(int tabIndex) {
    this.tabIndex = tabIndex;
    return this;
  }

  public List<Criterion> getCriteria() {
    return criteria;
  }

}
