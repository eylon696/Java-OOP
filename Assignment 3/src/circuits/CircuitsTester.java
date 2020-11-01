// 1110861
package circuits;

import util.Tester;

//----------------------------------------------------
//Class skeleton for checking.

class CircuitExceptionSk extends Exception {
	public CircuitExceptionSk() {}
}

abstract class GateSk {
	public GateSk(Gate[] inGates) {
	}

	public boolean calc() throws CircuitException {
		return false;
	}

	public abstract Gate simplify();

	protected abstract boolean func(boolean[] inValues) throws CircuitException;

	public abstract String getName();

	public String toString() {
		return null;
	}
}

class AndGateSk extends Gate {
	public AndGateSk(Gate[] inGates) {
		super(null);
	}

	protected boolean func(boolean[] inValues) {
		return false;
	}

	public String getName() {
		return null;
	}

	public Gate simplify() {
		return null;
	}
}

class And2GateSk extends AndGate {
	public And2GateSk(Gate g1, Gate g2) {
		super(null);
	}
}

class OrGateSk extends Gate {
	public OrGateSk(Gate[] inGates) {
		super(null);
	}

	protected boolean func(boolean[] inValues) {
		return false;
	}

	public String getName() {
		return null;
	}

	public Gate simplify() {
		return null;
	}
}

class Or2GateSk extends OrGate {
	public Or2GateSk(Gate g1, Gate g2) {
		super(null);
	}
}

class FalseGateSk extends Gate {
	private FalseGateSk() {
		super(null);
	}

	public static Gate instance() {
		return null;
	}

	protected boolean func(boolean[] inValues) {
		return false;
	}

	public String getName() {
		return null;
	}

	public Gate simplify() {
		return null;
	}
}

class TrueGateSk extends Gate {
	private TrueGateSk() {
		super(null);
	}

	public static Gate instance() {
		return null;
	}

	protected boolean func(boolean[] inValues) {
		return false;
	}

	public String getName() {
		return null;
	}

	public Gate simplify() {
		return null;
	}
}

class VarGateSk extends Gate {
	public VarGateSk(String name) {
		super(null);
	}

	protected boolean func(boolean[] inValues) throws CircuitException {
		return false;
	}

	public String getName() {
		return null;
	}

	public Gate simplify() {
		return null;
	}

	public void setVal(boolean value) {
	}
}

//----------------------------------------------------
//Here are the actual tests.

public class CircuitsTester extends Tester {

	void testStructure() {
		testEqualClasses(Gate.class, GateSk.class);
		testEqualClasses(AndGate.class, AndGateSk.class);
		testEqualClasses(And2Gate.class, And2GateSk.class);
		testEqualClasses(OrGate.class, OrGateSk.class);
		testEqualClasses(Or2Gate.class, Or2GateSk.class);
		testEqualClasses(TrueGate.class, TrueGateSk.class);
		testEqualClasses(FalseGate.class, FalseGateSk.class);
		testEqualClasses(VarGate.class, VarGateSk.class);
		testEqualClasses(CircuitException.class, CircuitExceptionSk.class);
	}

	void testTrueGate() throws CircuitException {
		initPublishedTest(TrueGate.class);
		Gate g = TrueGate.instance();
		Gate g2 = TrueGate.instance();
		check(g == g2, "should be a singleton.");
		checkEq(g.getName(), "T", "getName");
		checkEqStr(g, "T", "toString");
		checkEq(g.calc(), true, "calc");
	}

	void testFalseGate() throws CircuitException {
		initPublishedTest(FalseGate.class);
		Gate g = FalseGate.instance();
		Gate g2 = FalseGate.instance();
		check(g == g2, "should be a singleton.");
		checkEq(g.getName(), "F", "getName");
		checkEqStr(g, "F", "toString");
		checkEq(g.calc(), false, "calc");
	}

	void testAndGate() throws CircuitException {
		initPublishedTest(AndGate.class);
		Gate g = new AndGate(new Gate[] { TrueGate.instance(), FalseGate.instance() });
		checkEq(g.getName(), "AND", "getName");
		checkEqStr(g, "AND[T, F]", "toString");
		checkEq(g.calc(), false, "calc");
	}

	void testOrGate() throws CircuitException {
		initPublishedTest(OrGate.class);
		Gate g = new OrGate(new Gate[] { TrueGate.instance(), FalseGate.instance() });
		checkEq(g.getName(), "OR", "getName");
		checkEqStr(g, "OR[T, F]", "toString");
		checkEq(g.calc(), true, "calc");
	}

	void testNotGate() throws CircuitException {
		initPublishedTest(NotGate.class);
		Gate g = new NotGate(FalseGate.instance());
		checkEq(g.getName(), "NOT", "getName");
		checkEq(g.calc(), true, "calc");
	}

	void testVarGate() throws CircuitException {
		initPublishedTest(VarGate.class);
		VarGate g = new VarGate("blue");
		checkEq(g.getName(), "Vblue", "getName");
		checkThrows(() -> g.calc(), CircuitException.class, "calc() when var not set.");
		g.setVal(true);
		checkEq(g.calc(), true, "check2");
		g.setVal(false);
		checkEq(g.calc(), false, "check3");
	}

	void testCalc() throws CircuitException {
		initPublishedTest("testing calc()");
		VarGate v1 = new VarGate("1");
		VarGate v2 = new VarGate("2");
		Gate g1 = new Or2Gate(FalseGate.instance(), TrueGate.instance());
		Gate g2 = new Or2Gate(v1, new NotGate(v2));
		Gate out = new AndGate(new Gate[] { g1, g2, TrueGate.instance() });
		v1.setVal(false);
		v2.setVal(true);

		checkEqStr(out, "AND[OR[F, T], OR[V1, NOT[V2]], T]", "toString of example circuit wrong.");
		checkEqStr(out.calc(), "false", "calc of example circuit wrong.");
	}

	void testSimplify() {
		initPublishedTest("test simplify()");
		VarGate v1 = new VarGate("1");
		VarGate v2 = new VarGate("2");
		Gate g1 = new Or2Gate(FalseGate.instance(), TrueGate.instance());
		Gate g2 = new Or2Gate(v1, new NotGate(v2));
		Gate out = new AndGate(new Gate[] { g1, g2, TrueGate.instance() });

		checkEqStr(out.simplify(), "OR[V1, NOT[V2]]", "simplify of example circuit wrong.");
		checkEqStr(out, "AND[OR[F, T], OR[V1, NOT[V2]], T]", "circuit should not change because of simplify");

		v1.setVal(false);
		checkEqStr(out.simplify(), "NOT[V2]", "second simplify of example circuit wrong.");
	}

	// ------------------------------------------------------------

	public static void main(String[] args) {
		new CircuitsTester().myMain("circuits"); 
	}

	// ------------------------------------------------------------
	// Here you can choose which tests to run.
	
	public void myTests() throws CircuitException {
		testStructure();
		testTrueGate();
		testFalseGate();
		testAndGate();
		testOrGate();
		testNotGate();
		testVarGate();
		testCalc();
		testSimplify();
	}
}