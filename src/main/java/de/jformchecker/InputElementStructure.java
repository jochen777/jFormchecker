package de.jformchecker;

import org.apache.commons.lang3.StringUtils;

public class InputElementStructure {
  String errors;
  String label;
  String input;
  String help;
  public String getErrors() {
    return StringUtils.defaultString(errors);
  }
  public String getLabel() {
    return StringUtils.defaultString(label);
  }
  public String getInput() {
    return input;
  }
  public String getHelp() {
    return StringUtils.defaultString(help);
  }
  
  public void setErrors(String errors) {
    this.errors = errors;
  }
  public void setLabel(String label) {
    this.label = label;
  }
  public void setInput(String input) {
    this.input = input;
  }
  public void setHelp(String help) {
    this.help = help;
  }
}
