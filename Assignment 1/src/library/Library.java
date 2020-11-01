package library;

public class Library {
	private int size;
	private Book[] arr1;

	public Library(int size) {
		this.size = size;
		arr1 = new Book[size];
	}

	public void setBook(int bookNum, String title, Author auth) {
		Book s = new Book(title, auth);
		arr1[bookNum] = s;
	}

	public Book getBook(int bookNum) {
		if (arr1[bookNum] != null)
			return arr1[bookNum];
		else
			return null;
	}

}
