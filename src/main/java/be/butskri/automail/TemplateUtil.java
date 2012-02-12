package be.butskri.automail;

import java.util.Map;
import java.util.Map.Entry;

public class TemplateUtil {

	public static String vulParametersIn(String string, Map<String, String> parameterMap) {
		String result = string;
		for (Entry<String, String> entry : parameterMap.entrySet()) {
			result = result.replaceAll("\\$\\{" + entry.getKey() + "\\}", entry.getValue());
		}
		return result;
	}
}
