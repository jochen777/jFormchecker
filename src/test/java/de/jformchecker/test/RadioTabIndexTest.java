package de.jformchecker.test;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.junit.Assert;

import de.jformchecker.FormChecker;
import de.jformchecker.FormCheckerForm;
import de.jformchecker.elements.RadioInput;
import de.jformchecker.elements.SelectInput;
import de.jformchecker.test.builders.ExampleFormBuilder;
import de.jformchecker.test.builders.RequestBuilders;
import de.jformchecker.test.builders.SampleMapBuilders;

public class RadioTabIndexTest {

  @org.junit.Test
  public void test() {
    RadioInput radio = (RadioInput)RadioInput.build("radio")
        .setPossibleValues(SampleMapBuilders.generateRadioMap())
        .setDescription("Choice")
        .setTabIndex(4);
    Assert.assertTrue("Expected tabindex=4", radio.getInputTag().contains("tabindex=\"4"));
  }
  @org.junit.Test
  public void testForm() {
    Map<String, String> reqVals = new HashMap<>();
    String formId = "h";
    reqVals.put("firstname", "Jochen2");
    reqVals.put(FormChecker.SUBMIT_KEY, FormChecker.SUBMIT_VALUE_PREFIX + formId);
    HttpServletRequest request = RequestBuilders.buildExampleHttpRequest(reqVals);

    FormChecker fc = new FormChecker(formId, request);
    fc.addForm(ExampleFormBuilder.getComplexForm());
    fc.run();
    System.out.println(fc.getCompleteForm());
  }
  
  class Form extends FormCheckerForm {
    @Override
    public void init() {

      add(RadioInput
          .build("rdio")
          .setPossibleValues(SampleMapBuilders.generateRadioMap())
          .setDescription("Your Choice"));

      add(SelectInput
          .build("select")
          .setPossibleValues(SampleMapBuilders.generateSelectMap())
          .setDescription("Your Selection"));

    }
  }
}
