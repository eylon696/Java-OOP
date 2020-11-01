package bank;

public class ProAccount extends Account {
	private int[] history;
	private int size;

//Init constructor+successor from object Account
	public ProAccount(String name) {
		super(name);
		history = new int[100];
		size = 0;
	}

//Updates the amount after each action
	@Override
	public void add(int amount) {
		if (size < 100) {
			history[size] = amount + this.getShekels();
			size++;
			super.add(amount);
		}

	}
//Print the object ProAccount
	@Override
	public String toString() {
		String s = new String();
		s = "[";
		for (int i = 0; i < size; i++) {
			if (i == size - 1)
				s = s + history[i];
			else
				s = s + history[i] + ",";
		}
		s = s + "]";
		return getName() + " has " + getShekels() + " shekels " + s;
	}
//Transfer money from ProAccount "from" to ProAccount "to"
	public static void transfer(ProAccount from, ProAccount to, int amount) {
		from.add(-amount);
		to.add(amount);
	}

}
