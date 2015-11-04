package de.jformchecker.elements;

import java.util.Map;

import de.jformchecker.FormCheckerElement;

public class ButtonInput extends AbstractInput implements FormCheckerElement{

  public static ButtonInput build(String name) {
    ButtonInput i = new ButtonInput();
    i.name = name;
    return i;
  }
  
  @Override
  public boolean displayLabel() {
    return false;
  }

  
  @Override
  public String getInputTag(Map<String, String> attributes) {
    return "<button type=\"submit\" name=\""+name+"\" value=\""+getPreSetValue()+ getTabIndexTag() + "\">"+getDescription()+
        "</button><br/>";
  }

}
