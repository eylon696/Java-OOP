package library;

public class Author {
	private String name;
	private int birthYear;

	public Author(String name, int birthYear) {
		this.name = name;
		this.birthYear = birthYear;
	}

	public String getName() {
		return name;
	}

	public int getBirthYear() {
		return birthYear;
	}

	public int getAge(int thisYear) {
		return thisYear - birthYear;
	}

	public String toString() {
		return getName() + "(" + getBirthYear() + ")";

	}

}
