package Book;

import java.io.Serializable;

public class Book implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7337664851397941789L;
	
	private String title;
	private String authors;
	private double price;
	private String isbn;
	private String genre;
	private int year;
	
	public Book(String title, String authors, double price, String isbn, String genre, int year) {
		this.title = title;
		this.authors = authors;
		this.price = price;
		this.isbn = isbn;
		this.genre = genre;
		this.year = year;
	}
	
	public Book(Book copy) {
		this.title = copy.title;
		this.authors = copy.authors;
		this.price = copy.price;
		this.isbn = copy.isbn;
		this.genre = copy.genre;
		this.year = copy.year;
	}
	
	//ACCESSORS
	
	public String getTitle() {
		return this.title;
	}
	
	public String getAuthors() {
		return this.authors;
	}
	
	public double getPrice() {
		return this.price;
	}
	
	public String getIsbn() {
		return this.isbn;
	}
	
	public String getGenre() {
		return this.genre;
	}
	
	public int getYear() {
		return this.year;
	}
	
	//MUTATORS
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setAuthors(String authors) {
		this.authors = authors;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
	public void setGenre(String genre) {
		this.genre = genre;
	}
	
	public void setYear(int year) {
		this.year = year;
	}
	
	//EQUALS
	
	/**
	 * Equals method
	 * @param an object
	 * @return true if the object has the same state as the calling object, false otherwise
	 */
	public boolean equals(Object o) {
		if (o == null)
			return false;
		if (this.getClass() != o.getClass())
			return false;
		else {
			Book b = (Book)o;
			return (this.title == b.title &&
					this.authors == b.authors &&
					this.price == b.price &&
					this.isbn == b.isbn &&
					this.genre == b.genre &&
					this.year == b.year);
		}
	}
	
	//TOSTRING
	
	/**
	 * toString method
	 * @return a string defining the state of the object.
	 */
	public String toString() {
		return ("=================================="+
				"\nTitle: "+this.title+
				"\nAuthors: "+this.authors+
				"\nPrice: "+this.price+
				"\nISBN: "+this.isbn+
				"\nGenre: "+this.genre+
				"\nYear: "+this.year+"\n");
	}
}
