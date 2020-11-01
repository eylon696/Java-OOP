package images;

public class RGB {
	public static final RGB BLACK = new RGB(0);
	public static final RGB WHITE = new RGB(1);
	public static final RGB RED = new RGB(1, 0, 0);
	public static final RGB GREEN = new RGB(0, 1, 0);
	public static final RGB BLUE = new RGB(0, 0, 1);
	private double red;
	private double green;
	private double blue;

	public RGB(double red, double green, double blue) {
		this.red = red;
		this.green = green;
		this.blue = blue;

	}

	public RGB(double grey) {
		red = grey;
		green = grey;
		blue = grey;

	}

	public double getRed() {
		return red;

	}

	public double getBlue() {
		return blue;

	}

	public double getGreen() {
		return green;

	}

	public RGB invert() {
		RGB temp = new RGB(1 - red, 1 - green, 1 - blue);
		return temp;

	}

	public RGB filter(RGB filter) {
		RGB temp = new RGB(red * filter.red, green * filter.green, blue * filter.blue);
		return temp;
	}

	public static RGB superpose(RGB rgb1, RGB rgb2) {
		double redc = rgb1.red + rgb2.red;
		double greenc = rgb1.green + rgb2.green;
		double bluec = rgb1.blue + rgb2.blue;
		//Check if one of the colors reached the maximum value
		if (redc > 1)
			redc = 1.0;
		if (greenc > 1)
			greenc = 1.0;
		if (bluec > 1)
			bluec = 1.0;
		RGB temp = new RGB(redc, greenc, bluec);
		return temp;
	}

	//This method mix two colors
	//                      YELLOW    RED
	public static RGB mix(RGB rgb1, RGB rgb2, double alpha) {
		double color1=alpha * rgb1.red + (1 - alpha) * rgb2.red;
		double color2=alpha * rgb1.green + (1 - alpha) * rgb2.green;
		double color3=alpha * rgb1.blue + (1 - alpha) * rgb2.blue;
		RGB temp = new RGB(color1,color2,color3);
		return temp;
	}

	public String toString() {
		String s="<";
		s+=String.format("%.4f", red);
		s+=", ";
		s+=String.format("%.4f", green);
		s+=", ";
		s+=String.format("%.4f", blue);
		s+=">";
		return s;
	}
}
