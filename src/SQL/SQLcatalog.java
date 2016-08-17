package SQL;

import java.sql.*;
import java.util.*;
import javax.swing.*;

import entities.Book;

public class SQLcatalog {
	
	static Connection conn = null;
	
	//public SQLcatalog (){}
	
	public static ArrayList<Book> searchByParameter(String parameterType, String parameter) {
		
		PreparedStatement pst;
		String query1 = "SELECT * FROM book WHERE LOWER(" +parameterType+ ") LIKE LOWER('%" +parameter+ "%') ORDER BY title;"; //Also look for a portion of the parameter, and ignore case
		String query2 = "SELECT * FROM book NATURAL JOIN book_author NATURAL JOIN author "
			+ " WHERE (?) = book_author.book_id AND book_author.author_id = author.author_id"; //Natural join to find relationships book-author from the selected book_id
		int book_id;
		ArrayList<Book> bookList = new ArrayList<Book>();
		
		try {			
			conn = ConnectionProvider.getCon();
			pst = conn.prepareStatement(query1); //The first query may return more titles
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				book_id = rs.getInt("book_id"); //For each book that was found, the book_id is saved
				pst = conn.prepareStatement(query2);
				pst.setInt(1, book_id);
				ResultSet rs2 = pst.executeQuery(); //A second query searches for each book data, including its author(s)
				
				while (rs2.next()) {
					Book b = new Book(); //A new Book object is created
					b.setBook_id		(rs2.getInt("book_id"));
					b.setTitle			(rs2.getString("title"));
					b.setPublisher		(rs2.getString("publisher"));
					b.setISBN			(Long.parseLong(rs2.getString("ISBN")));
					b.setFormat			(rs2.getString("format"));
					b.setCollocation	(rs2.getString("collocation"));
					b.setBranch_id		(rs2.getString("branch_id"));
					b.setAuthor_last	(rs2.getString("last_name"));
					b.setAuthor_first	(rs2.getString("first_name"));
					b.setRole			(rs2.getString("role"));
					b.setAuthor_id		(rs2.getInt("author_id"));
					b.setAvailable (SQLbook.isBookAvailable(rs2.getInt("book_id")));
					bookList.add(b); //The book is added to a list
				}					
			}
			
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
		}
		
		return bookList;
	}
	
	public static ArrayList<Book> searchByAuthor(String last_name) {
		
		PreparedStatement pst;
		String query1 = "SELECT * FROM author WHERE LOWER(last_name) LIKE LOWER('%" +last_name+ "%') ORDER BY last_name"; //Search for book from author (Book and author are separate entities with a N:M relationship)
		//String query2 = "SELECT * FROM book NATURAL JOIN book_author NATURAL JOIN author"
		//	+ " WHERE (?) = book_author.author_id AND book_author.book_id = book.book_id"; //Natural join to find relationships book-author from the selected book_id
		String query2 = "SELECT * FROM author a INNER JOIN book_author ba ON ba.author_id = a.author_id"
						+" INNER JOIN book b ON ba.book_id = b.book_id"
						+" WHERE a.author_id = (?)";
		int author_id;
		ArrayList<Book> bookList = new ArrayList<Book>();
		
		try {			
			conn = ConnectionProvider.getCon();
			pst = conn.prepareStatement(query1); //The first query may return more authors
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				author_id = rs.getInt("author_id"); //For each author that was found, the author_id is saved
				pst = conn.prepareStatement(query2);
				pst.setInt(1, author_id);
				ResultSet rs2 = pst.executeQuery(); //A second query searches for each book data
				
				while (rs2.next()) {
					Book b = new Book(); //A new Book object is created
					b.setBook_id		(rs2.getInt("book_id"));
					b.setTitle			(rs2.getString("title"));
					b.setPublisher		(rs2.getString("publisher"));
					b.setISBN			(Long.parseLong(rs2.getString("ISBN")));
					b.setFormat			(rs2.getString("format"));
					b.setCollocation	(rs2.getString("collocation"));
					b.setBranch_id		(rs2.getString("branch_id"));
					b.setAuthor_last	(rs2.getString("last_name"));
					b.setAuthor_first	(rs2.getString("first_name"));
					b.setRole			(rs2.getString("role"));
					b.setAuthor_id		(rs2.getInt("author_id"));
					b.setAvailable (SQLbook.isBookAvailable(rs2.getInt("book_id")));
					bookList.add(b); //The book is added to a list
				}	
			}
			
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
		}
		
		return bookList;
	}
}
