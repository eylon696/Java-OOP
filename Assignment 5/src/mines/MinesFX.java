package mines;

import java.io.IOException;
import java.util.Random;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class MinesFX extends Application {
	private Mines mines;
	private Button buttons[][]; // All the buttons on the grid
	private int height, width, numMines;
	private GridPane gridPane;
	private Random random = new Random(); // Will be used to place the mines in random locations
	private boolean newGrid, endGame;
	private Controller controller;
	private Stage primartStage;



	public GridPane mineSweeperButtons(int height, int width) // creates buttons gridpane for mine board
	{
		GridPane gridpane = new GridPane();
		// Initializing the design of the buttons
		for (int i = 0; i < height; i++)
			for (int j = 0; j < width; j++) {
				buttons[i][j] = new Button(mines.get(i, j));
				Button button = buttons[i][j];
				button.setMinSize(45, 45);
				button.setMaxSize(50, 50);
				button.setPrefSize(45, 45);
				button.setOnMouseClicked(new MouseClickedButton(i, j)); // Set event handler
				gridpane.add(button, i, j); // Adding buttons to the gridpane
			}
		gridpane.setPadding(new Insets(50));// Set padding from the margins
		// Adding mines in random locations on the grid
		for (int i = 0; i < numMines; i++)
			mines.addMine(random.nextInt(height), random.nextInt(width));
		return gridpane;

	}

	class MouseClickedButton implements EventHandler<MouseEvent> {
		private int x, y;

		public MouseClickedButton(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public void handle(MouseEvent click) {
			MouseButton mouseButton = click.getButton(); // Getting the clicked button
			if (mouseButton == MouseButton.SECONDARY) // If clicked right on the mouse toggle the flag
			{
				mines.toggleFlag(x, y);
				buttons[x][y].setText(mines.get(x, y));
			} else if (mines.get(x, y) != "F" && mouseButton == MouseButton.PRIMARY) // If clicked left on the mouse and
																						// there is no flag
			{
				if (mines.isDone() == false) {
					if (mines.open(x, y) == false) // If we hit a mine we show the appropriate message
					{
						mines.setShowAll(true);
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("Loss");
						alert.setHeaderText(null);
						alert.setContentText("You lost! try again");
						if (endGame == false)// Show the message
							alert.show();
						endGame = true;
					}
				}

				if (mines.isDone() == true) // If the user won
				{
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Win");
					alert.setHeaderText(null);
					alert.setContentText("OMG, you just won!!!");
					if (endGame == false) // Show the message
						alert.show();
					endGame = true;
				}
				for (int i = 0; i < controller.getHeight(); i++) // Update the grid after each click
					for (int j = 0; j < controller.getWidth(); j++)
						buttons[i][j].setText(mines.get(i, j));
			}
		}

	}

	class Reset implements EventHandler<ActionEvent> // This class resetting the grid when reset button is pressed
	{
		private Controller innerController;
		private HBox innerHBox;

		public Reset(Controller innerController, HBox innerHBox) {
			this.innerController = innerController;
			this.innerHBox = innerHBox;
		}

		@Override
		public void handle(ActionEvent event) { // Creating a new grid
			if (newGrid == false) // Remove old grid
				innerHBox.getChildren().remove(gridPane);
			newGrid = false;
			endGame = false;
			height = innerController.getHeight();
			width = innerController.getWidth();
			numMines = innerController.getMines();
			if (width == 0 || height == 0)//Don't create if height or width is equal to 0
				return; 
			buttons = new Button[height][width]; 
			mines = new Mines(height, width, numMines); 
			gridPane = mineSweeperButtons(height, width); 
			innerHBox.getChildren().add(gridPane);
			primartStage.setHeight(width * 75 + 75); 
			primartStage.setWidth(height * 75 + 75); 
			primartStage.setMaxHeight(Double.MAX_VALUE);
			primartStage.setMaxWidth(Double.MAX_VALUE);
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primartStage) throws Exception {
		HBox hbox;
		try { // Loading fxml file
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("Minesweeper.fxml"));
			hbox = loader.load();
			controller = loader.getController();
			this.primartStage = primartStage;
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		Scene s = new Scene(hbox);
		primartStage.setScene(s);
		primartStage.setTitle("The Amazing Mines Sweeper");
		primartStage.show();
		newGrid = true;
		Button reset = controller.getResetbutton();
		reset.setOnAction(new Reset(controller, hbox));
	}

}
