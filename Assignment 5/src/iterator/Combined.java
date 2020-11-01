package iterator;

//import java.util.Arrays;
import java.util.Iterator;
//import java.util.List;
import java.util.NoSuchElementException;

public class Combined<E> implements Iterable<E> {

	private Iterator<E> first;
	private Iterator<E> second;

	public Combined(Iterable<E> first, Iterable<E> second) {
		this.first = first.iterator();
		this.second = second.iterator();
	}

	private class MyIteratorCombined implements Iterator<E> {
		private int flag = 0;

		@Override
		public boolean hasNext() {
			return first.hasNext() || second.hasNext();
		}

		@Override
		public E next() {
			E val = null;
			if (!hasNext())
				throw new NoSuchElementException();
			if (flag == 0 && first.hasNext()) {
				val = first.next();
				flag = 1;

			} 
			else if (flag==1&&second.hasNext()) {
				val = second.next();
				flag = 0;
			}
			else if(first.hasNext()&&!second.hasNext())
				val=first.next();
			else if(!first.hasNext()&&second.hasNext())
				val=second.next();
			return val;
		}
	}

	@Override
	public Iterator<E> iterator() {
		return new MyIteratorCombined();
	}

//	private static void main(String[] args) {
//		List<String> list1 = Arrays.asList("one", "two", "three");
//		List<String> list2 = Arrays.asList("A", "B", "C", "D", "E");
//
//		Combined<String> c = new Combined<>(list2, list1);
//		for (String s : c)
//			System.out.print(s + " ");
//	}

}
