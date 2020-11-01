package tree;

import java.util.Scanner;

public class ReversedWords {
//Returns the number of times a string is in the tree in reversed form
	public static int checkReversed() {
		Scanner scanner1 = new Scanner(System.in);
		Node n = new Node();
		int count = 0;
		String s;
		while (true) {
			s = scanner1.next();
			if (s.equals("X"))
				break;
			StringBuilder reverse = new StringBuilder();
			reverse.append(s); // Append the string to reverse
			reverse.reverse(); // Reversing the string
			if (n.num(reverse.toString()) == 0) // Check if the string doesn't exist in the tree
				n.add(s);
			else {// Check if the string exist in the tree
				count++;
				n.add(s);
			}
		}
		scanner1.close();
		return count;
	}
}
