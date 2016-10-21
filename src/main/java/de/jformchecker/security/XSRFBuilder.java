package de.jformchecker.security;

import java.security.SecureRandom;
import java.util.Base64;

import com.coverity.security.Escape;

import de.jformchecker.request.Request;
import de.jformchecker.request.SessionGet;
import de.jformchecker.request.SessionSet;

public class XSRFBuilder {

	private final SecureRandom random = new SecureRandom();

	public String buildCSRFTokens(Request req, boolean firstRun, SessionGet sessionGet, SessionSet sessionSet) {
		// is firstrun - then generate a complete new token
		StringBuilder tags = new StringBuilder();
		String name = "";
		String xsrfVal = "";

		String tokenName = "tokenname";
		String tokenVal = "tokenVal";

		if (!firstRun) {
			name = req.getParameter(tokenName);
			xsrfVal = req.getParameter(tokenVal);
			if (xsrfVal == null || !xsrfVal.equals(sessionGet.getAttribute(name))) {
				throw new XSRFException("Security Problem!");
			}

		}
		name = "token_" + Math.random();
		xsrfVal = getRandomValue();
		sessionSet.setAttribute(name, xsrfVal);

		tags.append("<input type=\"hidden\" name=\"" + tokenName + "\" value=\"" + Escape.htmlText(name)
				+ "\">");
		tags.append("<input type=\"hidden\" name=\"" + tokenVal + "\" value=\"" + Escape.htmlText(xsrfVal)
				+ "\">\n");
		return tags.toString();
	}

	private String getRandomValue() {
		final byte[] bytes = new byte[32];
		random.nextBytes(bytes);
		return Base64.getEncoder().encodeToString(bytes);
	}
}
