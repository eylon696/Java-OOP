package cities;

import java.util.Set;
import java.util.TreeSet;
import java.util.ArrayList;
import java.util.List;


public class Country implements Comparable<Country> {
	private String name;
	private Set<City> cities;
	
	public Country(String name) {
		this.name=name;
		cities=new TreeSet<City>();
	}
	public void addCity(City city) {
		if(!city.getCountry().toString().equals(name))
			throw new IllegalArgumentException ();
		cities.add(city);
	}
	public int population() {
		int sum=0;
		for(City i : cities ) {
			sum+=i.getPopulation();
		}
		return sum;
	}
	public String toString() {
		return name;
		
	}
	public List<City> smallCities(int under){
		List<City> l =new ArrayList<City>();
		for(City i : cities) {
			if(i.getPopulation()<under)
				l.add(i);//add the city with population under to the array list
		}
		return l;
		
	}
	public String report() {
		StringBuilder s= new StringBuilder();
		s.append(name);
		s.append("(");
		s.append(population());
		s.append(")");
		s.append(" : ");
		for(City i : cities) {
			s.append(i.getName());
			s.append("(");
			s.append(i.getPopulation());
			s.append("), ");
		}
		s.deleteCharAt(s.length()-1);
		s.deleteCharAt(s.length()-1);
		return s.toString();
	}
	
	//Compares country names
	@Override
	public int compareTo(Country other) {
		return name.compareTo(other.name);
		
	}
	//Checks if the country names are the same 
	@Override
	public boolean equals(Object obj) {
		Country other=(Country) obj;
		if(!other.name.equals(this.name))
			return false;
		return true;
	}
}
