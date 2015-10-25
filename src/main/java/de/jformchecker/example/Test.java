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

import de.jformchecker.FormChecker;
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
    System.out.println("init");

  }

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    response.setContentType("text/html; charset=UTF-8");
    response.setCharacterEncoding("UTF-8");

    if (cfg == null) {
      init(request.getServletContext());
    }

    // TODO Auto-generated method stub
    ExampleForm form = new ExampleForm();
    FormChecker fc = new FormChecker("h", request);
    fc.addForm(form);
    fc.run();

    Map<String, Object> root = new HashMap<>();
    root.put("fc", fc);

    Template temp = cfg.getTemplate("test.ftl");

    /* Merge data-model with template */
    try {
      temp.process(root, response.getWriter());
    } catch (TemplateException e1) {
      e1.printStackTrace();
    }

  }
}
