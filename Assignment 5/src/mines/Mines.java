package mines;

import java.util.ArrayList;
import java.util.List;

public class Mines {
	private static int height, width, numMines;
	private static Location[][] minesSweeper;
	private boolean showAll = false;

	public Mines(int height, int width, int numMines) {
		this.height = height;
		this.width = width;
		this.numMines = numMines;
		minesSweeper = new Location[height][width];
		for (int i = 0; i < height; i++)// Filling the matrix with locations
			for (int j = 0; j < width; j++)
				minesSweeper[i][j] = new Location(i, j);
	}

	public boolean addMine(int i, int j) {
		if (minesSweeper[i][j].mine == true)// If already a mine return false
			return false;
		minesSweeper[i][j].mine = true;
		numMines++;
		for (Location l : SingleLocation.myNeighbours(i, j))// To each neighbor of the mine increase it's nearMines
			l.nearMines++;
		return true;
	}

//Open recursively each location if the neighbours are not mines  
	public boolean open(int i, int j) {
		if (minesSweeper[i][j].mine)
			return false;
		minesSweeper[i][j].open = true;
		if (minesSweeper[i][j].nearMines == 0) {
			for (Location l : SingleLocation.myNeighbours(i, j))
				if (!l.open)
					open(l.x, l.y);
		}
		return true;
	}

	// Switch the flag state
	public void toggleFlag(int x, int y) {
		if (minesSweeper[x][y].flag == true)
			minesSweeper[x][y].flag = false;
		else
			minesSweeper[x][y].flag = true;

	}

//Check if all the non mine locations are open
	public boolean isDone() {
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (minesSweeper[i][j].mine != true && minesSweeper[i][j].open != true) {
					return false;
				}
			}
		}
		return true;
	}

	public String get(int i, int j) {
		StringBuilder s = new StringBuilder();
		if (showAll == true) { // If true, open the location return its string and close again
			boolean temp = minesSweeper[i][j].open;// Save the original state of the location (open/close)
			minesSweeper[i][j].open = true;
			s.append(minesSweeper[i][j].toString());
			minesSweeper[i][j].open = temp;// Return the original state of the location (open/close)
			return s.toString();
		}
		return minesSweeper[i][j].toString();
	}

	public void setShowAll(boolean showAll) {
		this.showAll = showAll;

	}

	public String toString() {
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				s.append(get(i, j));//Get the specific location's toString
			}
			s.append("\n");
		}
		return s.toString();
	}
//Inner class that describes each location in the minesSweeper
	private class Location {
		private int x, y;
		private boolean mine, flag, open;
		private int nearMines;

		public Location(int x, int y) {
			mine = false;
			open = false;
			flag = false;
			nearMines = 0;
			this.x = x;
			this.y = y;
		}

		public String toString() {
			if (!open && flag)
				return "F";
			else if (!open && !flag)
				return ".";
			else if (open && mine)
				return "X";
			else if (nearMines == 0)
				return " ";
			return "" + nearMines;
		}

	}
//Inner class that returns a list of neighbours for a specific location
	private static class SingleLocation {
		public static List<Location> myNeighbours(int i, int j) {
			List<Location> neighbours = new ArrayList<>();
			if (i - 1 >= 0 && j - 1 >= 0)// Top Left
				neighbours.add(minesSweeper[i - 1][j - 1]);
			if (i - 1 >= 0)// Top Middle
				neighbours.add(minesSweeper[i - 1][j]);
			if (i - 1 >= 0 && j + 1 < width)// Top Right
				neighbours.add(minesSweeper[i - 1][j + 1]);
			if (j - 1 >= 0)// Middle Left
				neighbours.add(minesSweeper[i][j - 1]);
			if (j + 1 < width)// Middle Right
				neighbours.add(minesSweeper[i][j + 1]);
			if (i + 1 < height && j - 1 >= 0)// Bottom Left
				neighbours.add(minesSweeper[i + 1][j - 1]);
			if (i + 1 < height)// Bottom Middle
				neighbours.add(minesSweeper[i + 1][j]);
			if (i + 1 < height && j + 1 < width)// Bottom Right
				neighbours.add(minesSweeper[i + 1][j + 1]);
			return neighbours;

		}
	}
}
