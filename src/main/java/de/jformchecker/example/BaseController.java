package de.jformchecker.example;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.jformchecker.FormChecker;
import de.jformchecker.Utils;
import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.TemplateNotFoundException;

public abstract class BaseController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  Configuration cfg;

  private void init(ServletContext context) {
    cfg = new Configuration(Configuration.VERSION_2_3_22);
    cfg.setServletContextForTemplateLoading(context, "/");
    cfg.setDefaultEncoding("UTF-8");
    cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
    cfg.setTemplateUpdateDelayMilliseconds(4);
  }

  /* Merge data-model with template */
  public final void putFcInTemplate(HttpServletResponse response, FormChecker fc, String templateName)
      throws TemplateNotFoundException, MalformedTemplateNameException, ParseException,
      IOException {
    try {
    
      Map<String, Object> root = new HashMap<>();

      root.put("fc", fc);
      Template temp = cfg.getTemplate(templateName);
      temp.process(root, response.getWriter());

    } catch (TemplateException e1) {
      e1.printStackTrace();
    }
  }
  
  

  protected void processResult(FormChecker fc) {
    if (fc.isValid()) {
      ExampleBean bean = new ExampleBean();
      Utils.fillBean(fc.getForm().getElements(), bean);
      System.out.println("bean:" + bean);
      System.out.println("--------------");
      System.out.println(Utils.getDebugOutput(fc.getForm().getElementsAsMap()));
    }
  }

  protected void initRequest(HttpServletRequest request, HttpServletResponse response) {
    response.setContentType("text/html; charset=UTF-8");
    response.setCharacterEncoding("UTF-8");

    if (cfg == null) {
      init(request.getServletContext());
    }
  }
}