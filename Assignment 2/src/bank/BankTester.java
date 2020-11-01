// 9406301
package bank;

import util.Tester;

//Class skeletons for checking.
class AccountSk {
	public AccountSk(String name) {
	}

	public int getShekels() {
		return 0;
	}

	public String getName() {
		return null;
	}

	public void add(int amount) {
	}

	public String toString() {
		return null;
	}
}

class ProAccountSk extends Account {
	public ProAccountSk(String name) {
		super(null);
	}
	public void add(int amount) {
	}
	public String toString() {
		return null;
	}
	public static void transfer(ProAccount from, ProAccount to, int amount) {
	}
}

public class BankTester extends Tester {

	public void testStructure() {
		testEqualClasses(Account.class, AccountSk.class);
		testEqualClasses(ProAccount.class, ProAccountSk.class);
	}

	public void testAccount() {
		initPublishedTest(Account.class);
		Account a = new Account("Pinoccio");
		a.add(50);
		a.add(100);
		checkEq(a.getName(), "Pinoccio", "getName()");
		checkEq(a.getShekels(), 150, "after adding 50, and then 100");
		checkEqStr(a, "Pinoccio has 150 shekels", "toString()");
		a.add(-20);
		checkEq(a.getShekels(), 130, "after adding 50, then 100, and then -20");
	}
	
	public void testPro() {
		initPublishedTest(ProAccount.class);
		ProAccount a = new ProAccount("Shimshon");
		ProAccount b = new ProAccount("Yovav");
		a.add(1000);
		ProAccount.transfer(a, b, 100);
		a.add(200);
		ProAccount.transfer(a, b, 50);
	
		checkEqStr(a, "Shimshon has 1050 shekels [1000,900,1100,1050]", "Checking Shimshon");
		checkEqStr(b, "Yovav has 150 shekels [100,150]", "Checking Yovav");
	}


	// ------------------------------------------------------------

	public static void main(String[] args) {
		new BankTester().myMain("bank");
	}

	// ------------------------------------------------------------
	// Here you can choose which tests to run.

	public void myTests() {
		testStructure();
		testAccount();
		testPro();
	}

}
