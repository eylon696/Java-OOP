package tree;

public class Node {
	private int count;
	private Node[] children;
//Init constructor
	public Node() {
		this.children = new Node['z' - 'a' + 1];
		for (int i = 0; i < children.length; i++)
			children[i] = null;
		this.count = 0;
	}

//Returns the number of instances of s 
	public int num(String s) {
		Node temp = this;
		if(s.length()==0)
			return count;
		for (int i = 0; i < s.length(); i++) {
			if (temp.children[s.charAt(i) - 'a'] != null)// char already exist
				temp = temp.children[s.charAt(i) - 'a'];
			else // the char does not exit we need to create new node
				return 0;
		}
		return temp.count;
	}
//Adds the string to the tree
	public void add(String s) {
		Node temp = this;
		for (int i = 0; i < s.length(); i++) {
			if (temp.children[s.charAt(i) - 'a'] != null)// char already exist
				temp = temp.children[s.charAt(i) - 'a'];
			else // the char does not exit we need to create new node
			{
				temp.children[s.charAt(i) - 'a'] = new Node();
				temp = temp.children[s.charAt(i) - 'a'];
			}
		}
			temp.count++;
	}
}
