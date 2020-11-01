package circuits;

import java.util.ArrayList;

public class OrGate extends Gate {

	public OrGate(Gate[] inGates) {
		super(inGates);
	}

	@Override
	public String getName() {
		return "OR";
	}
	// Return true if one of the values are true, false otherwise
	@Override
	protected boolean func(boolean[] inValues) {
		for (int i = 0; i < inValues.length; i++) {
			if (inValues[i] == true)
				return true;
		}
		return false;
	}

	@Override
	public Gate simplify() {
		int i = 0;
		ArrayList<Gate> arrG = new ArrayList<>();// Creating a list array that will contain every gate besides
		                                              // false gate and true gate
		for (i = 0; i < inGates.length; i++) {
			// Converting every double not("expression") to "expression"
			if (inGates[i] instanceof NotGate && inGates[i].inGates[0] instanceof NotGate) {
				inGates[i] = inGates[i].inGates[0].inGates[0];
			}
			Gate gate = inGates[i].simplify();
			if (gate instanceof TrueGate) { // Return true gate if one of the gate is true gate
				return TrueGate.instance();
			}
			// adding only non false gates to our list array
			if (!(gate instanceof FalseGate)) {
				arrG.add(gate);
			}
		}

		if (arrG.size() == 1) {
			return arrG.get(0);
		}
		if (arrG.size() == 0) {
			return FalseGate.instance();
		}

		Gate[] gates = new Gate[arrG.size()];
		for (i = 0; i < gates.length; i++) {
			gates[i] = arrG.get(i);
		}
		return new OrGate(gates);
	}
}
