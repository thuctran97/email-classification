package utils;
import java.util.*;

import model.EmailData;

import java.io.*;

public class DataReader {
	public static List<EmailData> readData(String path) throws FileNotFoundException, IOException {
		List<EmailData> emails = new ArrayList<>();
		List<String> data = DataReader.readText(path);
		for (String emailData : data) {
			emails.add(EmailData.parseClassAndContent(emailData));
		}
		return emails;
	}
	public static List<String> readText(String path) 
			throws FileNotFoundException, IOException{
		List<String> listOfText = new ArrayList<>();
		try(BufferedReader reader = new BufferedReader(new FileReader(path))){
			String str = null;
			while ((str = reader.readLine()) != null) {
				listOfText.add(str);
			}
		}		
		return listOfText;
	}
	public static List<String> readText(File file) 
			throws FileNotFoundException, IOException{
		return readText(file.getAbsolutePath());
	}
}
