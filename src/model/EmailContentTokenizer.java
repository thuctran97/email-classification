package model;
import java.util.*;

public class EmailContentTokenizer {
	
	private static String preprocess(String text) {
		return text;
	}
	
	public static List<String> tokenize(String data) {
		List<String> tokens = new ArrayList<>();				
		data = preprocess(data);
		String[] temps = data.split(" ");
		for (String word : temps) {
			tokens.add(word);
		}
		return tokens; 
	}
}
