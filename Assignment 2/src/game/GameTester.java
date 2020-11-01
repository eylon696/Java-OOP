// 17614598
package game;

import util.Tester;

//Class skeletons for checking.
class PlayerSk {
	public PlayerSk(String name, char mark) {
	}

	public String getName() {
		return null;
	}

	public char getMark() {
		return 1;
	}

	public String toString() {
		return null;
	}
}

class BoardSk {
	public BoardSk(int n, int m) {
	}

	public boolean isEmpty(int i, int j) {
		return false;
	}

	public Player get(int i, int j) {
		return null;
	}

	public boolean isFull() {
		return false;
	}

	protected boolean set(int i, int j, Player p) {
		return false;
	}

	protected int maxLineContaining(int i, int j) {
		return 0;
	}

	public String toString() {
		return null;
	}
}

class GameSk extends Board {
	public GameSk(int n, int m, Player p1, Player p2) {
		super(n, m);
	}

	protected boolean onePlay(Player p) {
		return false;
	}

	protected boolean doesWin(int x, int y) {
		return false;
	}

	public Player play() {
		return null;
	}
}

class TicTacToeSk extends Game {
	public TicTacToeSk(String player1, String player2) {
		super(0, 0, null, null);
	}

	protected boolean doesWin(int x, int y) {
		return false;
	}
}

class FourInARowSk extends Game {
	public FourInARowSk(String player1, String player2) {
		super(0, 0, null, null);
	}

	protected boolean doesWin(int x, int y) {
		return false;
	}

	protected boolean onePlay(Player p) {
		return false;
	}
}

//-------------------------------------------------------------------
public class GameTester extends Tester {

	public void testStructure() {
		testEqualClasses(Player.class, PlayerSk.class);
		testEqualClasses(Board.class, BoardSk.class);
		testEqualClasses(Game.class, GameSk.class);
		testEqualClasses(TicTacToe.class, TicTacToeSk.class);
		testEqualClasses(FourInARow.class, FourInARowSk.class);
	}

	void testPlayer() {
		initPublishedTest(Player.class);
		Player p = new Player("Test", 'X');
		checkEq(p.getName(), "Test", "Player.getName");
		checkEq(p.getMark(), 'X', "Player.getMark");
		checkEqStr(p, "Test(X)", "Player.toString");
	}

	void testBoard() {
		initPublishedTest(Board.class);
		Player p1 = new Player("Bibi", 'B');
		Player p2 = new Player("Gantz", 'G');
		Board b = new Board(3, 4);

		b.set(0, 0, p1);
		b.set(1, 0, p1);
		b.set(2, 2, p2);
		b.set(0, 0, p2);
		b.set(0, 1, p1);
		checkEq(b.get(1, 0), p1, "Board.get");
		checkEqStr(b, "BB..\nB...\n..G.\n", "Board.toString");
		checkEq(b.isEmpty(2, 3), true, "Board.isEmpty");
		checkEq(b.isFull(), false, "Board.isFull");
		checkEq(b.maxLineContaining(1, 0), 2, "Board.maxLineContaining(1)");
		checkEq(b.maxLineContaining(2, 2), 1, "Board.maxLineContaining(2)");
	}

	void testGame() {
		initPublishedTest(Game.class);
		Player p1 = new Player("Red", 'R');
		Player p2 = new Player("Black", 'B');
		Game g;

		g = new Game(3, 4, p1, p2);
		g.set(0, 0, p1);
		check(g.doesWin(0, 0), "Game.doesWin");

		setInput("1 1  2 1");
		g = new Game(3, 4, p1, p2);
		g.set(1, 1, p1);
		check(!g.onePlay(p2), "Game.onePlay");

		setInput("1 1   2 1    1 1    0 0");
		g = new Game(3, 4, p1, p2);
		checkEq(g.play(), p1, "Game.play");
	}

	void testTicTacToe() {
		initPublishedTest(TicTacToe.class);
		
		setInput("1 1  0 0  2 0  1 2 0 2");
		TicTacToe g = new TicTacToe("sponge", "bob");
		checkEqStr(g.play(), "sponge(X)", "TicTacToe");
	}

	void testFourInARow() {
		initPublishedTest(FourInARow.class);
		
		setInput("1  0  1  2  3  0  1  0 5  6 6 0");
		FourInARow g = new FourInARow("sponge", "bob");
		checkEqStr(g.play(), "bob(B)", "FourInARow");
	}
	
	// ------------------------------------------------------------

	public static void main(String[] args) {
		new GameTester().myMain("game");
	}

	// ------------------------------------------------------------
	// Here you can choose which tests to run.

	public void myTests() {
		testStructure();
		testPlayer();
		testBoard();
		testGame();
		testFourInARow();
		testTicTacToe();
	}
}
