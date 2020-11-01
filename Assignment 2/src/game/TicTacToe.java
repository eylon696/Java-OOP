package game;

public class TicTacToe extends Game {
//Init Constructor
	public TicTacToe(String player1, String player2) {
		super(3, 3, new Player(player1, 'X'), new Player(player2, 'O'));//Calls the Game constructor

	}

	@Override
	protected boolean doesWin(int x, int y) { // Check if the line's length is 3
		if (maxLineContaining(x, y) == 3)
			return true;
		return false;
	}

}
