package equiv;

import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;


public class Equiv<E> {

	private List<HashSet<E>> l = new ArrayList<HashSet<E>>();// generate array of sets from type E

	public void add(E e1, E e2) {

		boolean flag = false;

		for (HashSet<E> h : l) {
			if (h.contains(e1) || h.contains(e2)) {// check if the array contains E e1 or E e2
				// if yes add both of them (HashSet ignore duplicate)
				h.add(e1);
				h.add(e2);
				flag = true;
				break;
			}
		}
		if (!flag) {// if the elements e1 and e2 does not exists in the sets we adding them and
					// adding the sets to the list
			HashSet<E> h = new HashSet<E>();
			h.add(e1);
			h.add(e2);
			l.add(h);
		}
	}

	public boolean are(E e1, E e2) {
		if (e1.equals(e2))// checks if the elements are equals if so they are in the same set
			return true;
		for (HashSet<E> h : l) { // Searching e1 and e2 in any one of the hash sets
			if (h.contains(e1) && h.contains(e2))
				return true;
		}
		return false;
	}
}
