package cities;

public class Road {
	private City city1, city2;
	private int length;

	public Road(City city1, City city2, int length) {
		this.city1 = city1;
		this.city2 = city2;
		this.length = length;
		city1.connect(this);
		city2.connect(this);
	}

	public City getCity1() {
		return city1;
	}

	public City getCity2() {
		return city2;
	}
	public int getLength() {
		return length;
	}
}
