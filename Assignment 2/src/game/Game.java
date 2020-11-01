package game;

import java.util.Scanner;

public class Game extends Board {
	protected Player[] players;
	protected Scanner s;

//Init Constructor
	public Game(int n, int m, Player p1, Player p2) {
		super(n, m);
		players = new Player[2];
		players[0] = p1;
		players[1] = p2;
		s = new Scanner(System.in);
	}

//Check if the move of the player wins the game
	protected boolean doesWin(int i, int j) {
		if (i == 0 && j == 0) 
			return true;
		return false;
	}

//Request the player to make move and check if the move won the game
	protected boolean onePlay(Player p) {
		int x;
		int y;
		while (true) {
			x = s.nextInt();
			y = s.nextInt();
			if (isEmpty(x, y)) {
				set(x, y, p);
				break;
			}
		}
		if (doesWin(x, y))
			return true;
		return false;

	}

//Manage the game until someone wins
	public Player play() {
		while (true) {
			if (isFull())
				return null;
			if (onePlay(players[0]))
				return players[0];
			if (isFull())
				return null;
			if (onePlay(players[1]))
				return players[1];
		}
	}
}
