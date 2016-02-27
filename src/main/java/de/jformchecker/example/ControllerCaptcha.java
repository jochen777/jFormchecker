package de.jformchecker.example;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.jformchecker.FormChecker;
import de.jformchecker.example.forms.ExampleForm;
import de.jformchecker.example.forms.ExampleFormCaptcha;

/**
 * Servlet implementation class Test
 */
@WebServlet("/Captcha")
public class ControllerCaptcha extends BaseController {
  private static final long serialVersionUID = 1L;


  /**
   * @see HttpServlet#HttpServlet()
   */
  public ControllerCaptcha() {
    super();
  }

  

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    initRequest(request, response);

    /****************************************
     * prepare jFormchecker
     */
    FormChecker fc =
        FormChecker.build("id", request, new ExampleFormCaptcha())
        .run();

    processResult(fc);

    putFcInTemplate(response, fc, "test.ftl");

  }

 
}
