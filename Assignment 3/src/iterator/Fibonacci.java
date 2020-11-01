package iterator;

public class Fibonacci implements MyIterator {
	private int upperBound;
	private int numfib1;//The first number in fibo series
	private int numfib2;//The second number in fibo series
	//Init Constructor
	public Fibonacci(int upperBound) {
		this.upperBound=upperBound;
		numfib1=0;
		numfib2=1;
	}

	
	@Override
	public boolean hasNext() {
		if(numfib2<=upperBound)
			return true;
		return false;
	}

	@Override
	public int next() {
		int temp;
		if(hasNext()==true) {
			if(numfib1==0)
			{
				temp=numfib2;
				numfib2=numfib2+numfib1;
				numfib1=temp;
				return 1;
			}
				
			temp=numfib2;
			numfib2=numfib2+numfib1;
			numfib1=temp;
		}
		return numfib1;	
	}

}
