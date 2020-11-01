package SimpleFx2;

import java.io.IOException;
import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {
	public static void main(String[] args) {
		launch(args);
	//	Application.launch(Main.class, new String[0]);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		GridPane gridpane;
		Scene scene;
		try {
			FXMLLoader loader = new FXMLLoader();
			URL loacation = getClass().getResource("check.fxml");
			loader.setLocation(loacation);
			gridpane = (GridPane)loader.load();
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		scene = new Scene(gridpane);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Voting Machine");
		primaryStage.show();
	}
}
