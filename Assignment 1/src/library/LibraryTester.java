// 311854861
package library;

import util.Tester;

//----------------------------------------------------
// Class skeletons for checking.

class AuthorSk {
	public AuthorSk(String name, int birthYear) {
	}
	
	public String getName() {
		return null;
	}
	
	public int getBirthYear() {
		return 0;
	}
	
	public int getAge(int thisYear) {
		return 0;
	}
	
	public String toString() {
		return null;
	}
}

class BookSk {
	public BookSk(String title, Author auth) {
	}
	
	public String getTitle() {
		return null;
	}
	
	public String getAuthorName() {
		return null;
	}
	
	public int getAuthorBirthYear() {
		return 0;
	}
	
	public String toString() {
		return null;
	}
}

class LibrarySk {
	public LibrarySk(int size) {
	}
	
	public void setBook(int bookNum, String title, Author auth) {
	}
	
	public Book getBook(int bookNum) {
		return null;
	}
}


public class LibraryTester extends Tester {

	public void testStructures() {
		testEqualClasses(Author.class, AuthorSk.class);
		testEqualClasses(Book.class, BookSk.class);
		testEqualClasses(Library.class, LibrarySk.class);
	}


	public void testAuthor() {
		initPublishedTest("basic test");
		Author a = new Author("Miguel de Cervantes", 1547);
		checkEq(a.getName(), "Miguel de Cervantes", "getName()");
		checkEq(a.getBirthYear(), 1547, "getBirthYear()");
		checkEq(a.getAge(2019), 472, "getAge");
		checkEqStr(a, "Miguel de Cervantes(1547)", "toString()");
	}

	public void testBook() {
		initPublishedTest("basic test");
		Author a = new Author("Miguel de Cervantes", 1547);
		Book b = new Book("Don Quixote", a);
		checkEq(b.getAuthorName(), "Miguel de Cervantes", "getAuthorName()");
		checkEq(b.getAuthorBirthYear(), 1547, "getAuthorBirthYear()");
		checkEq(b.getTitle(), "Don Quixote", "getTitle()");
		checkEqStr(b, "Don Quixote written by Miguel de Cervantes(1547)", "toString");
	}

	public void testLibrary() {
		initPublishedTest("basic test");
		Library l = new Library(3);		
		Author a1 = new Author("Miguel de Cervantes", 1547);
		Author a2 = new Author("Nikolai Gogol", 1809);
		l.setBook(1, "Don Quixote", a1);
		l.setBook(0, "Dead Souls", a2);
		checkEqStr(l.getBook(0), "Dead Souls written by Nikolai Gogol(1809)", "getBook(0)");
		checkEqStr(l.getBook(1), "Don Quixote written by Miguel de Cervantes(1547)", "getBook(1)");
	}
		
	
	// ------------------------------------------------------------

	public static void main(String[] args) {
		new LibraryTester().myMain();
	}

	// ------------------------------------------------------------
	// Here you can choose which tests to run.

	public void myTests() {
		testStructures();
		testAuthor();
		testBook();
		testLibrary();
	}
}
