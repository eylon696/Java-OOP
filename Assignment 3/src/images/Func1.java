package images;

public class Func1 implements TwoDFunc {

	@Override
	public double f(double x, double y) {
			if (x < 0.25)
				return 0;
			if (y < 0.25)
				return 1;
		return (x + y) / 2;
	}


}
