package cities;

public class City {
	private Road[] roads;
	private int numRoads;
	private String name;

	public City(String name) {
		this.name = name;
		roads = new Road[10];
		numRoads = 0;
	}

	public void connect(Road r) {
		roads[numRoads] = r;
		numRoads++;
	}

	public City nearestCity() {
		City temp = null;
		int templength;
		if (numRoads != 0) 
		{
			templength = roads[0].getLength();
			temp=roads[0].getCity2();
			for (int i = 1; i < numRoads; i++) {
				if (templength > roads[i].getLength()) {
					templength = roads[i].getLength();
					temp = roads[i].getCity2();
				}
			}
		}
		return temp;
	}

	public String toString() {
		return name;
	}

}
