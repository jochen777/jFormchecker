package de.jformchecker.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import de.jformchecker.FormChecker;
import de.jformchecker.themes.TwoColumnBootstrapFormBuilder;

/**
 * Servlet implementation class Test
 */
@WebServlet("/upload")
@MultipartConfig(location="/tmp", fileSizeThreshold=1024*1024, 
maxFileSize=1024*1024*5, maxRequestSize=1024*1024*5*5)
public class ControllerFileUpload extends BaseController {
  private static final long serialVersionUID = 1L;


  /**
   * @see HttpServlet#HttpServlet()
   */
  public ControllerFileUpload() {
    super();
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    initRequest(request, response);

    final FormChecker formChecker =
        FormChecker.build("id", request, new ExampleFormUpload())
        .setFormBuilder(new TwoColumnBootstrapFormBuilder())
        .run();

    putFcInTemplate(response, formChecker, "test.ftl");
  }
  
  
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    initRequest(request, response);

    
    FormChecker fc =
        FormChecker.build("id", request, 
            new ExampleFormUpload())
        .setFormBuilder(new TwoColumnBootstrapFormBuilder())
        .run();

    processResult(fc);
    if (fc.isValidAndNotFirstRun()) {
      processRequest(request, response);
    }


    putFcInTemplate(response, fc, "test.ftl");

  }

  // example taken from: https://docs.oracle.com/javaee/6/tutorial/doc/glraq.html
  protected void processRequest(HttpServletRequest request,
      HttpServletResponse response)
      throws ServletException, IOException {
  response.setContentType("text/html;charset=UTF-8");

  // Create path components to save the file
  final String path = "/Users/jpier/Development/sandbox/uploads";   // specify your upload path.
  final Part filePart = request.getPart("upload");
  final String fileName = getFileName(filePart);

  OutputStream out = null;
  InputStream filecontent = null;
  final PrintWriter writer = response.getWriter();

  try {
      out = new FileOutputStream(new File(path + File.separator
              + fileName));
      filecontent = filePart.getInputStream();

      int read = 0;
      final byte[] bytes = new byte[1024];

      while ((read = filecontent.read(bytes)) != -1) {
          out.write(bytes, 0, read);
      }
      writer.println("New file " + fileName + " created at " + path);
      System.err.println("uploaded");
  } catch (FileNotFoundException fne) {
      writer.println("You either did not specify a file to upload or are "
              + "trying to upload a file to a protected or nonexistent "
              + "location.");
      writer.println("<br/> ERROR: " + fne.getMessage());

      System.err.println("Problems during file upload. Error: {0}" 
              + fne.getMessage());
  } finally {
      if (out != null) {
          out.close();
      }
      if (filecontent != null) {
          filecontent.close();
      }
      if (writer != null) {
          writer.close();
      }
  }
}

private String getFileName(final Part part) {
  for (String content : part.getHeader("content-disposition").split(";")) {
      if (content.trim().startsWith("filename")) {
          return content.substring(
                  content.indexOf('=') + 1).trim().replace("\"", "");
      }
  }
  return null;
}
  
  
}
