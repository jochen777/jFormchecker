package de.jformchecker.test.builders;

import java.util.Map;

import de.jformchecker.FormChecker;
import de.jformchecker.request.Request;
import de.jformchecker.request.SampleRequest;

public class RequestBuilders {

	public static final String FC_ID = "id44";

	public static Request buildExampleHttpRequest() {
		SampleRequest request = new SampleRequest();
		request.setParameter("firstname", "Jochen Pier<bold>");
		return request;
	}

	public static Request buildExampleHttpRequest(Map<String, String> reqVals) {
		SampleRequest request = new SampleRequest();

		for (String key : reqVals.keySet()) {
			request.setParameter(key, reqVals.get(key));
		}
		return request;
	}

	public static Request buildExampleHttpRequestWithFirstRun(Map<String, String> reqVals) {
		SampleRequest request = (SampleRequest)buildFirstRunEmptyHttpRequest();
		
		for (String key : reqVals.keySet()) {
			request.setParameter(key, reqVals.get(key));
		}
		return request;
	}

	
	public static Request buildRequestwithFirstRun(String key, String val) {
		SampleRequest r = (SampleRequest)buildFirstRunEmptyHttpRequest();
		r.setParameter(key, val);
		return r;
	}

	public static Request buildEmptyHttpRequest() {
		SampleRequest request = new SampleRequest();
		return request;
	}

	public static Request buildFirstRunEmptyHttpRequest() {
		SampleRequest request = new SampleRequest();
		request.setParameter(FormChecker.SUBMIT_KEY, FormChecker.SUBMIT_VALUE_PREFIX + RequestBuilders.FC_ID);
		return request;
	}

	public static FormChecker buildFcFirstrunRequest() {
		return new FormChecker(RequestBuilders.FC_ID, RequestBuilders.buildFirstRunEmptyHttpRequest());
	}

	
	public static FormChecker buildFcWithEmptyRequest() {
		return new FormChecker(RequestBuilders.FC_ID, RequestBuilders.buildEmptyHttpRequest());
	}

}
