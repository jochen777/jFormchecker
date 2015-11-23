package de.jformchecker.example;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.jformchecker.FormChecker;

@WebServlet("/ajax_receive")
public class ControllerAjaxReceive extends BaseController{
  private static final long serialVersionUID = 1L;


  /**
   * @see HttpServlet#HttpServlet()
   */
  public ControllerAjaxReceive() {
    super();
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    initRequest(request, response);

    FormChecker fc =
        FormChecker.build("id", request, new ExampleFormShort());
    fc.run();
    if (fc.isValidAndNotFirstRun()) {        
      response.setStatus(HttpServletResponse.SC_OK);
      response.getWriter().write("{ \"message\": \""+"OK"+"\" }");
      response.getWriter().flush();
      response.getWriter().close();
    } else {
      response.setStatus(HttpServletResponse.SC_OK);
      response.getWriter().write("{ \"message\": \""+"NOK"+"\" }");
      response.getWriter().flush();
      response.getWriter().close();
    }
  }

}
