// 18744739
package tree;

import util.Tester;

//----------------------------------------------------
//Class skeletons for checking.
class NodeSk {
	public NodeSk() {}
	public void add(String s) {}
	public int num(String s) { return 0; }
}

class ReversedWordsSk {
	public ReversedWordsSk() {}
	public static int checkReversed() { return 0; }	
}


public class TreeTester extends Tester {

	void testStructure() {
		testEqualClasses(Node.class, NodeSk.class);
		testEqualClasses(ReversedWords.class, ReversedWordsSk.class);
	}
	
	void testNode() {
		initPublishedTest(Node.class);
		Node n = new Node();
		n.add("a");
		checkEq(n.num("a"), 1, "Node(1)");
		n.add("abc");
		checkEq(n.num("abc"), 1, "Node(2)");
		checkEq(n.num("a"), 1, "Node(3)");
		checkEq(n.num("b"), 0, "Node(4)");
		n.add("abc");
		checkEq(n.num("abc"), 2, "Node(5)");

		Node n2 = new Node();
		n2.add("a");
		checkEq(n2.num("a"), 1, "Node(6)");
	}

	void testReversedWords() {
		initPublishedTest(ReversedWords.class);
		setInput(
				"evil stressed star live raw pupils slipup where raw war rats live madam X");
		checkEq(ReversedWords.checkReversed(), 5,
				"ReversedWords");
	}
	
	// ------------------------------------------------------------

	public static void main(String[] args) {
		new TreeTester().myMain("tree");
	}

	// ------------------------------------------------------------
	// Here you can choose which tests to run.

	public void myTests() {
		testStructure();
		testNode();
		testReversedWords();
	}

}
