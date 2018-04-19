package application;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import model.EmailContentTokenizer;
import model.EmailData;
import model.NumbOfWords;
import utils.DataWriter;

public class Process {
	public static Map<String, NumbOfWords> Number = new HashMap<String, NumbOfWords>();
	public static List<String> Class = new ArrayList<String>();
	public static int NumbOfVocab, NumOfWordsInSpam, NumOfWordsInHam, Sum;
	public static float PHam, PSpam;

	public static void addNumber(List<String> words, int x, int y) {
		for (String word : words) {
			int h = 0, s = 0;
			if (Number.containsKey(word)) {
				h = Number.get(word).getCountInHam();
				s = Number.get(word).getCountInSpam();
			}
			NumbOfWords n = new NumbOfWords(h + x, s + y);
			Number.put(word, n);
		}
	}
	
	public static float PWSpam(String word) {
		int sum = NumOfWordsInSpam + NumbOfVocab;
		if (Number.containsKey(word)) return (float) (Number.get(word).getCountInSpam() + 1)/sum; else
			return (float)1/sum;
	}

	public static float PWHam(String word) {
		int sum = NumOfWordsInHam + NumbOfVocab;
		if (Number.containsKey(word)) return (float) (Number.get(word).getCountInHam() + 1) / sum; else 
		return (float)1/sum;
	}

	public static void prepareNumber(List<EmailData> emails){
		int NumbOfSpam = 0, NumbOfHam = 0;
		for (EmailData email : emails) {
			List<String> words = EmailContentTokenizer.tokenize(email);	
			if (email.getEmailClass().getName().equals("spam")) {
				NumbOfSpam++;
				NumOfWordsInSpam += words.size();
				addNumber(words, 0, 1);
			} else {
				NumbOfHam++;
				NumOfWordsInHam += words.size();
				addNumber(words, 1, 0);
			}
		}
		PSpam = (float) NumbOfSpam/emails.size();
		PHam = (float) NumbOfHam/emails.size();
		NumbOfVocab = Number.size();
	}
	public static void classifier(List<String> inputData) {
		float PHamEmail=0,PSpamEmail=0;
		for (String email : inputData) {
			List<String> words = EmailContentTokenizer.tokenize(email);
			PHamEmail=PHam;
			PSpamEmail=PSpam;
			for (String word: words) {
				PHamEmail*=PWHam(word);
				PSpamEmail*=PWSpam(word);
			}
			if (PHamEmail>PSpamEmail) Class.add("ham"); else Class.add("spam");
		}
	}
	public static void printNumber() throws FileNotFoundException, IOException {
		List<String> result = new ArrayList<String>();
		Set<String> keys = Number.keySet();
		for (String key : keys) {
			String ans= key+" " + PWSpam(key) + " " + PWHam(key);
			result.add(ans);
		}
		DataWriter.writeData(".\\train data\\number.txt", result);
	}
	public static void printCompare(List<EmailData> datasetList) throws FileNotFoundException, IOException {
		int count=0;
		List<String> result = new ArrayList<String>();
		for (int i=0; i<Class.size(); i++) {
			String classDataset= datasetList.get(i).getEmailClass().getName();
			String classTest = Class.get(i);
			String ans= classDataset+" "+classTest;
			if (classDataset!=classTest) {
				count++;
				ans+=" Sai------------------------";
			} else ans+=" Dung";
			result.add(ans);	
		}
		DataWriter.writeData(".\\train data\\compare.txt",result);
		System.out.println("ti le chinh xac: "+ (1-(float)count/Class.size())*100);
	}
	
	public static void printResult() throws FileNotFoundException, IOException {
		DataWriter.writeData(".\\train data\\result.txt",Class);
	}
}
