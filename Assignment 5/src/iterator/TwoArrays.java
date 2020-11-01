package iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class TwoArrays implements Iterable<Integer> {
	private int[] a1;
	private int[] a2;

	public TwoArrays(int[] a1, int[] a2) {
		this.a1 = a1;
		this.a2 = a2;
	}

	private class IteratorTwoArr implements Iterator<Integer> {
		private int i = 0;
		private int count1 = 0, count2 = 0;
		private int flag = 0;

		@Override
		public boolean hasNext() {

			return i < a1.length + a2.length;
		}

		@Override
		public Integer next() {
			int val = 0;
			if (!hasNext())
				throw new NoSuchElementException();
			if (flag == 0 && count1 < a1.length) {
				val = a1[count1];
				count1++;
				flag = 1;
			} else if (count2 < a2.length) {
				val = a2[count2];
				count2++;
				flag = 0;
			} else if (count1 < a1.length && !(count2 < a2.length)) {
				val = a1[count1];
				count1++;
			} else if (!(count1 < a1.length) && count2 < a2.length) {
				val = a2[count2];
				count2++;
			}
			i++;
			return val;
		}

	}

	@Override
	public Iterator<Integer> iterator() {
		return new IteratorTwoArr();
	}

//	private static void main(String[] args) {
//		int[] a1 = { 1, 2, 3, 4 };
//		int[] a2 = { 100, 101, 102, 103, 104, 105, 106 };
//
//		TwoArrays aa = new TwoArrays(a1, a2);
//		for (int i : aa)
//			System.out.print(i + " ");
//
//	}

}
