package simpleFX;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Simple1 extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) {
		Scene scene = new Scene(makeGridPane(), 270, 150); //Creating a new scene 
		stage.setTitle("Voting Machine");//The window title is voting machine
		stage.setScene(scene);//Add the scene to the stage
		stage.show();//launch the show
	}

	private int i;
	private GridPane makeGridPane() {
		Label label = new Label("0");
		Button button1 = new Button("Ofra Haza");
		Button button2 = new Button("Yardena Arazi");
		GridPane gridpane = new GridPane();

		//When pressing "Ofra Haza" increasing the counter by 1 and displaying it
		class LabelIncreaser implements EventHandler<ActionEvent> {
			@Override
			public void handle(ActionEvent event) {
				i++;
				label.setText("" + i + "");
			}
		}
		//When pressing "Yardena Arazi" decreasing the counter by 1 and displaying it
		class LabelDecreaser implements EventHandler<ActionEvent> {
			@Override
			public void handle(ActionEvent event) {
				i--;
				label.setText("" + i + "");
			}
		}

		label.setAlignment(Pos.CENTER);//Setting the counter to be in the center
		BackgroundFill bgFill = new BackgroundFill(Color.RED, new CornerRadii(0), null);//Creating a red background fill
		Background bg = new Background(bgFill);//Creating background with the red fill
		label.setBackground(bg);//Adding the red background to the counter
		label.setPrefSize(Double.MAX_VALUE, Double.MAX_VALUE);//Making the label resizable
		button1.setOnAction(new LabelIncreaser());//Increase counter when pressing "Ofra Haza"
		button2.setOnAction(new LabelDecreaser());//Decrease counter when pressing "Yardena Arazi"
		gridpane.setHgap(30);//Adding a gap between the buttons 
		gridpane.setVgap(10);//Adding a gap below the buttons
		gridpane.add(button1, 0, 0);//Adding "Ofra Haza" button to the gridpane
		gridpane.add(button2, 1, 0);//Adding "Yardena Arazi" button to the gridpane
		gridpane.add(label, 0, 2, 2, 3);//Adding the counter to the gridpane and set the layout of the background
		gridpane.setPadding(new Insets(20));////Add padding from the margins
		return gridpane;
	}

}
