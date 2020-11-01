package cities;

public class City implements Comparable<City> {
	private String name;
	private Country country;
	private int population;

	public City(String name, Country country, int population) {
		this.name = name;
		this.country = country;
		this.population = population;
	}

	public String getName() {
		return name;
	}

	public Country getCountry() {
		return country;
	}

	public int getPopulation() {
		return population;
	}

	public String toString() {
		String s = String.format("%s (of %s)", getName(), getCountry());
		return s;
	}
	
	//Compares City names
	@Override
	public int compareTo(City other) {
		return this.name.compareTo(other.getName()); 
	}
	
	//Check if both country and city are equals
	@Override
	public boolean equals(Object obj) {
		City other=(City) obj;
		if(!other.getCountry().equals(country))
			return false;
		if(!other.name.equals(this.getName()))
			return false;
		return true;
	}
}
