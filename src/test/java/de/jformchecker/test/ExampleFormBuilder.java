package de.jformchecker.test;

import de.jformchecker.FormCheckerForm;
import de.jformchecker.elements.TextInput;

public class ExampleFormBuilder {
  public static FormCheckerForm getVerySimpleForm() {
    return new FormCheckerForm() {
      @Override
      public void init() {
        add(TextInput.build("firstname")
            .setRequired()
            .setPreSetValue("Horst")
            .setHelpText("My Helptext")
            .setDescription("Your firstname")
            );
      }
    };
  }

  public static FormCheckerForm getVerySimpleFormWithoutHelp() {
    return new FormCheckerForm() {
      @Override
      public void init() {
        add(TextInput.build("firstname")
            .setRequired()
            .setPreSetValue("Horst")
            .setDescription("Your firstname")
            );
      }
    };
  }


}
