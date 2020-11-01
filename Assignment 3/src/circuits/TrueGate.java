package circuits;

public class TrueGate extends Gate {
	private static TrueGate instance = null;
	
	private TrueGate() {
		super(new Gate[] {});
	}

	@Override
	public String getName() {
		return "T";
	}

	@Override
	public Gate simplify() {
		return this;
	}

	@Override
	protected boolean func(boolean[] inValues)  {
		return true;
	}
	//Creating a one instance of true gate in case the gate wasn't created yet
	public static Gate instance() {
		if(instance == null) {
			instance = new TrueGate();
		}
		return instance;
	}

}
