package de.jformchecker.elements;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

  protected String buildAttributes(Map<String, String> attributes) {
    StringBuilder attrStr = new StringBuilder();
    for (String attribute : attributes.keySet()) {
      attrStr.append(attribute).append("=\"").append(attributes.get(attribute)).append("\" ");
    }
    return attrStr.toString();
  }

  public String getInputTag() {
    return getInputTag(new HashMap<>());
  }

  // return highest tabindex of this element
  public int getLastTabIndex() {
    return tabIndex;
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
  
  protected String getElementId() {
    return " id=\"form_" + name+"\" ";
  }

  public int getTabIndex() {
    return  tabIndex;
  }
  
  public String getTabIndexTag() {
    return "tabindex=\"" + getTabIndex() + "\" ";
  }

  public AbstractInput setTabIndex(int tabIndex) {
    this.tabIndex = tabIndex;
    return this;
  }

  public List<Criterion> getCriteria() {
    return criteria;
  }

}
