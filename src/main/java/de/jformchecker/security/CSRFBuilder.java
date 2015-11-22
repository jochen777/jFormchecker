package de.jformchecker.security;

import java.security.SecureRandom;
import java.util.Base64;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringEscapeUtils;

public class CSRFBuilder {
  
  private final SecureRandom random = new SecureRandom();

  
  public String buildCSRFTokens(HttpServletRequest req, boolean firstRun) {
    // is firstrun - then generate a complete new token
    StringBuilder tags = new StringBuilder();
    String name = "";
    String xsrfVal = "";

    String tokenName = "tokenname";
    String tokenVal = "tokenVal";

    if (!firstRun) {
      name = req.getParameter(tokenName);
      xsrfVal = req.getParameter(tokenVal);
      if (xsrfVal == null || !xsrfVal.equals(req.getSession().getAttribute(name))) {
        throw new RuntimeException("Security Problem!");
      }

    }
    name = "token_" + Math.random();
    xsrfVal = getRandomValue();
    req.getSession().setAttribute(name, xsrfVal);


    tags.append("<input type=\"hidden\" name=\"" + tokenName + "\" value=\""
        + StringEscapeUtils.escapeHtml4(name) + "\">");
    tags.append("<input type=\"hidden\" name=\"" + tokenVal + "\" value=\""
        + StringEscapeUtils.escapeHtml4(xsrfVal) + "\">\n");
    return tags.toString();
  }

  private String getRandomValue() {
    final byte[] bytes = new byte[32];
    random.nextBytes(bytes);
    return Base64.getEncoder().encodeToString(bytes);
  }
}
