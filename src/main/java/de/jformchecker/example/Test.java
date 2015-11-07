package de.jformchecker.example;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.jformchecker.BasicBootstrapFormBuilder;
import de.jformchecker.FormChecker;
import de.jformchecker.Utils;
import de.jformchecker.elements.DateInput;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

/**
 * Servlet implementation class Test
 */
@WebServlet("/Test")
public class Test extends HttpServlet {
  private static final long serialVersionUID = 1L;

  Configuration cfg;

  /**
   * @see HttpServlet#HttpServlet()
   */
  public Test() {
    super();
  }

  private void init(ServletContext context) {
    cfg = new Configuration(Configuration.VERSION_2_3_22);
    cfg.setServletContextForTemplateLoading(context, "/");
    cfg.setDefaultEncoding("UTF-8");
    cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
    cfg.setTemplateUpdateDelayMilliseconds(4);
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    initRequest(request, response);

    /****************************************
     * prepare jFormchecker
     */
    FormChecker fc =
        FormChecker.build("id", request, new ExampleForm()).setProtectAgainstCSRF()
        .setFormBuilder(new BasicBootstrapFormBuilder())
        .run();

    processResult(fc);



    /* Merge data-model with template */
    try {
    
      Map<String, Object> root = new HashMap<>();

      /****************************************
       * put jFormChecker in the map for the template processing
       */
      root.put("fc", fc);
      Template temp = cfg.getTemplate("test.ftl");
      temp.process(root, response.getWriter());

    } catch (TemplateException e1) {
      e1.printStackTrace();
    }

  }
  
  

  private void processResult(FormChecker fc) {
    if (fc.isValid()) {
      ExampleBean bean = new ExampleBean();
      Utils.fillBean(fc.getElements(), bean);
      System.out.println("bean:" + bean);
      System.out.println(((DateInput)fc.getElements().get("date")).getDateValue());
      System.out.println("--------------");
      System.out.println(Utils.getDebugOutput(fc.getElements()));
    }
  }

  private void initRequest(HttpServletRequest request, HttpServletResponse response) {
    response.setContentType("text/html; charset=UTF-8");
    response.setCharacterEncoding("UTF-8");

    if (cfg == null) {
      init(request.getServletContext());
    }
  }
}
