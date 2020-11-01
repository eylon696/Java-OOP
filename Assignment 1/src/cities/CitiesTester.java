// 306714402
package cities;

import util.Tester;

//----------------------------------------------------
// Class skeletons for checking.

class CitySk {
	public CitySk(String name) {
	}

	public void connect(Road r) {
	}

	public String toString() {
		return null;
	}

	public City nearestCity() {
		return null;
	}

}

class RoadSk {
	public RoadSk(City city1, City city2, int length) {
	}

	public City getCity1() {
		return null;
	}

	public City getCity2() {
		return null;
	}

	public int getLength() {
		return 0;
	}
}

public class CitiesTester extends Tester {

	public void testCityStructure() {
		testEqualClasses(City.class, CitySk.class);
	}

	public void testRoadStructure() {
		testEqualClasses(Road.class, RoadSk.class);
	}

	public void testCityAndRoad() {
		initPublishedTest("basic test");
		City karmiel = new City("Karmiel");
		City metula = new City("Metula");
		City telAviv = new City("Tel-Aviv");
		City jerusalem = new City("Jerusalem");

		checkEqStr(karmiel, "Karmiel", "toString()");

		new Road(karmiel, metula, 50);
		new Road(karmiel, telAviv, 100);
		new Road(telAviv, jerusalem, 80);
		Road r = new Road(jerusalem, metula, 175);

		checkEq(r.getCity1(), jerusalem, "Road.getCity1");
		checkEq(r.getCity2(), metula, "Road.getCity2");
		checkEq(r.getLength(), 175, "Road.getLength");

		check(karmiel.nearestCity() == metula, "City.nearestCity");
	}

	// ------------------------------------------------------------

	public static void main(String[] args) {
		new CitiesTester().myMain();
	}

	// ------------------------------------------------------------
	// Here you can choose which tests to run.

	public void myTests() {
		testCityStructure();
		testRoadStructure();
		testCityAndRoad();
	}
}
