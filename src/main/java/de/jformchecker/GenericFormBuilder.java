package de.jformchecker;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

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
      List< FormCheckerElement> elements, boolean isMultipart, boolean firstRun,
      FormChecker fc) {
    StringBuilder formHtml = new StringBuilder();
    
    TagAttributes formTagAttributes = createFormTagAttributes(fc.getForm()); 
    
    if (isMultipart) {
      formHtml.append("<form name=\"" + id + "\" id=\"form_" + id + "\" action=\"" + formAction
          + "\" "+Utils.buildAttributes(formTagAttributes)+"  method=\"GET\" enctype=\"multipart/form-data\">\n");
    } else {
      formHtml.append("<form name=\"" + id + "\" id=\"form_" + id + "\" "+
          Utils.buildAttributes(formTagAttributes)+" action=\"" + formAction
          + "\" method=\"GET\" >\n");
    }
    formHtml.append(getSubmittedTag(id));
    if (fc.protectedAgainstCSRF) {
      formHtml.append(fc.buildCSRFTokens());
    }
    int lastTabIndex = 0;
    for(FormCheckerElement elem : elements){
      // label
      Wrapper elementWrapper = getWrapperForElem(elem);
      formHtml.append(elementWrapper.start);
      formHtml.append(getErrors(elem, firstRun));
      boolean displayLabel = !StringUtils.isEmpty(elem.getDescription()); 
      if (displayLabel) {
        formHtml.append(getLabelForElement(elem, getLabelAttributes(elem), firstRun)).append("\n");
      }
      // input tag
      Map<String, String> attribs = new LinkedHashMap<>();
      addAttributesToInputFields(attribs, elem);
      Wrapper inputWrapper = getWrapperForInput(elem);
      formHtml.append(inputWrapper.start);
      formHtml.append(elem.getInputTag(attribs));
      // help tag
      if (!StringUtils.isEmpty(elem.getHelpText())) {
        formHtml.append(getHelpTag(elem.getHelpText(), elem));
      }
      
      if (displayLabel) {
        formHtml.append("\n<br>"); // only append nl, if something was given
                               // out
      }
      formHtml.append(inputWrapper.end);
      formHtml.append(elementWrapper.end);
      lastTabIndex = elem.getLastTabIndex();
    }
    formHtml.append(getSubmit(lastTabIndex + 1));
    formHtml.append("</form>\n");

    return formHtml.toString();
  }


  protected String getHelpTag(String helpText, FormCheckerElement elem) {
    return "<span id=\"" +GenericFormBuilder.getHelpBlockId(elem) + "\" class=\"help-block\">" +
        helpText + 
        "</span>";
  }

  public static String getHelpBlockId(FormCheckerElement elem) {
    // RFE: Add FC-ID here, so we can have more than one form on one page.
    return "helpBlock_" + elem.getName(); 
  }

  private TagAttributes createFormTagAttributes(FormCheckerForm form) {
    TagAttributes atribs = new TagAttributes();
    atribs.add(getFormAttributes());
    atribs.add(form.getFormTagAttributes());
    if (!form.html5Validation) {
      atribs.addToAttribute("novalidate", "");
    }
    return atribs;
  }


  public TagAttributes getLabelAttributes(FormCheckerElement elem) {
    TagAttributes attributes = new TagAttributes();
    attributes.put("class", "col-sm-2 control-label");
    return attributes;
  }


  public Wrapper getWrapperForInput(FormCheckerElement elem) {
    return new Wrapper("<div class=\"col-sm-10\">", "</div>");
  }


  public TagAttributes getFormAttributes() {
    TagAttributes attributes = new TagAttributes();
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

  public String getLabelForElement(FormCheckerElement e, TagAttributes attribs,
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
