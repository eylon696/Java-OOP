package iterator;

public class MyArray implements MyIterator {
	private int [] arr;
	private int i=0;
	
	//Init Constructor
	public MyArray(int arr[]) {
		this.arr=arr;
	}

	@Override
	public boolean hasNext() {
		if(i<arr.length)
			return true;
		return false;
	}

	@Override
	public int next() {
		if(i<arr.length)
			return arr[i++];//return the cell arr[i] and then inc i
		return arr[i];
	}
	
}
