package de.jformchecker;

import java.util.Map;

/**
 * Builds: 
 * a generic form 
 * the label-elements
 * @author jochen
 *
 */
public class GenericFormBuilder {

  String errStyle = "error";
  String okStyle = "";
  String formStyle = "";
  String requiredChars = " *";
  String addToLabel = ": ";
  String labelStyle = "";
  String submitLabel = "OK";
  String submitClass = "";

  public String getGenericForm(String id, String formAction,
      Map<String, FormCheckerElement> elements, boolean isMultipart, boolean firstRun, FormChecker fc) {
    StringBuilder form = new StringBuilder();
    if (isMultipart) {
      form.append("<form name=\"" + id + "\" id=\"form_" + id + "\" action=\"" + formAction
          + "\" method=\"GET\" enctype=\"multipart/form-data\">\n");
    } else {
      form.append("<form name=\"" + id + "\" id=\"form_" + id + "\" action=\"" + formAction
          + "\" method=\"GET\" >\n");
    }
    form.append(getSubmittedTag(id));
    if (fc.protectedAgainstCSRF) { 
      form.append(fc.buildCSRFTokens());
    }
    
    int tabOrder = 0;
    for (String key : elements.keySet()) {
      // label
      FormCheckerElement elem = elements.get(key);
      /*
       * set tabIndex if not already-provided Attention: This may mix an order where a form has some
       * elements with order and some without order.
       */
      if (elem.getTabIndex() == 0) {
        elem.setTabIndex(tabOrder);
      }
      if (elem.displayLabel()) {
        form.append(getLabelForElement(elem, "", "", firstRun));
      }
      // input tag
      form.append(elem.getInputTag("","form-control"));
      if (elem.displayLabel()) {
        form.append("\n<br>"); // only append nl, if something was given
                               // out
      }
      tabOrder++;
    }
    form.append(getSubmit(tabOrder));
    form.append("</form>\n");

    return form.toString();
  }

  public String getSubmit(int tabOrder) {
    return "<input tabindex=\"" + tabOrder + "\" class=\"" + submitClass
        + "\" type=\"submit\" value=\"" + submitLabel + "\">\n";
  }

  public String getSubmit() {
    return this.getSubmit(0);
  }


  // 1 = ohne div
  // 2 = divs umn label und element
  private int grouping = 1;
  public final static int GROUPING_NO = 1;
  public final static int GROUPING_DIV = 2;

  public int getGrouping() {
    return grouping;
  }

  public void setGrouping(int grouping) {
    this.grouping = grouping;
  }

  public String getLabelForElement(FormCheckerElement e, String tagAddition, String classes,
      boolean firstRun) {

    StringBuffer buf = new StringBuffer();
    String paramClasses = new String();

    if (classes != null && !"".equals(classes))
      paramClasses += " " + classes;

    // no label for hidden elements
    // if (e.getClass()==Hidden.class) return "";

    // buf.append("\n");
    // prepend <div> if enabled
    if (getGrouping() == GenericFormBuilder.GROUPING_DIV)
      buf.append("<div id=\"fc-" + e.getName() + "-label-container\">");

    if (firstRun || e.isValid()) {
      /*
       * if (classes!=null) okStyle+=" "+classes;
       */
      buf.append("<label class=\"" + okStyle + paramClasses + "\" for=\"" + e.getName() + "\""
          + tagAddition + " id=\"" + e.getName() + "_label\">" + e.getDescription() + addToLabel
          + (e.isRequired() ? requiredChars : "") + "</label>");
    } else {
      /*
       * if (classes!=null) errStyle+=" "+classes;
       */
      buf.append("Problem: " + e.getErrorMessage() + "!!<br>");
      buf.append("<label class=\"" + errStyle + paramClasses + "\"  for=\"" + e.getName() + "\""
          + tagAddition + " id=\"" + e.getName() + "_label\">" + e.getDescription() + addToLabel
          + (e.isRequired() ? requiredChars : "") + "</label>");
    }
    // append </div> if enabled
    if (getGrouping() == GenericFormBuilder.GROUPING_DIV)
      buf.append("</div>");

    // buf.append("\n");

    return buf.toString();
  }

  public String getSubmittedTag(String id) {
    return "<input type=\"hidden\" name=\"" + FormChecker.SUBMIT_KEY + "\" value=\""
        + FormChecker.SUBMIT_VALUE_PREFIX + id + "\">";
  }

}
