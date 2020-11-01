package game;

public class Board {

	protected Player[][] board;
	protected int n, m;

//Init Constructor
	public Board(int n, int m) {
		this.n = n;
		this.m = m;
		board = new Player[n][m];
	}
//Put player p into the cell 
	protected boolean set(int i, int j, Player p) {
		if (board[i][j] == null) {
			board[i][j] = p;
			return true;
		}
		return false;
	}
//Check if the cell is empty or full
	public boolean isEmpty(int i, int j) {
		if (i >= n || j >= m)
			return false;
		if (board[i][j] == null) {
			return true;
		}
		return false;
	}

	public Player get(int i, int j) {
		if (isEmpty(i, j))
			return null;
		return board[i][j];
	}
//The board is full
	public boolean isFull() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (isEmpty(i, j))
					return false;
			}
		}
		return true;
	}

	public String toString() {
		String s = new String();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (isEmpty(i, j))
					s += ".";
				else
					s += board[i][j].getMark();
			}
			s += "\n";
		}
		return s;
	}
//Returns the max line contains the mark of the player in a row
	protected int maxLineContaining(int i, int j) {
		int max = 1, count = 1;
		int k, r = i, c = j;
		// n rows going over the entire column
		if(isEmpty(i,j))
			return 0;
		//Check the column
		for (k = i-1; k >= 0; k--) {
			if (!isEmpty(k, j) && board[k][j].getMark() == board[i][j].getMark())
				max++;
			else
				break;
		}
		for (k = i+1; k < n; k++) {
			if (!isEmpty(k, j) && board[k][j].getMark() == board[i][j].getMark())
				max++;
			else
				break;
		}
		//Check the row
		for (k = j-1; k >= 0; k--) {
			if (!isEmpty(i, k)&&board[i][k].getMark() == board[i][j].getMark())
				count++;
			else
				break;
		}
		for (k = j+1; k < m; k++) {
			if (!isEmpty(i, k)&&board[i][k].getMark() == board[i][j].getMark())
				count++;
			else
				break;
		}
		if (count > max)
			max = count;
		count = 1;
		//Check the diagonal primary
		r=i-1;
		c=j-1;
		while (r >= 0 && c >= 0) {
			if (!isEmpty(r, c)&&board[r][c].getMark() == board[i][j].getMark())
				count++;
			else
				break;
			r--;
			c--;
		}
		r=i+1;
		c=j+1;
		while (r < n && c < m) {
			if (!isEmpty(r, c)&&board[r][c].getMark() == board[i][j].getMark())
				count++;
			else
				break;
			r++;
			c++;
		}
		
		if (count > max)
			max = count;
		count = 1;
		r = i-1;
		c = j+1;
		//Check the diagonal secondary
		while (r >= 0 && c < m) {
			if (!isEmpty(r, c) && board[r][c].getMark() == board[i][j].getMark())
				count++;
			else
				break;
			r--;
			c++;
		}
		r = i+1;
		c = j-1;
		while (r < n && c >= 0) {
			if (!isEmpty(r, c) && board[r][c].getMark() == board[i][j].getMark())
				count++;
			else
				break;
			r++;
			c--;
		}
		if (count > max)
			max = count;
		return max;
	}
}
