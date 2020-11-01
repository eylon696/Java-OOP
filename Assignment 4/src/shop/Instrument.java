package shop;

public abstract class Instrument {
	public static int serial1 = -1;
	private String company;
	private int price;

	public Instrument(String company, int price) {
		this.company = company;
		this.price = price;
		serial1++;
	}

	public int getPrice() {
		return price;
	}

	public String getCompany() {
		return company;
	}

	public abstract int getSerial();

	public abstract String toString();

}
