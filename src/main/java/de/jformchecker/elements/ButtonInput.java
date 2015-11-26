package de.jformchecker.elements;

import java.util.Map;

import de.jformchecker.FormCheckerElement;

public class ButtonInput extends AbstractInput implements FormCheckerElement{

  String buttonText = "";
  
  public static ButtonInput build(String name) {
    ButtonInput i = new ButtonInput();
    i.name = name;
    return i;
  }
  
  public ButtonInput setButtonText(String buttonText) {
    this.buttonText = buttonText;
    return this;
  }
  
  @Override
  public String getInputTag(Map<String, String> attributes) {
    return "<button type=\"submit\" name=\""+name+"\" value=\""+getPreSetValue()+"\" "+ getTabIndexTag() + ">"+buttonText+
        "</button><br/>\n";
  }

}
