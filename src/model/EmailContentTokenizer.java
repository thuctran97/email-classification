package model;
import java.util.*;

public class EmailContentTokenizer {
	
	private static String preprocess(String text) {
		return text;
	}
	public static List<String> tokenize(EmailData email) {
		List<String> tokens = new ArrayList<>();
		String content = preprocess(email.getContent());
		tokens = tokenize(content);
		return tokens;
	}
//	public static int check(int a) {
//		if ((a>47 && a<58) || (a>64 && a<91) || (a>96 && a<123)) return 1;
//		if (a!=32) return 2;
//		return 0;
//	}
//	public static boolean sameType(char a, char b) {
//		int na = (int)a;
//		int nb = (int)b;
//		if (check(na)==check(nb) && check(na)!=0) return true;
//		return false;
//	}
	public static boolean check(int a) {
		if ((a>47 && a<58) || (a>64 && a<91) || (a>96 && a<123)) return true;
		return false;
	}
	public static boolean sameType(char a, char b) {
		int na = (int)a;
		int nb = (int)b;
		if (check(na) && check(nb) ) return true;
		return false;
	}
	public static List<String> tokenize(String content) {
		List<String> tokens = new ArrayList<>();
		StringBuilder s = new StringBuilder(content);
		String sub = ""+s.charAt(0);
		for (int i = 1; i < s.length(); i++) {
			char j = s.charAt(i);
			if (sameType(j, s.charAt(i - 1)))
				sub += j;
			else 
			{
				if (sub!="") tokens.add(sub);
				sub = "";
				if (!Character.isWhitespace(j)) sub+=j;
			}
			if (i==s.length()-1) tokens.add(sub);
		}
		return tokens; 
	}
//	public static List<String> tokenize(String content) {
//		List<String> tokens = new ArrayList<>();
//		String[] temps = content.split(" ");
//		for (String word : temps) {
//			String w = word;
//			if (w.contains("Â")) {
//				StringBuilder s = new StringBuilder(w);
//				s.deleteCharAt(w.indexOf("Â"));
//				w = s.toString();
//			}
//			tokens.add(w);
//		}
//		return tokens; 
//	}
}
