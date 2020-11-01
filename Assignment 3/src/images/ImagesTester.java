// 12721987
package images;

import util.Tester;

// Structure

class RGBSk {
	public static final RGB BLACK = null, WHITE = null, RED = null, GREEN = null, BLUE = null;

	public RGBSk(double grey) {
	}

	public RGBSk(double red, double green, double blue) {
	}

	public double getRed() {
		return 0;
	}

	public double getGreen() {
		return 0;
	}

	public double getBlue() {
		return 0;
	}

	public RGB invert() {
		return null;
	}

	public RGB filter(RGB filter) {
		return null;
	}

	public static RGB mix(RGB rgb1, RGB rgb2, double alpha) {
		return null;
	}

	public static RGB superpose(RGB rgb1, RGB rgb2) {
		return null;
	}

	public String toString() {
		return null;
	}
}

interface ImageSk {
	public int getWidth();

	public int getHeight();

	public RGB get(int x, int y);
}


// ---------------------------------------------------------------

class BaseImageTester extends Tester {
	boolean smallDiff(double x, double y) {
		return Math.abs(x - y) < 0.001;
	}

	void checkEqRGB(RGB rgb1, RGB rgb2, String message) {
		check(smallDiff(rgb1.getRed(), rgb2.getRed()) && smallDiff(rgb1.getBlue(), rgb2.getBlue())
				&& smallDiff(rgb1.getGreen(), rgb2.getGreen()),
				String.format("%sExpected %s but got %s", message == null || message.equals("") ? "" : message + ": ",
						rgb2, rgb1));
	}
}

public class ImagesTester extends BaseImageTester {

	private static RGB g1 = new RGB(0.2, 0.7, 0.111);
	private static RGB g2 = new RGB(0.9, 0.3, 0.75);
	private static RGB g3 = new RGB(0.3, 0.6, 0.21);
	private static Image circle = new Circle(20, 10, 5, 8, 10, g1, g2);
	private static Image grad = new Gradient(10, 20, g3, g2);

	void testStructure() {
		testEqualClasses(RGB.class, RGBSk.class);
		testEqualClasses(Image.class, ImageSk.class);
	}

	void testRGB() {
		initPublishedTest(RGB.class, "Testing all RGB's methods");
		checkEqDouble(g1.getRed(), 0.2, "getRed");
		checkEqDouble(g1.getGreen(), 0.7, "getGreen");
		checkEqDouble(g1.getBlue(), 0.111, "getBlue");
		checkEqStr(g1, "<0.2000, 0.7000, 0.1110>", "toString");

		checkEqRGB(new RGB(0.2), new RGB(0.2, 0.2, 0.2), "grey constructor");
		checkEqRGB(g1.invert(), new RGB(0.8, 0.3, 1 - 0.111), "invert");
		checkEqRGB(g1, new RGB(0.2, 0.7, 0.111), "invert should not change original");

		checkEqRGB(g1.filter(g2), new RGB(0.18, 0.21, 0.08325), "filter");
		checkEqRGB(RGB.mix(g1, g2, 0.3), new RGB(0.69, 0.42, 0.5583), "mix");
		checkEqRGB(RGB.BLACK, new RGB(0, 0, 0), "BLACK");
		checkEqRGB(RGB.WHITE, new RGB(1, 1, 1), "WHITE");
		checkEqRGB(RGB.RED, new RGB(1, 0, 0), "RED");
		checkEqRGB(RGB.GREEN, new RGB(0, 1, 0), "GREEN");
		checkEqRGB(RGB.BLUE, new RGB(0, 0, 1), "BLUE");
	}

	void testGradient() {
		initPublishedTest(Gradient.class);
		Image i = new Gradient(20, 10, g1, g2);
		checkEq(i.getWidth(), 20, "getWidth");
		checkEq(i.getHeight(), 10, "getHeight");
		checkEqRGB(i.get(3, 2), new RGB(0.305, 0.64, 0.20685), "check1");
	}

	void testCircle() {
		initPublishedTest(Circle.class);
		checkEqRGB(circle.get(4, 7), new RGB(0.29899495, 0.643431, 0.201368), "inside circle");
		checkEqRGB(circle.get(19, 9), g2, "outside circle");
	}

	void testFilter() {
		initPublishedTest(Filter.class);
		Image i = new Filter(circle, g3);
		checkEqRGB(i.get(2, 5), new RGB(0.149095, 0.318177, 0.080242), "check");
	}

	void testInvert() {
		initPublishedTest(Invert.class);
		Image i = new Invert(circle);
		checkEqRGB(i.get(2, 5), new RGB(0.503015, 0.469706, 0.617895), "check");
	}

	void testTranspose() {
		initPublishedTest(Transpose.class);
		Image i = new Transpose(circle);
		checkEq(i.getWidth(), 10, "getWidth");
		checkEq(i.getHeight(), 20, "getHeight");
		checkEqRGB(i.get(2, 5), new RGB(0.620000, 0.460000, 0.494400), "check");
	}

	void testSuperpose() {
		initPublishedTest(Superpose.class);
		Image i = new Superpose(circle, grad);
		checkEq(i.getWidth(), 20, "getWidth");
		checkEq(i.getHeight(), 20, "getHeight");
		checkEqRGB(i.get(1, 1), new RGB(1.000000, 0.947510, 0.890178), "check1");
		checkEqRGB(i.get(18, 18), RGB.BLACK, "check2");
		checkEqRGB(i.get(18, 1), new RGB(0.900000, 0.300000, 0.750000), "check3");
		checkEqRGB(i.get(1, 18), new RGB(0.360000, 0.570000, 0.264000), "check4");
	}

	void testMix() {
		initPublishedTest(Mix.class);
		Image i = new Mix(circle, grad, 0.3);
		checkEq(i.getWidth(), 20, "getWidth");
		checkEq(i.getHeight(), 20, "getHeight");
		checkEqRGB(i.get(1, 1), new RGB(0.481307, 0.512253, 0.372653), "check1");
		checkEqRGB(i.get(18, 18), RGB.BLACK, "check2");
		checkEqRGB(i.get(18, 1), new RGB(0.900000, 0.300000, 0.750000), "check3");
		checkEqRGB(i.get(1, 18), new RGB(0.360000, 0.570000, 0.264000), "check4");
	}

	void testTwoColorImage() {
		initPublishedTest(TwoColorImage.class);
		Image i = new TwoColorImage(10, 20, RGB.RED, RGB.GREEN, new TwoDFunc() {
			@Override
			public double f(double x, double y) {
				return Math.sin(x * Math.PI * 2) + Math.cos(y * Math.PI * 2) + 2;
			}
		});
		checkEqRGB(i.get(4, 7), new RGB(0.000000, 1.000000, 0.000000), "check1");
		checkEqRGB(i.get(8, 11), new RGB(0.902113, 0.097887, 0.000000), "check2");
	}

	void testComplex() {
		initPublishedTest("complex example");
		Image i1 = new Gradient(500, 500, RGB.BLUE, RGB.BLACK);
		Image i2 = new Transpose(new Gradient(500, 500, RGB.RED, RGB.BLACK));
		Image i3 = new Mix(i1, i2, 0.5);
		Image i4 = new Circle(350, 150, new RGB(1, 1, 0), RGB.BLACK);
		Image i5 = new Circle(200, 100, new RGB(0, 0.5, 1), RGB.BLACK);
		Image i6 = new Circle(500, 200, RGB.WHITE, RGB.BLACK);
		Image i7 = new Superpose(i3, i4);
		Image i8 = new Superpose(i5, i6);
		Image i9 = new Superpose(i7, i8);

		checkEqRGB(i9.get(100, 100), new RGB(0.692893, 0.792893, 1.000000), "check1");
		checkEqRGB(i9.get(200, 400), new RGB(0.309431, 0.209431, 0.509431), "check2");
	}

	// -------------------------------------------------------------------------------
	// main

	// ------------------------------------------------------------

	public static void main(String[] args) {
		new ImagesTester().myMain("images");
	}

	// ------------------------------------------------------------
	// Here you can choose which tests to run.

	public void myTests() {
		testStructure();
		testRGB();
		testGradient();
		testCircle();
		testFilter();
		testInvert();
		testTranspose();
		testSuperpose();
		testMix();
		testTwoColorImage();
		testComplex();
	}
}