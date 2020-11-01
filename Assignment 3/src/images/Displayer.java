package images;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Displayer extends Application {

	private static Image image;

	private static int to255(double x) {
		return Math.round((int) (x * 255.0));
	}

	private static Color fromRGB(RGB c) {
		return Color.rgb(to255(c.getRed()), to255(c.getGreen()), to255(c.getBlue()));
	}

	private static void saveToFile(WritableImage image) {
		File outputFile = new File("abc.png");
		BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
		try {
			ImageIO.write(bImage, "png", outputFile);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Displayer");

		WritableImage writableImage = new WritableImage(image.getWidth(), image.getHeight());
		PixelWriter pixelWriter = writableImage.getPixelWriter();

		for (int i = 0; i < image.getWidth(); i++) {
			for (int j = 0; j < image.getHeight(); j++) {
				pixelWriter.setColor(i, j, fromRGB(image.get(i, j)));
			}
		}
		ImageView view = new ImageView();
		view.setImage(writableImage);
		saveToFile(writableImage);

		HBox hbox = new HBox(view);
		Scene scene = new Scene(hbox, image.getWidth(), image.getHeight());
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void display(Image i) {
		image = i;
		Application.launch(new String[] {});
	}
	
	
	
	public static void main(String[] args) {

		// A simple class implementing Image
		class RedImage implements Image {
			@Override
			public int getWidth() {
				return 200;
			}
			@Override
			public int getHeight() {
				return 200;
			}
			@Override
			public RGB get(int x, int y) {
				if (x < 70)
					return y < 120 ? RGB.RED : RGB.BLUE;
				else
					return y < 80 ? RGB.GREEN : new RGB(1, 1, 0);
			}
		}
		
		Displayer.display(new RedImage());
	}
}
