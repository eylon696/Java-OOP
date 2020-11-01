package SimpleFx2;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class MyController {

	private int counter = 0;
	@FXML
	private GridPane g1;
	@FXML
	private Button b1;

	@FXML
	private Button b2;

	@FXML
	private Label l1;

	//When pressing "Ofra Haza" increasing the counter by 1 and displaying it
	@FXML
	void increasecounter(MouseEvent event) {
		counter++;
		l1.setText("" + counter + "");

	}
	//When pressing "Yardena Arazi" decreasing the counter by 1 and displaying it
	@FXML
	void decreasecounter(MouseEvent event) {
		counter--;
		l1.setText("" + counter + "");
	}
}
