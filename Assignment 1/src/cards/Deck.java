package cards;

public class Deck {
	private Card[] cards;
	private int num;
	private int numOfCards;

	public Deck(int num) {
		this.num = num;
		int counter = 0;
		numOfCards = num * 4;
		cards = new Card[numOfCards];
		for (int i = 0; i < num; i++)
			for (int j = 0; j < 4; j++) {
				cards[counter] = new Card(i, j);
				counter++;
			}
	}

	public Deck(Deck from, int num) {
		cards = new Card[num];
		int temp;
		if (num > from.numOfCards) {
			temp = from.numOfCards;
			numOfCards = from.numOfCards;
		} else {
			temp = num;
			numOfCards = num;
		}

		for (int i = 0; i < temp; i++) {
			cards[i] = from.takeOne();
		}
	}

	public Deck(Deck first, Deck second) {
		int counter = 0;
		int temp=first.numOfCards + second.numOfCards;
		cards = new Card[first.numOfCards + second.numOfCards];
		numOfCards = first.numOfCards + second.numOfCards;
		for (int i = 0; i < temp; i++) {
			if (first.numOfCards > 0 && second.numOfCards > 0) {
				cards[counter] = first.cards[first.numOfCards-1];
				counter++;
				first.numOfCards--;
				cards[counter] = second.cards[second.numOfCards-1];
				counter++;
				second.numOfCards--;
				i++;
			} else if (first.numOfCards == 0 && second.numOfCards != 0) {
				cards[counter] = second.cards[second.numOfCards-1];
				counter++;
				second.numOfCards--;
			} else if (second.numOfCards == 0 && first.numOfCards != 0) {
				cards[counter] = first.cards[first.numOfCards-1];
				counter++;
				first.numOfCards--;
			}
		}
	}

	public int getNumCards() {
		return numOfCards;
	}

	public Card takeOne() {
		Card temp = null;
		if (numOfCards == 0)
			return temp;
		temp = cards[numOfCards - 1];
		numOfCards--;
		return temp;
	}

	public String toString() {
		String s = "";
		int i;
		for (i = 0; i < numOfCards - 1; i++)
			s = s + cards[i].toString() + ", ";
		s = "[" + s + cards[i].toString() + "]";
		return s;
	}

	public void sort() {
		Card temp;
		for (int i = 0; i < numOfCards - 1; i++)
			for (int j = 0; j < numOfCards - i - 1; j++) {
				if (cards[j].compareTo(cards[j + 1]) > 0) {
					temp = cards[j];
					cards[j] = cards[j + 1];
					cards[j + 1] = temp;
				}
			}
	}
}
