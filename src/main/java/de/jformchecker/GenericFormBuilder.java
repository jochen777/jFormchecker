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
public abstract class GenericFormBuilder {

  String requiredChars = " *";
  String addToLabel = ": ";
  String submitClass = "";


  protected abstract String getHelpTag(String helpText, FormCheckerElement elem);


  public abstract TagAttributes getLabelAttributes(FormCheckerElement elem);


  public abstract Wrapper getWrapperForInput(FormCheckerElement elem);


  public abstract TagAttributes getFormAttributes();


  public abstract void addAttributesToInputFields(Map<String, String> attribs, FormCheckerElement elem);


  // returns the HTML code that should be given out, before and after an input-element is written
  public abstract Wrapper getWrapperForElem(FormCheckerElement elem, boolean firstRun);


  public abstract String getErrors(FormCheckerElement e, boolean firstRun);


  final public String getGenericForm(String id, String formAction,
      List<FormCheckerElement> elements, boolean isMultipart, boolean firstRun, FormChecker fc) {
    // RFE: Get rid of this fc. object here. better: give FormCheckerForm
    StringBuilder formHtml = new StringBuilder();

    TagAttributes formTagAttributes = createFormTagAttributes(fc.getForm());

    if (isMultipart) {
      formHtml.append("<form name=\"" + id + "\" id=\"form_" + id + "\" action=\"" + formAction
          + "\" " + Utils.buildAttributes(formTagAttributes)
          + "  method=\"GET\" enctype=\"multipart/form-data\">\n");
    } else {
      formHtml.append("<form name=\"" + id + "\" id=\"form_" + id + "\" "
          + Utils.buildAttributes(formTagAttributes) + " action=\"" + formAction
          + "\" method=\"GET\" >\n");
    }
    formHtml.append(getSubmittedTag(id));
    if (fc.protectedAgainstCSRF) {
      formHtml.append(fc.buildCSRFTokens());
    }
    int lastTabIndex = 0;
    for (FormCheckerElement elem : elements) {
      // label
      Wrapper elementWrapper = getWrapperForElem(elem, firstRun);
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
    formHtml.append(getSubmit(lastTabIndex + 1, fc.getForm().getSubmitLabel()));
    formHtml.append("</form>\n");

    return formHtml.toString();
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


  public String getSubmit(int tabOrder, String submitLabel) {
    return "<input tabindex=\"" + tabOrder + "\" class=\"" + submitClass
        + "\" type=\"submit\" value=\"" + submitLabel + "\">\n";
  }

  public String getSubmit(String submitLabel) {
    return this.getSubmit(0, submitLabel);
  }



  public String getLabelForElement(FormCheckerElement e, TagAttributes attribs, boolean firstRun) {
    return ("<label " + Utils.buildAttributes(attribs) + " for=\"form_" + e.getName() + "\""
        + " id=\"" + e.getName() + "_label\">" + e.getDescription() + addToLabel
        + (e.isRequired() ? requiredChars : "") + "</label>");
  }

  public String getSubmittedTag(String id) {
    return "<input type=\"hidden\" name=\"" + FormChecker.SUBMIT_KEY + "\" value=\""
        + FormChecker.SUBMIT_VALUE_PREFIX + id + "\">";
  }

}
