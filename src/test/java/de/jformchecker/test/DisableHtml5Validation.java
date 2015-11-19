package de.jformchecker.test;

import org.junit.Assert;
import org.junit.Test;

import de.jformchecker.FormChecker;
import de.jformchecker.FormCheckerForm;
import de.jformchecker.elements.TextInput;

public class DisableHtml5Validation {

  @Test
  public void testDisabledHtml5Validation() {
    FormChecker fc = RequestBuilders.buildFcWithEmptyRequest();
    fc.addForm(new FormCheckerForm() {
      @Override
      public void init() {
        add(TextInput.build("firstname")
            .setRequired()
            .setPreSetValue("Horst")
            .setDescription("Your firstname")
            );
        add(TextInput.build("lastname")
            .setRequired()
            .setPreSetValue("Horst")
            .setDescription("Your lastname")
            );
        disableHtml5Validation();
      }
    });
    fc.run();
    Assert.assertTrue("Form Tag should contain novalidate attribute!", (fc.getCompleteForm().contains(" novalidate ")));
  }
}
