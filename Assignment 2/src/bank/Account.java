package bank;

public class Account {
	private String name;
	private int shekels;

//Init constructor
	public Account(String name) {
		this.name = name;
		shekels = 0;
	}

	public int getShekels() {
		return shekels;
	}

	public String getName() {
		return name;
	}

	public void add(int amount) {
		shekels += amount;
	}
//Print the object Account
	public String toString() {
		return name + " has " + shekels + " shekels";
	}
}
