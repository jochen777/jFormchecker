package de.jformchecker.test;

import org.junit.Assert;
import org.junit.Test;

import de.jformchecker.FormChecker;
import de.jformchecker.FormCheckerForm;
import de.jformchecker.elements.TextInput;

// Tests for setting stuff required
public class RequiredTest {
  
  @Test
  public void testRequiredField() {
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
      }
    });
    fc.run();
    Assert.assertTrue("Form should contain a required attribute!", (fc.getCompleteForm().contains(" required ")));
  }

  
}
