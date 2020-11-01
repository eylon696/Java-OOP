package shop;

public enum Type {
	ACOUSTIC, ELECTRIC, CLASSICAL;
	
	public String toString() {
		String s="";
		switch (this) {
		case ACOUSTIC:
			s="Acoustic";
			break;
		case ELECTRIC:
			s="Electric";
			break;
		case CLASSICAL:
			s="Classical";
			break;
		default:
			break;
		}
		return s;
	}
}
