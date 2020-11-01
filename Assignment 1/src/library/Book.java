package library;

public class Book {
	private String title;
	private Author auth;

	public Book(String title, Author auth) {
		this.title = title;
		this.auth = auth;
	}

	public String getTitle() {
		return title;
	}

	public String getAuthorName() {
		return auth.getName();
	}

	public int getAuthorBirthYear() {
		return auth.getBirthYear();
	}

	public String toString() {
		return title + " written by " + getAuthorName() + "(" + getAuthorBirthYear() + ")";
	}
}
