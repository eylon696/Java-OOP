package cities;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class World {

	private Map<String, Country> countries;

	public World() {
		countries = new TreeMap<String, Country>();
	}
//Inserting a new country to the tree map
	public void addCountry(String name) {
		Country country = new Country(name);
		countries.put(name, country);	
	}

	public void addCity(String name, String countryName, int population)  {
		//adds a new city to an existing country
		if(countries.containsKey(countryName)) {
			City city = new City(name,countries.get(countryName),population);
			countries.get(countryName).addCity(city);
		}
		//the country does not exist in the tree map
		else
			throw new IllegalArgumentException();
	}
//returns a sum of all the populations of all the countries
	public int population() {
		int sum = 0;
		for (Map.Entry<String, Country> entry : countries.entrySet()) {
			sum += entry.getValue().population();
		}
		return sum;
	}

	public List<City> smallCities(int under) {
		List<City> l = new ArrayList<City>();
		for (Map.Entry<String, Country> entry : countries.entrySet()) {
			List<City> temp = countries.get(entry.getValue().toString()).smallCities(under);
			for (City c : temp) {
				l.add(c);//add the city with population under to the array
			}
		}
		return l;
	}

	public String report() {
		StringBuilder s = new StringBuilder();
		for (Map.Entry<String, Country> entry : countries.entrySet()) {
			s.append(entry.getValue().report());
			s.append("\n");
		}
		s.append("Total population is ");
		s.append(population());
		s.append("\n");
		return s.toString();
	}

}
