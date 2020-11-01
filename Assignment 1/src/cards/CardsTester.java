// 1078676455
package cards;

import util.Tester;

//----------------------------------------------------
//Class skeletons for checking.

class CardSk {
	public CardSk(int num, int suit) {
	}

	public int getNum() {
		return 0;
	}

	public int getSuit() {
		return 0;
	}

	public String toString() {
		return null;
	}

	public int compareTo(Card other) {
		return 0;
	}
}

class DeckSk {
	public DeckSk(int num) {
	}

	public DeckSk(Deck from, int num) {
	}

	public DeckSk(Deck first, Deck second) {
	}

	public void sort() {
	}

	public int getNumCards() {
		return 0;
	}

	public Card takeOne() {
		return null;
	}

	public String toString() {
		return null;
	}
}

public class CardsTester extends Tester {

	public void testCardStructure() {
		testEqualClasses(Card.class, CardSk.class);
	}

	public void testDeckStructure() {
		testEqualClasses(Deck.class, DeckSk.class);
	}

	// -------------------------------------------------------------------------
	// Here are the tests:

	void testCard() {
		initPublishedTest(Card.class);
		Card c = new Card(3, 2);
		checkEq(c.getNum(), 3, "getNum()");
		checkEq(c.getSuit(), 2, "getSuit()");
		Card c2 = new Card(4, 0);
		check(c.compareTo(c2) < 0, "compareTo()");
		checkEqStr(c, "3H", "toString()");
	}

	void testDeck() {
		initPublishedTest(Deck.class);
		Deck d1 = new Deck(3);
		checkEqStr(d1, "[0C, 0D, 0H, 0S, 1C, 1D, 1H, 1S, 2C, 2D, 2H, 2S]", "toString of: new Deck(3)");
		Deck d2 = new Deck(d1, 4);
		checkEqStr(d2, "[2S, 2H, 2D, 2C]", "using constructor Deck(Deck, int)");
		Deck d3 = new Deck(d1, d2);
		checkEqStr(d3, "[1S, 2C, 1H, 2D, 1D, 2H, 1C, 2S, 0S, 0H, 0D, 0C]", "using constructor Deck(Deck, Deck)");
		check(d1.getNumCards() == 0 && d2.getNumCards() == 0, "constructor Deck(Deck, Deck) not emptying input decks");
		d3.sort();
		checkEqStr(d3, "[0C, 0D, 0H, 0S, 1C, 1D, 1H, 1S, 2C, 2D, 2H, 2S]", "sort()");
		checkEqStr(d3.takeOne(), "2S", "takeOne");
	}

	// ------------------------------------------------------------

	public static void main(String[] args) {
		new CardsTester().myMain();
	}

	// ------------------------------------------------------------
	// Here you can choose which tests to run.

	public void myTests() {
		testCardStructure();
		testDeckStructure();
		testDeck();
		testCard();
	}
}
