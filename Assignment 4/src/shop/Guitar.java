package shop;

public class Guitar extends Instrument {

	private Type type;
	private int guitarserial;

	public Guitar(String company, int price, Type type) {
		super(company, price);
		this.type = type;
		this.guitarserial = serial1;
	}

	public int getSerial() {
		return guitarserial;
	}

	public Type getType() {
		return type;
	}

	@Override
	public String toString() {
		String s = "";
		switch (getType()) {
		case ACOUSTIC:
			s = String.format("Guitar(%s) %s(%d), price = %d",type.toString(), getCompany(), getSerial(), getPrice());
			break;
		case ELECTRIC:
			s = String.format("Guitar(%s) %s(%d), price = %d",type.toString(), getCompany(), getSerial(), getPrice());
			break;
		case CLASSICAL:
			s = String.format("Guitar(%s) %s(%d), price = %d",type.toString(), getCompany(), getSerial(), getPrice());
			break;
		default:
			break;
		}
		return s;
	}
}
