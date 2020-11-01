package shop;

public class Piano extends Instrument {
	private int octaves;
	private int pianoserial;
	
	public Piano(String company, int price, int octaves) {
		super(company,price);
		this.octaves=octaves;
		this.pianoserial=serial1;
	}
	
	public int getSerial() {
		return pianoserial;
	}
	public int getOctaves() {
		return octaves;
	}

	@Override
	public String toString() {
		String s=String.format("Piano(%d octaves) %s(%d), price = %d",getOctaves(),getCompany(),getSerial(),getPrice());
		return s;
	}
}
