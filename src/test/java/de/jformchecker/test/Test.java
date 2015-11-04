package de.jformchecker.test;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.junit.Assert;
import org.springframework.mock.web.MockHttpServletRequest;

import de.jformchecker.FormChecker;
import de.jformchecker.criteria.Criteria;
import de.jformchecker.elements.TextInput;

public class Test {

  @org.junit.Test
  public void testXSS() {
    TextInput ti = buildExampleTextInput();
    HttpServletRequest request = buildExampleHttpRequest();
    ti.init(request, false);
    String inputTag = ti.getInputTag();
    assertEquals(
        "<input  id=\"form_firstname\"  tabindex=\"0\" type=\"text\" name=\"firstname\" value=\"Jochen Pier&lt;bold&gt;\">",
        inputTag);
  }



  @org.junit.Test
  public void testPrefillText() {
    TextInput ti = buildExampleTextInput();
    HttpServletRequest request = buildEmptyHttpRequest();
    ti.init(request, true);
    String inputTag = ti.getInputTag();
    assertEquals("<input  id=\"form_firstname\"  tabindex=\"0\" type=\"text\" name=\"firstname\" value=\"Jochen\">",
        inputTag);
  }


  @org.junit.Test
  public void testPrebuildForm() {
    Map<String, String> reqVals = new HashMap<>();
    String formId = "h";
    reqVals.put("firstname", "Jochen2");
    reqVals.put(FormChecker.SUBMIT_KEY, FormChecker.SUBMIT_VALUE_PREFIX + formId);
    HttpServletRequest request = buildExampleHttpRequest(reqVals);

    ExampleFormTest form = new ExampleFormTest();
    FormChecker fc = new FormChecker(formId, request);
    fc.addForm(form);
    fc.run();
    assertEquals("Jochen2", fc.getValue("firstname"));
    Assert.assertTrue("FC should be not valid", !fc.isValid());
    // todo: Check if valid!

  }



  @org.junit.Test
  public void testPrebuildForm2() {
    Map<String, String> reqVals = new HashMap<>();
    String firstname = "Max";
    String formId = "h";
    reqVals.put("firstname", firstname);
    reqVals.put("lastname", "Pan");
    reqVals.put("description", "eine Beschreibung");

    reqVals.put(FormChecker.SUBMIT_KEY, FormChecker.SUBMIT_VALUE_PREFIX + formId);

    HttpServletRequest request = buildExampleHttpRequest(reqVals);

    ExampleFormTest form = new ExampleFormTest();
    FormChecker fc = new FormChecker(formId, request);
    fc.addForm(form);
    fc.run();
    assertEquals(firstname, fc.getValue("firstname"));
    Assert.assertTrue("FC should be valid", fc.isValid());

  }



  @org.junit.Test
  public void testRequired() {
    //fail("Not yet implemented");
  }

  @org.junit.Test
  public void testForbiddenSelect() {
    //fail("Not yet implemented");
  }



  private HttpServletRequest buildExampleHttpRequest() {
    MockHttpServletRequest request = new MockHttpServletRequest();
    request.setParameter("firstname", "Jochen Pier<bold>");
    return request;
  }

  private HttpServletRequest buildExampleHttpRequest(Map<String, String> reqVals) {
    MockHttpServletRequest request = new MockHttpServletRequest();

    for (String key : reqVals.keySet()) {
      request.setParameter(key, reqVals.get(key));
    }
    return request;
  }


  private HttpServletRequest buildEmptyHttpRequest() {
    MockHttpServletRequest request = new MockHttpServletRequest();
    return request;
  }


  private TextInput buildExampleTextInput() {
    TextInput ti = (TextInput) TextInput.build("firstname").setDescription("Vorname")
        .setPreSetValue("Jochen").setCriterias(Criteria.accept("Peter"));
    return ti;
  }

}
