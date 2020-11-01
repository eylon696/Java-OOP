// 16673095
package tasks;

import java.util.Arrays;

import util.Tester;

//Class skeletons for checking.
class TasksSk {
	public TasksSk(int num) {
	}

	public boolean dependsOn(int task1, int task2) {
		return false;
	}

	public int[] order() {
		return null;
	}
}

class NamedTasksSk extends Tasks {
	public NamedTasksSk(String[] names) { super(0); }
	public boolean dependsOn(String task1, String task2) { return false; }
	public String[] nameOrder() { return null; }
}

public class TasksTester extends Tester {

	public void testStructure() {
		testEqualClasses(Tasks.class, TasksSk.class);
		testEqualClasses(NamedTasks.class, NamedTasksSk.class);
	}

	private static boolean fulfills(int[] a, int task1, int task2) {		
		for (int i = 0; i < a.length; i++) {
			if (a[i] == task1)
				return false;
			if (a[i] == task2)
				return true;
		}
		return false;
	}

	private void checkDep(int[] a, int task1, int task2) {
		check(fulfills(a, task1, task2),
				": Answer " + Arrays.toString(a) + " does not respect: " + task1 + " depends on " + task2);
	}

	public void testTasks() {
		initPublishedTest(Tasks.class);

		Tasks t = new Tasks(6);
		t.dependsOn(3, 2);
		t.dependsOn(0, 3);
		t.dependsOn(2, 5);
		t.dependsOn(4, 5);

		check(!t.dependsOn(7, 4), "Tasks.dependsOn - not handling illegal output well");

		int[] order = t.order();
		checkDep(order, 3, 2);
		checkDep(order, 0, 3);
		checkDep(order, 2, 5);
		checkDep(order, 4, 5);
	}

	private boolean fulfills(String[] a, String task1, String task2) {
		for (int i = 0; i < a.length; i++) {
			if (a[i].equals(task1))
				return false;
			if (a[i].equals(task2))
				return true;
		}
		return false;
	}

	private void checkDep(String[] a, String task1, String task2) {
		check(fulfills(a, task1, task2),
				": Answer " + Arrays.toString(a) + " does not respect: " + task1 + " dependes on " + task2);
	}

	public void testNamedTasks() {
		initPublishedTest(NamedTasks.class);
		String[] names = { "zero", "one", "two", "three", "four", "five" };
		NamedTasks t = new NamedTasks(names);
		t.dependsOn("three", "two");
		t.dependsOn("one", "three");
		t.dependsOn("two", "five");
		t.dependsOn("four", "five");

		check(!t.dependsOn("four", "brrr"), "NamedTasks.dependsOn - not handling illegal output well");

		String[] order = t.nameOrder();
		checkDep(order, "three", "two");
		checkDep(order, "one", "three");
		checkDep(order, "two", "five");
		checkDep(order, "four", "five");
	}

	// ------------------------------------------------------------

	public static void main(String[] args) {
		new TasksTester().myMain("tasks");
	}

	// ------------------------------------------------------------
	// Here you can choose which tests to run.

	public void myTests() {
		testStructure();
		testTasks();
		testNamedTasks();
	}

}
