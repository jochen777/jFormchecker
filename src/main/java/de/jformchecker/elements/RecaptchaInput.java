package de.jformchecker.elements;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

import de.jformchecker.FormCheckerElement;
import de.jformchecker.validator.Validator;

/**
 * Captcha for distinugishing between human and robots.
 * 
 * 
 * Based on the recaptcha by google.
 * see: https://developers.google.com/recaptcha/intro
 * 
 * @author jochen
 *
 */
public class RecaptchaInput extends AbstractInput implements FormCheckerElement {

	// Site specific key. Obtain that from google. Will be shown in html
	String siteKey = null;
	// Secret string. This will be send by https to google along with the userinput
	String secret = null;
	
	
  public static RecaptchaInput build(String name) {
    RecaptchaInput ci = new RecaptchaInput();
    ci.name = name;
    return ci;
  }
  
  public RecaptchaInput setSiteKey(String siteKey) {
	  this.siteKey = siteKey;
	  return this;
  }

  public RecaptchaInput setSecret(String secret) {
	  this.secret = secret;
	  return this;
  }

  
  @Override
  public String getInputTag(Map<String, String> attributes) {
	  // TODO: style, format, tabindex
	  // JS Include should be placed in head. But for convenience, this works as well.
    return "<script src=\"https://www.google.com/recaptcha/api.js\">"
    		+ "</script><div class=\"g-recaptcha\" "
    		+ "data-sitekey=\""+ siteKey+ "\"></div>";
  }


  
  
  @Override
  public void init(HttpServletRequest request, boolean firstRun, Validator validator) {
    if (!firstRun) {
      String userInput = request.getParameter("g-recaptcha-response");

      if (!verify(userInput, secret)){
        this.valid = false;
        this.setErrorMessage("Captcha not valid");
      }
    }
  }
  
  
  public boolean verify(String gRecaptchaResponse, String secret) {
      if (gRecaptchaResponse == null || "".equals(gRecaptchaResponse)) {
          return false;
      }
       
      try{
      URL obj = new URL("https://www.google.com/recaptcha/api/siteverify");
      HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

      con.setRequestMethod("POST");

      String postParams = "secret=" + secret + "&response="
              + gRecaptchaResponse;

      // Send post request
      con.setDoOutput(true);
      DataOutputStream wr = new DataOutputStream(con.getOutputStream());
      wr.writeBytes(postParams);
      wr.flush();
      wr.close();

      int responseCode = con.getResponseCode();
      System.err.println("Responsecode: " + responseCode);
      BufferedReader in = new BufferedReader(new InputStreamReader(
              con.getInputStream()));
      String inputLine;
      StringBuffer response = new StringBuffer();

      while ((inputLine = in.readLine()) != null) {
          response.append(inputLine);
      }
      in.close();

      // print result
      System.out.println(response.toString());
       
      //parse JSON response and return 'success' value
      JsonReader jsonReader = Json.createReader(new StringReader(response.toString()));
      JsonObject jsonObject = jsonReader.readObject();
      jsonReader.close();
       
      return jsonObject.getBoolean("success");
      }catch(Exception e){
          e.printStackTrace();
          return false;
      }
  }

}
