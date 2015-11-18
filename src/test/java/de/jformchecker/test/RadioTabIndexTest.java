package de.jformchecker.test;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.junit.Assert;

import de.jformchecker.FormChecker;
import de.jformchecker.FormCheckerForm;
import de.jformchecker.criteria.Criteria;
import de.jformchecker.elements.RadioInput;
import de.jformchecker.elements.SelectInput;
import de.jformchecker.elements.TextInput;

public class RadioTabIndexTest {

  @org.junit.Test
  public void test() {
    RadioInput radio = (RadioInput)RadioInput.build("radio")
        .setPossibleValues(SampleMapGenerators.generateRadioMap())
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

    ExampleFormTest form = new ExampleFormTest();
    FormChecker fc = new FormChecker(formId, request);
    fc.addForm(new Form());
    fc.run();
    System.out.println(fc.getCompleteForm());
  }
  
  class Form extends FormCheckerForm {
    @Override
    public void init() {

      add(RadioInput
          .build("rdio")
          .setPossibleValues(SampleMapGenerators.generateRadioMap())
          .setDescription("Your Choice"));

      add(SelectInput
          .build("select")
          .setPossibleValues(SampleMapGenerators.generateSelectMap())
          .setDescription("Your Selection"));

    }
  }
}
