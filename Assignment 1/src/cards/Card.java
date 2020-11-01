package cards;

public class Card {
	private int num;
	private int suit;
	

	public Card(int num, int suit) {
		this.num = num;
		this.suit = suit;
	
	}

	public int getNum() {
		return num;
	}

	public int getSuit() {
		return suit;
	}

	public String toString() {
		String s = null;
		switch (suit) {
		case 0:
			s = num + "C";
			break;
		case 1:
			s = num + "D";
			break;
		case 2:
			s = num + "H";
			break;
		case 3:
			s = num + "S";
			break;
		}
		return s;
	}

	public int compareTo(Card other) {
		 if (num > other.num)
			return 1;
		else if (num < other.num)
			return -1;
		else if(num==other.num && suit!=other.suit)
		{
			if(suit>other.suit)
				return 1;
			else return -1;
		}
		return 0;
	}
}
