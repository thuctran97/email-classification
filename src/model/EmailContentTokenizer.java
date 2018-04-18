package model;
import java.util.*;

public class EmailContentTokenizer {
	
	private static String preprocess(String text) {
		return text;
	}
	
	public static List<String> tokenize(EmailData email) {
		List<String> tokens = new ArrayList<>();				
		String content = preprocess(email.getContent());
		String[] temps = content.split(" ");
		for (String word : temps) {
			tokens.add(word);
		}
		return tokens; 
	}
}
