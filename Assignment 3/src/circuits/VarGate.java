package circuits;

public class VarGate extends Gate {
	private String name;
	private boolean value;
	private int flag = 0;

	public VarGate(String name) {
		super(new Gate[] {});
		this.name = name;
	}

	@Override
	protected boolean func(boolean[] inValues) throws CircuitException {
		if (flag == 1)  //If the value has been set return it
			return value;
		throw new CircuitException();
	}

	public void setVal(boolean value) {
		this.value = value;
		flag = 1;
	}

	@Override
	public String getName() {

		return "V" + name;
	}


	@Override
	public Gate simplify() {
		if(flag==0) //If the value has'nt been set yet
			return this;
		if(value==true)
			return TrueGate.instance();
		return FalseGate.instance();
	}
}
