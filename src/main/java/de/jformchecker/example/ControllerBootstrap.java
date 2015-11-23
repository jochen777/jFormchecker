package de.jformchecker.example;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.jformchecker.FormChecker;
import de.jformchecker.themes.TwoColumnBootstrapFormBuilder;
import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

/**
 * Servlet implementation class Test
 */
@WebServlet("/Test")
public class ControllerBootstrap extends BaseController {
  private static final long serialVersionUID = 1L;


  /**
   * @see HttpServlet#HttpServlet()
   */
  public ControllerBootstrap() {
    super();
  }

  

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    initRequest(request, response);

    /****************************************
     * prepare jFormchecker
     */
    FormChecker fc =
        FormChecker.build("id", request, new ExampleForm()).setProtectAgainstCSRF()
        .setFormBuilder(new TwoColumnBootstrapFormBuilder())
        .run();

    processResult(fc);



    putFcInTemplate(response, fc, "test.ftl");

  }

 
}
