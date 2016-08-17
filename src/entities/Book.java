package entities;

public class Book {

	int book_id;
	String title;
	String publisher;
	long ISBN;
	String format; 
	String collocation;
	String branch_id;
	String author_last;
	String author_first;
	String role;
	int author_id;
	boolean available;
	
	
	public String[] toArray() {
		String[] bookArray = {book_id +"", title, publisher, ISBN +"", format, 
				collocation, branch_id, author_last, author_first, role, available +""};
		return bookArray; 
	}
	public String[] toArrayWoAuthor() {
		String[] bookArray = {book_id +"", title, publisher, ISBN +"", format, 
				collocation, branch_id, available +""};
		return bookArray; 
	}
	
	public int getBook_id() {
		return book_id;
	}
	public void setBook_id(int book_id) {
		this.book_id = book_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public long getISBN() {
		return ISBN;
	}
	public void setISBN(long iSBN) {
		ISBN = iSBN;
	}
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	public String getCollocation() {
		return collocation;
	}
	public void setCollocation(String collocation) {
		this.collocation = collocation;
	}
	public String getBranch_id() {
		return branch_id;
	}
	public void setBranch_id(String branch_id) {
		this.branch_id = branch_id;
	}
	public String getAuthor_last() {
		return author_last;
	}
	public void setAuthor_last(String author_last) {
		this.author_last = author_last;
	}
	public String getAuthor_first() {
		return author_first;
	}
	public void setAuthor_first(String author_first) {
		this.author_first = author_first;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public int getAuthor_id() {
		return author_id;
	}
	public void setAuthor_id(int author_id) {
		this.author_id = author_id;
	}
	public boolean isAvailable() {
		return available;
	}
	public void setAvailable(boolean available) {
		this.available = available;
	}
}
