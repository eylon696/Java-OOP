package graph;

import java.util.HashSet;
import java.util.Collection;

public class Maze implements GraphInterface<Place> {

	private int size;
	private int startx;
	private int starty;
	private int endx;
	private int endy;
	private char[][] maze;

	public Maze(int size, int startx, int starty, int endx, int endy) {
		// Checks if the start and end points are in the range
		if (startx >= size || starty >= size || endx >= size || endy >= size || startx < 0 || starty < 0 || endx < 0
				|| endy < 0)
			throw new IllegalArgumentException();
		maze = new char[size][size];
		this.size = size;
		this.startx = startx;
		this.starty = starty;
		this.endx = endx;
		this.endy = endy;
		// filling all the matrix with dots
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				maze[i][j] = '.';
			}
		}
		maze[startx][starty] = 'S';
		maze[endx][endy] = 'E';
	}

	public boolean addWall(int x, int y) {
		try {// try to create a new place if it works the Place is in the range
			Place w = new Place(x, y, size);
		} catch (IllegalArgumentException e) {
			return false;
		}
		// can't add a wall if the point is start/end point
		if ((x == startx && y == starty) || (x == endx && y == endy))
			return false;
		// if the spot already as a wall don't add 
		if (maze[x][y] == '@')
			return false;
		maze[x][y] = '@';
		return true;
	}

	public String toString() {
		String s = "";
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				s += maze[i][j];
			}
			s += "\n";
		}
		return s;
	}
//Checks if the maze is solvable (has a path from start to end)
	public boolean isSolvable() {
		Graph<Place> g = new Graph<>();
		Place p[][] = new Place[size][size];
		try {
			for (int i = 0; i < size; i++) {//adding all the vertices to the graph
				for (int j = 0; j < size; j++) {
					p[i][j] = new Place(i, j, size); // creating place for vertex
					if (maze[i][j] != '@') {
						g.addVertex(p[i][j]);
					}

				}
			}

			for (int i = 0; i < size; i++) {//adding all the edges to the graph
				for (int j = 0; j < size; j++) {
					if (maze[i][j] != '@') {
						if (i + 1 < size) {
							if (maze[i + 1][j] != '@')
								g.addEdge(p[i][j], p[i + 1][j]);
						}
						if (j + 1 < size) {
							if (maze[i][j + 1] != '@')
								g.addEdge(p[i][j], p[i][j + 1]);
						}
					}
				}
			}
			return g.connected(p[startx][starty], p[endx][endy]);
		} catch (GraphException e) {
			e.printStackTrace();
		}
		return false;
	}

	public Collection<Place> neighbours(Place p) {
		HashSet<Place> c = new HashSet<>();
		//checking if there is a wall in any direction around Place p 
		if (p.getY() - 1 >= 0) {
			if (maze[p.getX()][p.getY() - 1] != '@')
				c.add(new Place(p.getX(), p.getY() - 1, size));
		}
		if (p.getY() + 1 < size) {
			if (maze[p.getX()][p.getY() + 1] != '@')
				c.add(new Place(p.getX(), p.getY() + 1, size));
		}
		if (p.getX() - 1 >= 0) {
			if (maze[p.getX() - 1][p.getY()] != '@')
				c.add(new Place(p.getX() - 1, p.getY(), size));
		}
		if (p.getX() + 1 < size) {
			if (maze[p.getX() + 1][p.getY()] != '@')
				c.add(new Place(p.getX() + 1, p.getY(), size));
		}
		return c;
	}
}
