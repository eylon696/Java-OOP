package mines;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Controller {

	@FXML
	public Button resetB;
	@FXML
	public TextField heightB;
	@FXML
	public TextField widthB;
	@FXML
	public TextField minesB;

	@FXML

	public Button getResetbutton() { //Returns reset button
		return resetB;
	}

	public int getHeight() { // Returns the inserted height
		int val;
		try {
			val = Integer.parseInt(heightB.getText());
			if (val > 0)
				return val;
			return 0;
		} catch (Exception e) ////If the input is not a number return 0
		{
			return 0;
		}
	}

	public int getWidth() { // Returns the inserted width
		int val;
		try {
			val = Integer.parseInt(widthB.getText());
			if (val > 0)
				return val;
			return 0;
		} catch (Exception e) {//If the input is not a number return 0
			return 0;
		}
	}

	public int getMines() { // Returns the inserted mines
		int val;
		try {
			val = Integer.parseInt(minesB.getText());
			if (val > 0)
				return val;
			return 0;
		} catch (Exception e) {//If the input is not a number return 0
			return 0;
		}
	}

}
