package circuits;

public class FalseGate extends Gate {

	private static FalseGate instance = null;
	
	private FalseGate() {
		super(new Gate[] {});
	}

	@Override
	public String getName() {
		return "F";
	}

	@Override
	public Gate simplify() {
		return this;
	}

	@Override
	protected boolean func(boolean[] inValues)  {
		return false;
	}
	
	//Creating a one instance of false gate in case the gate wasn't created yet
	public static Gate instance() {
		if(instance == null) {
			instance = new FalseGate();
		}
		return instance;
	}

}
