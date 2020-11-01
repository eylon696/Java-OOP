package circuits;

public class Or2Gate extends OrGate {

	public Or2Gate(Gate g1, Gate g2) {
		super(new Gate[] { g1, g2 });
	}
}
