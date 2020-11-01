package game;

public class FourInARow extends Game {
//Init Constructor
	public FourInARow(String player1, String player2) {
		super(6, 7, new Player(player1, 'W'), new Player(player2, 'B'));
	}

	@Override
	protected boolean doesWin(int i, int j) {// Check if the line's length is 4
		if (maxLineContaining(i, j) == 4)
			return true;
		return false;
	}

	@Override
	protected boolean onePlay(Player p) { // Request the player to make move and check if the move won the game
		int y;
		int i = 6;
		while (true) {
			y = s.nextInt();
			if (isEmpty(0, y)) {
				while (true) {
					if (isEmpty(i, y)) {
						set(i, y, p);
						break;
					}
					i--;
				}
				break;
			}
		}
		if (doesWin(i, y))
			return true;
		return false;
	}
}
