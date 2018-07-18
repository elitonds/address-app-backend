package address.utils;

import java.text.Normalizer;

public class Utils {

	public static String removeAccents(String input) {
		String replacedString = Normalizer.normalize(input, Normalizer.Form.NFD);
		replacedString = replacedString.replaceAll("[^\\p{ASCII}]", "");
		return replacedString;
	}
}
