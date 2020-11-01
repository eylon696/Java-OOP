// 1571291935
package root;

import util.Tester;

// ----------------------------------------------------
// Class skeleton for checking.

class RooterSk {
	public RooterSk(double precision) {
	}

	public void setPrecision(double precision) {
	}

	public double sqrt(double x) {
		return 0.0;
	}
}


// ----------------------------------------------------
// Here are the actual tests.

public class RooterTester extends Tester {

	public void testStructure() {
		testEqualClasses(Rooter.class, RooterSk.class);
	}

	public void testRooter() {
		initPublishedTest(Rooter.class);
		Rooter r = new Rooter(0.01);
		double r2 = Math.sqrt(2);
		check(Math.abs(r.sqrt(2) - r2) <= 0.011, "precision 0.01, result is not close enough");
		r.setPrecision(0.00001);
		check(Math.abs(r.sqrt(2) - r2) <= 0.000011, "after setPrecision(0.00001), result is not close enough");
	}

	// ------------------------------------------------------------

	public static void main(String[] args) {
		new RooterTester().myMain(); 
	}

	// ------------------------------------------------------------
	// Here you can choose which tests to run.
	
	public void myTests() {
		testStructure();
		testRooter();
	}
}
