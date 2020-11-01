package graph;

public class Place {
	private int x;
	private int y;

	public Place(int x, int y, int bound) {
		//Checks if x and y are in the range
		if (x < 0 || x > bound - 1 || y < 0 || y > bound - 1)
			throw new IllegalArgumentException();
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

//Creating a hash code that will be used by any hash set
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}
//Checks if both x and y are equals to another Place
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Place)) {
			return false;
		}
		Place other = (Place) obj;
		if (x != other.x) {
			return false;
		}
		if (y != other.y) {
			return false;
		}
		return true;
	}

}


