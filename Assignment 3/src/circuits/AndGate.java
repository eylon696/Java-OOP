package circuits;

import java.util.ArrayList;

public class AndGate extends Gate {

	// Init constructor
	public AndGate(Gate[] inGates) {
		super(inGates);
	}

	@Override
	public String getName() {
		return "AND";
	}

	// Return false if one of the values are false, true otherwise
	@Override
	protected boolean func(boolean[] inValues) {
		for (int i = 0; i < inValues.length; i++) {
			if (inValues[i] == false)
				return false;
		}
		return true;
	}

	@Override
	public Gate simplify() {
		int i = 0;
		ArrayList<Gate> arrG = new ArrayList<Gate>(); // Creating a list array that will contain every gate besides
														// false gate and true gate
		for (i = 0; i < inGates.length; i++) {
			// Converting every double not("expression") to "expression"
			if (inGates[i] instanceof NotGate && inGates[i].inGates[0] instanceof NotGate) {
				inGates[i] = inGates[i].inGates[0].inGates[0];
			}
			Gate gate = inGates[i].simplify();
			if (gate instanceof FalseGate) { // Return false gate if one of the gate is false gate
				return FalseGate.instance();
			}
			// adding only non true gates to our list array
			if (!(gate instanceof TrueGate)) {
				arrG.add(gate);
			}
		}

		if (arrG.size() == 1) {
			return arrG.get(0);
		}

		if (arrG.size() == 0) {
			return TrueGate.instance();
		}
		Gate[] gates = new Gate[arrG.size()];
		for (i = 0; i < gates.length; i++) {
			gates[i] = arrG.get(i);
		}
		return new AndGate(gates);
	}
}
