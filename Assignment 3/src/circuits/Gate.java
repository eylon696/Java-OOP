package circuits;

public abstract class Gate {
	protected Gate[] inGates;

	public Gate(Gate[] inGates) {
		this.inGates = inGates;
	}

	public boolean calc() throws CircuitException {
		if(inGates==null||inGates.length==0) //Stop conditions to the recursive call
			return this.func(null);
		boolean[] inValues=new boolean[inGates.length];
		for (int i = 0; i < inGates.length; i++) { //filling the inValues array with the corresponding values for each gate
			inValues[i]=inGates[i].calc();		
		}
		return this.func(inValues);
	}

	protected abstract boolean func(boolean[] inValues) throws CircuitException;

	public abstract String getName();

	public abstract Gate simplify();

	public String toString() {
		StringBuilder s =new StringBuilder();
		s.append(this.getName());
		if(inGates.length!=0)
			s.append("[");
		for(int i=0;i<inGates.length;i++) { //
			s.append(inGates[i].toString());
			if(i<inGates.length-1) //Add ", " in case there is at least one more gate to add to the string 
				s.append(", ");
		}
		if(inGates.length!=0)
			s.append("]");
		return s.toString();

	}
}


