package de.jformchecker;

/**
 * Utils to handle ajax stuff
 * @author jochen
 *
 */
public class AjaxUtils {

	/**
	 * returns json output in this format: http://labs.omniti.com/labs/jsend
	 * 
	 * { "status" : "fail", "data" : { "title" : "A title is required" } }
	 *
	 * 
	 * { status : "success", data : null }
	 * 
	 * @param fc
	 * @return
	 */
	public static String getJsonOutput(FormChecker fc) {
		StringBuilder json = new StringBuilder();
		String status = fc.isValidAndNotFirstRun() ? "success" : "fail";
		json.append("{\"status\": \"" + status + "\",");
		json.append("\"data\":");
	
		// Problems:
		if (fc.isValidAndNotFirstRun()) {
			json.append("\"null\",");
		} else {
			// append errors:
			json.append("{");
			String commaAppend = "";
			for (FormCheckerElement elem : fc.getForm().getElements()) {
				if (!elem.isValid()) {
					json.append(commaAppend);
					json.append("\"" + elem.getName() + "\":" + "\"" + elem.getValidationResult().getMessage() + "\"");
					commaAppend = ",";
				}
			}
			json.append("},");
		}
	
		// no problems
		json.append("\"okdata\":");
		// append errors:
		json.append("{");
		String commaAppend = "";
		for (FormCheckerElement elem : fc.getForm().getElements()) {
			if (elem.isValid()) {
				json.append(commaAppend);
				json.append("\"" + elem.getName() + "\":" + "\"" + "OK" + "\"");
				commaAppend = ",";
			}
		}
		json.append("}");
	
		json.append("}");
	
		return json.toString();
	}

}
