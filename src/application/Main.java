package application;
	
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.stage.Stage;
import model.EmailData;
import utils.DataReader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		//launch(args);		
		try {
			List<EmailData> emails = new ArrayList<>();
			List<String> data = DataReader.readText(".\\train data\\SMSSpamCollection.data");
			for (String emailData : data) {
				emails.add(EmailData.parseClassAndContent(emailData));				
			}
			
			for (EmailData email : emails) {
				System.out.println(email);
			}
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}		
	}
}
