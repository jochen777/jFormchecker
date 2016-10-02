package de.jformchecker.test.builders;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.mock.web.MockHttpServletRequest;

import de.jformchecker.FormChecker;

public class RequestBuilders {

	public static final String FC_ID = "id44";

	public static HttpServletRequest buildExampleHttpRequest() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setParameter("firstname", "Jochen Pier<bold>");
		return request;
	}

	public static HttpServletRequest buildExampleHttpRequest(Map<String, String> reqVals) {
		MockHttpServletRequest request = new MockHttpServletRequest();

		for (String key : reqVals.keySet()) {
			request.setParameter(key, reqVals.get(key));
		}
		return request;
	}

	public static HttpServletRequest buildEmptyHttpRequest() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		return request;
	}

	public static FormChecker buildFcWithEmptyRequest() {
		return new FormChecker(RequestBuilders.FC_ID, RequestBuilders.buildEmptyHttpRequest());
	}

}
