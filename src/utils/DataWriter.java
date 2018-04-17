package utils;
import java.io.*;

public class DataWriter {
	public static void writeData(String path, String data) 
			throws FileNotFoundException, IOException{
		try(BufferedWriter writer = new BufferedWriter(new FileWriter(path))){
			writer.write(data);
		}
	}
	
	public static void writeData(File file, String data) 	
			throws FileNotFoundException, IOException{
		writeData(file.getAbsoluteFile(), data);
		
	}
}
