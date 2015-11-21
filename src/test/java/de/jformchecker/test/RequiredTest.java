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
    fc.addForm(ExampleFormBuilder.getVerySimpleForm());
    fc.run();
    Assert.assertTrue("Form should contain a required attribute!", (fc.getCompleteForm().contains(" required ")));
  }

  
}
