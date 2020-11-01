package iterator;

public class IteratorToString {

	public static String toString(MyIterator it) {
		StringBuilder s = new StringBuilder();
		s.append("[");
		while (it.hasNext() == true) {
			s.append(it.next() + " ");
		}
		s.append("]");
		s.deleteCharAt(s.length() - 2);
		return s.toString();
	}

}
