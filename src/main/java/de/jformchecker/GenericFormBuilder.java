package de.jformchecker;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Builds: a generic form the label-elements
 * 
 * @author jochen
 *
 */
public class GenericFormBuilder {

  String errStyle = "error";
  String okStyle = "";
  String requiredChars = " *";
  String addToLabel = ": ";
  String labelStyle = "";
  String submitLabel = "OK";
  String submitClass = "";

  final public String getGenericForm(String id, String formAction,
      Map<String, FormCheckerElement> elements, boolean isMultipart, boolean firstRun,
      FormChecker fc) {
    StringBuilder form = new StringBuilder();
    if (isMultipart) {
      form.append("<form name=\"" + id + "\" id=\"form_" + id + "\" action=\"" + formAction
          + "\" method=\"GET\" enctype=\"multipart/form-data\">\n");
    } else {
      form.append("<form name=\"" + id + "\" id=\"form_" + id + "\" "+
          Utils.buildAttributes(getFormAttributes())+" action=\"" + formAction
          + "\" method=\"GET\" >\n");
    }
    form.append(getSubmittedTag(id));
    if (fc.protectedAgainstCSRF) {
      form.append(fc.buildCSRFTokens());
    }
    int lastTabIndex = 0;
    for (String key : elements.keySet()) {
      // label
      FormCheckerElement elem = elements.get(key);
      Wrapper elementWrapper = getWrapperForElem(elem);
      form.append(elementWrapper.start);
      form.append(getErrors(elem, firstRun));
      if (elem.displayLabel()) {
        form.append(getLabelForElement(elem, getLabelAttributes(elem), firstRun)).append("\n");
      }
      // input tag
      Map<String, String> attribs = new LinkedHashMap<>();
      addAttributesToInputFields(attribs, elem);
      Wrapper inputWrapper = getWrapperForInput(elem);
      form.append(inputWrapper.start);
      form.append(elem.getInputTag(attribs));
      if (elem.displayLabel()) {
        form.append("\n<br>"); // only append nl, if something was given
                               // out
      }
      form.append(inputWrapper.end);
      form.append(elementWrapper.end);
      lastTabIndex = elem.getLastTabIndex();
    }
    form.append(getSubmit(lastTabIndex + 1));
    form.append("</form>\n");

    return form.toString();
  }


  public TagAtrributes getLabelAttributes(FormCheckerElement elem) {
    TagAtrributes attributes = new TagAtrributes();
    attributes.put("class", "col-sm-2 control-label");
    return attributes;
  }


  public Wrapper getWrapperForInput(FormCheckerElement elem) {
    return new Wrapper("<div class=\"col-sm-10\">", "</div>");
  }


  public TagAtrributes getFormAttributes() {
    TagAtrributes attributes = new TagAtrributes();
    attributes.put("class", "form-horizontal");
    return attributes;
  }

  public void addAttributesToInputFields(Map<String, String> attribs, FormCheckerElement elem) {
    attribs.put("class", "form-control");
  }


  // returns the HTML code that should be given out, before and after an input-element is written
  public Wrapper getWrapperForElem(FormCheckerElement elem) {
    return new Wrapper("<div class=\"form-group\">", "</div>");
  }


  public String getSubmit(int tabOrder) {
    return "<input tabindex=\"" + tabOrder + "\" class=\"" + submitClass
        + "\" type=\"submit\" value=\"" + submitLabel + "\">\n";
  }

  public String getSubmit() {
    return this.getSubmit(0);
  }



  public String getErrors(FormCheckerElement e, boolean firstRun) {
    if (!firstRun && !e.isValid()) {
      return ("Problem: " + e.getErrorMessage() + "!!<br>");
    }
    return "";
  }

  public String getLabelForElement(FormCheckerElement e, TagAtrributes attribs,
      boolean firstRun) {

    
    String statusClassToAdd = errStyle;
    if (firstRun || e.isValid()) {
      statusClassToAdd = okStyle;
    } 

    attribs.addToAttribute("class", statusClassToAdd);
    
    return ("<label "+Utils.buildAttributes(attribs)+" for=\"form_" + e.getName() + "\""
        + " id=\"" + e.getName() + "_label\">" + e.getDescription() + addToLabel
       + (e.isRequired() ? requiredChars : "") + "</label>");
  }

  public String getSubmittedTag(String id) {
    return "<input type=\"hidden\" name=\"" + FormChecker.SUBMIT_KEY + "\" value=\""
        + FormChecker.SUBMIT_VALUE_PREFIX + id + "\">";
  }

}
