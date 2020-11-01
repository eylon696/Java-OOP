package circuits;

public class NotGate extends Gate {

	public NotGate(Gate in) {
		super(new Gate[] { in });
	}

	@Override
	protected boolean func(boolean[] inValues)  {
		return !inValues[0];
	}

	@Override
	public String getName() {
		return "NOT";
	}

	//Return false gate if true gate and vice verse, otherwise return the grandchild
	@Override
	public Gate simplify() {
		if(inGates[0].simplify()instanceof TrueGate)
			return FalseGate.instance();
		if(inGates[0].simplify()instanceof FalseGate)
			return TrueGate.instance();
		return this;
	}

}
