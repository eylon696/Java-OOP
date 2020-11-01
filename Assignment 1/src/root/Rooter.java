package root;

public class Rooter {
	private double precision;

	public Rooter(double precision) {
		this.precision = precision;
	}

	public void setPrecision(double precision) {
		this.precision = precision;

	}

	public double sqrt(double x) {
		double one = x / 2, two = x / one, temp;
		while (true) {
			two = x / one;
			if (one == two)
				return one;
			else {
				if (one < two)
					temp = two - one;
				else
					temp = one - two;
				if (temp < precision)
					return one;
				else
					one = (one + two) / 2;
			}
		}
	}
}