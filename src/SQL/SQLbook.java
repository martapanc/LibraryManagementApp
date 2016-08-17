package SQL;

import java.sql.*;
import java.util.*;
import javax.swing.*;

import entities.*;

public class SQLbook {
	
	static Connection conn = null;
	
	//public SQLbook (){}
	
	public static int createBook(Book b, ArrayList<Author> aList) {
		
		int status =0;
		PreparedStatement pst;
		//String title = b.getTitle();
		int book_id=0, author_id=0; 		
		
		try {	
			conn = ConnectionProvider.getCon();
			String query1 = "INSERT INTO book (title, publisher, ISBN, collocation, format, branch_id) "
					+ "VALUES (?,?,?,?,?,?) RETURNING book_id";
			pst = conn.prepareStatement(query1); //Adds book to database
			pst.setString(1, b.getTitle());
			pst.setString(2, b.getPublisher());
			pst.setLong(3, b.getISBN());
			pst.setString(4, b.getCollocation());
			pst.setString(5, b.getFormat());
			pst.setString(6, b.getBranch_id());
			ResultSet rs = pst.executeQuery();
			ConnectionProvider.saveQueryToFile(pst);
			while(rs.next()) book_id = rs.getInt("book_id");
			for (int i=0; i<aList.size(); i++) { 	//A book may have more than 1 author
				Author a = aList.get(i);
				String last = a.getLast_name();	
				String first = a.getFirst_name(); 		
				String role = a.getRole();
				String query2 = "SELECT * FROM author WHERE last_name = '" +last+ "' AND first_name= '" +first+ "'";
				pst = conn.prepareStatement(query2);	//Check if author already exists; if it does, save its id
				rs = pst.executeQuery();
				if (rs.next()) {
					author_id = rs.getInt("author_id");
				} else {
					String query3 = "INSERT INTO author (last_name, first_name, role) VALUES (?,?,?) RETURNING author_id";
					pst = conn.prepareStatement(query3);	//Add new author if it does not exist and save its id
					pst.setString(1, last);
					pst.setString(2, first);
					pst.setString(3, role);
					rs = pst.executeQuery();
					ConnectionProvider.saveQueryToFile(pst);
					if (rs.next())	author_id = rs.getInt("author_id");
				}
				String query5 = "INSERT INTO book_author (book_id, author_id) VALUES (?,?)";
				pst = conn.prepareStatement(query5);  //Create new book_author relationship with book and current author 
				pst.setInt(1, book_id);
				pst.setInt(2, author_id);
				pst.executeUpdate();
				ConnectionProvider.saveQueryToFile(pst);
			}	
		JOptionPane.showMessageDialog(null, "Book # "+book_id+ " and its author(s) inserted correctly");	
		status = 1;
		conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage());
		} 
		
		return status;
	}

	public static ArrayList<Book> searchBook(int bookid, String title) {
		
		PreparedStatement pst;
		ArrayList<Book> bookList = new ArrayList<Book>();
		
		try {
		//Select books from database and create arrayList, which is then returned to be printed on the table
			String select = "SELECT * FROM book WHERE ";
			String whereBook = " book_id = " +bookid;
			String whereTitle = " lower(title) LIKE lower('" +title+ "%')";
			String query = "";
			if (!title.equals("") && bookid == 0) 	query = select + whereTitle; 
			else if (title.equals("") && bookid != 0) 	query = select + whereBook;
					else query = select + whereBook + " OR " + whereTitle;	
			/*If the query is not created case by case (empty-full, full-empty, full-full) when an id is inserted and the title field is empty 
			/ALL books are returned despite the book_id was specified, since " OR title like '%'" is always true*/
			conn = ConnectionProvider.getCon();
			pst = conn.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Book b = new Book(); //A new Book object is created
				b.setBook_id (rs.getInt("book_id"));
				b.setTitle (rs.getString("title"));
				b.setPublisher (rs.getString("publisher"));
				b.setISBN (Long.parseLong(rs.getString("ISBN")));
				b.setFormat (rs.getString("format"));
				b.setCollocation (rs.getString("collocation"));
				b.setBranch_id (rs.getString("branch_id"));
				b.setAvailable (isBookAvailable(rs.getInt("book_id")));
				bookList.add(b);				
			}			
			
			conn.close();			
			
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage());
		} 	
		
		return bookList;	
	}

	public static int updateBook (int bookid, Book b) {
		int status=0;
		PreparedStatement pst;
		String where = " WHERE book_id = " + bookid;
		String check = "SELECT * FROM book WHERE book_id=" +bookid;
		
		try {
		// Update selected book, if it exists, only with specified values
		// Multiple if statements check whether the value has been specified in the input fields.
			conn = ConnectionProvider.getCon();			
			pst = conn.prepareStatement(check);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				if (!b.getTitle().isEmpty()) {
					String query = "UPDATE book SET ";
					query += "title = '" +b.getTitle()+ "'";
					query += where;
					pst = conn.prepareStatement(query);
					pst.executeUpdate();
					ConnectionProvider.saveQueryToFile(pst);
					status =1;
				}
				if (!b.getPublisher().isEmpty()) {
					String query = "UPDATE book SET ";
					query += "publisher = '" +b.getPublisher()+ "'";
					query += where;
					pst = conn.prepareStatement(query);
					pst.executeUpdate();
					ConnectionProvider.saveQueryToFile(pst);
					status =1;
				}
				if (!b.getCollocation().isEmpty()) {
					String query = "UPDATE book SET ";
					query += "collocation = '" +b.getCollocation()+ "'";
					query += where;
					pst = conn.prepareStatement(query);
					pst.executeUpdate();
					ConnectionProvider.saveQueryToFile(pst);
					status =1;
				}
				if (b.getISBN() != 0) {
					String query = "UPDATE book SET ";
					query += "ISBN = '" +b.getISBN()+ "'";
					query += where;
					pst = conn.prepareStatement(query);
					pst.executeUpdate();
					ConnectionProvider.saveQueryToFile(pst);
					status =1;
				}
				if (!b.getFormat().isEmpty()) {
					String query = "UPDATE book SET ";
					query += "format = '" +b.getFormat()+ "'";
					query += where;
					pst = conn.prepareStatement(query);
					pst.executeUpdate();
					ConnectionProvider.saveQueryToFile(pst);
					status =1;
				}
				if (!b.getBranch_id().isEmpty()) {
					String query = "UPDATE book SET ";
					query += "branch_id = '" +b.getBranch_id()+ "'";
					query += where;
					pst = conn.prepareStatement(query);
					pst.executeUpdate();
					ConnectionProvider.saveQueryToFile(pst);
					status =1;
				}
				JOptionPane.showMessageDialog(null, "Book was updated correctly.");
			} else JOptionPane.showMessageDialog(null, "Book was not found.");
			conn.close();			
			
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage());
		} 	
		
		return status;
	}
	
	public static int deleteBook(int bookid) {
		
		int status =0;
		PreparedStatement pst;
		String check = "SELECT * from book WHERE book_id=" + bookid;
		String query = "DELETE from book WHERE book_id = " + bookid;
		
		
		try {
		// Delete selected book, if it exists
			conn = ConnectionProvider.getCon();	
			pst = conn.prepareStatement(check);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				
					pst = conn.prepareStatement(query);
					pst.executeUpdate();
					status = 1;		
					JOptionPane.showMessageDialog(null, "Book was deleted correctly.");
				
			} else JOptionPane.showMessageDialog(null, "Book was not found.");
			conn.close();			
			
		} catch (SQLException e) {
			e.printStackTrace();
			if (e.getMessage().toLowerCase().contains("referenced from table \"loan_book\""))
				JOptionPane.showMessageDialog(null, "Book #" +bookid+ " cannot be deleted \nbecause it is currently on loan.");
		} 	
		
		return status;
		
	}
	
	public static ArrayList<Author> searchAuthor(int authorid, String last) {
		
		PreparedStatement pst;
		ArrayList<Author> authorList = new ArrayList<Author>();
		
		try {
		//Select authors from database and create arrayList, which is then returned to be printed on the table
			String select = "SELECT * FROM author WHERE ";
			String whereAuthor = " author_id = " +authorid;
			String whereLast = " lower(last_name) LIKE lower('" +last+ "%')";
			String query = "";
			if (!last.equals("") && authorid == 0) 	query = select + whereLast; 
			else if (last.equals("") && authorid != 0) 	query = select + whereAuthor;
					else query = select + whereAuthor + " OR " + whereLast;	
			/*If the query is not created case by case (empty-full, full-empty, full-full) when an id is inserted and the Lastname field is empty 
			/ALL authors are returned despite the author_id was specified, since " OR last like '%'" is always true*/
			conn = ConnectionProvider.getCon();
			pst = conn.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Author a = new Author();
				a.setAuthor_id(rs.getInt("author_id"));
				a.setLast_name(rs.getString("last_name"));
				a.setFirst_name(rs.getString("first_name"));
				a.setRole(rs.getString("role"));
				authorList.add(a);
			}			
			conn.close();			
			
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage());
		} 	
		
		return authorList;	
	}

	
	public static int updateAuthor (int authorid, Author a) {
		int status=0;
		PreparedStatement pst;
		String check = "SELECT * FROM author WHERE author_id=" + authorid;
		String where = " WHERE author_id = " + authorid;
		
		try {
		// Update selected author, if it exists, only with specified values
		// Multiple if statements check whether the value has been specified in the input fields.
			conn = ConnectionProvider.getCon();			
			pst = conn.prepareStatement(check);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				if (!a.getLast_name().isEmpty()) {
					String query = "UPDATE author SET ";
					query += "last_name = '" +a.getLast_name()+ "'";
					query += where;
					pst = conn.prepareStatement(query);
					pst.executeUpdate();
					status =1;
				}
				if (!a.getFirst_name().isEmpty()) {
					String query = "UPDATE author SET ";
					query += "first_name = '" + a.getFirst_name()+ "'";
					query += where;
					pst = conn.prepareStatement(query);
					pst.executeUpdate();
					status =1;
				}
				if (!a.getRole().isEmpty()) {
					String query = "UPDATE author SET ";
					query += "role = '" +a.getRole()+ "'";
					query += where;
					pst = conn.prepareStatement(query);
					pst.executeUpdate();
					status =1;
				}
				JOptionPane.showMessageDialog(null, "Author was updated correctly.");
			} else JOptionPane.showMessageDialog(null, "Author was not found.");
			conn.close();			
			
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage());
		} 	
		
		return status;
	}
	
	
	public static int deleteAuthor(int authorid) {
	// When a book is deleted, its author is kept because he could have written other books	
	// An author may be deleted only when he is not associated to any book
		int status =0;
		PreparedStatement pst;
		String checkExist = "SELECT * from author WHERE author_id=" + authorid;
		String checkRel = "SELECT * from book_author WHERE author_id=" + authorid;
		String query = "DELETE from author WHERE author_id=" + authorid;
		
		try {
		// Delete selected author, if it exists
			conn = ConnectionProvider.getCon();	
			pst = conn.prepareStatement(checkExist);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				pst = conn.prepareStatement(checkRel);
				if (rs.next()) JOptionPane.showMessageDialog(null, "Author #" +authorid+ " cannot be deleted because still associated to a book.");
				else {pst.executeQuery();
					pst = conn.prepareStatement(query);
					pst.executeUpdate();
					status = 1;		
					JOptionPane.showMessageDialog(null, "Author was deleted correctly.");
				}
			} else JOptionPane.showMessageDialog(null, "Author was not found.");
			conn.close();			
			
		} catch (SQLException e) {
			e.printStackTrace();
			if (e.getMessage().contains("is still referenced from table \"book_author\"")) JOptionPane.showMessageDialog(null, "Selected author cannot be deleted because still associated to a book.");
		} 	
		
		return status;
		
	}
	
	
	public static boolean isBookAvailable(int bookid) {
	//If the return timestamp is before the current date and time, the book is available	
		boolean available = false;
		PreparedStatement pst;
		String query = "SELECT book_id, max(return_timestamp) FROM loan NATURAL JOIN loan_book NATURAL JOIN book "
				+ "WHERE book.book_id = loan_book.book_id AND loan_book.loan_id = loan.loan_id "
				+ "AND book_id = " + bookid+ " GROUP BY book_id";
		//max(return_timestamp) -> select the most recent return date in case a book was borrowed more than once
		try {

			conn = ConnectionProvider.getCon();
			pst = conn.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			Timestamp return_date = new Timestamp(0);
			Timestamp current_date = new Timestamp(Calendar.getInstance().getTime().getTime());
			if (rs.next()) 	return_date = rs.getTimestamp("max");
			if (return_date.compareTo(current_date) < 0) available = true;				
			conn.close();			
			
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage());
		} 	
		
		return available;
	}
}
