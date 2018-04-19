package application;

import java.io.FileNotFoundException;
import java.io.IOException;
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
			Scene scene = new Scene(root, 400, 400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws FileNotFoundException, IOException {
	//	List<EmailData> datasetList = DataReader.readData(".\\train data\\set1.data");
		List<EmailData> datasetList = DataReader.readData(".\\train data\\SMSSpamCollection.data");
		Process.prepareNumber(datasetList);
	//	List<String> inputData = DataReader.readText(".\\train data\\test1.txt");
		List<String> inputData = DataReader.readText(".\\train data\\test.txt");
		Process.classifier(inputData);
		Process.printNumber();
		Process.printCompare(datasetList);
		Process.printResult();
	}
}
