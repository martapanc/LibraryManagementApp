package SQL;

import java.sql.*;
import java.sql.Date;
import java.util.*;
import javax.swing.*;

public class SQLloan {
	
	static Connection conn = null;
	
	//public SQLloan (){}
	
	public static int countUserCurrentLoans(int user_id) {
		
		PreparedStatement pst;
		String query1 = "SELECT * FROM library_user WHERE user_id= " + user_id;
		String query2 = "SELECT count(*) FROM loan l INNER JOIN loan_book lb ON l.loan_id = lb.loan_id"
			+ " WHERE l.user_id = (?) AND return_timestamp > current_timestamp";

		int count = 0;
		
		try {			
			conn = ConnectionProvider.getCon();
			pst = conn.prepareStatement(query1); //First checks if user exists
			ResultSet res = pst.executeQuery();
			
			if (res.next()) {			
				pst = conn.prepareStatement(query2); //Then counts user's current loans
				pst.setInt(1, user_id);
				ResultSet rs = pst.executeQuery();
				if (rs.next()) 	count = rs.getInt("count");
				if (count < 6) 	JOptionPane.showMessageDialog(null, "User #" + user_id + " can still borrow " + (6-count) + " book(s).");
				else JOptionPane.showMessageDialog(null, "User #" + user_id + " has reached the maximum number of loans.");
				return (6-count); //Returns how many books can still be borrowed
			} else JOptionPane.showMessageDialog(null, "User #" + user_id + " does not exist.");
			
			conn.close();			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;	 
	}
	
	
	public static int createNewLoan(int user_id, ArrayList<Integer> book_ids) {
		
		int status = 0;
		PreparedStatement pst;
		String query1 = "INSERT INTO loan (user_id) values (?) RETURNING loan_id";
		String query2 = "SELECT return_timestamp FROM loan WHERE loan_id=?";
		String query3 = "INSERT INTO loan_book (loan_id, book_id, user_id) VALUES (?,?,?)";
		int loan_id = 0;
		Timestamp return_timestamp = new Timestamp(0);
		try {			
			conn = ConnectionProvider.getCon();
			pst = conn.prepareStatement(query1); //Creates new loan and returns loan_id
			pst.setInt(1, user_id);
			ResultSet rs = pst.executeQuery();
			ConnectionProvider.saveQueryToFile(pst);
			if(rs.next()) loan_id = rs.getInt("loan_id");
			pst = conn.prepareStatement(query2);	//Get return date, which was added after creation
			pst.setInt(1, loan_id);
			rs = pst.executeQuery();
			if (rs.next()) return_timestamp = rs.getTimestamp("return_timestamp");
			for (int i=0; i<book_ids.size(); i++) {
				if (SQLbook.isBookAvailable(book_ids.get(i))) {	//Creates relation loan_book for every book in the loan, after checking it is not already on loan
					pst = conn.prepareStatement(query3);	
					pst.setInt(1, loan_id);
					pst.setInt(2, book_ids.get(i));
					pst.setInt(3, user_id);
					pst.executeUpdate(); 
					JOptionPane.showMessageDialog(null, "Book #" +book_ids.get(i)+ " was added successfully!");
					status =1;
					ConnectionProvider.saveQueryToFile(pst);
				} else {JOptionPane.showMessageDialog(null, "Book #" + book_ids.get(i) + " is already on loan.");}
				
			}
			
			
			conn.close();			
			
		} catch (SQLException exception) {
			exception.printStackTrace();
			if (exception.getMessage().contains("violates foreign key constraint \"loan_book_book_id_fkey\""))
				JOptionPane.showMessageDialog(null, "Please check Book IDs.");
		}
		return status;
	}

	
	public static int addOneWeek(int loanid) {
		int status = 0;
		PreparedStatement pst;
		String check = "SELECT * FROM loan WHERE loan_id=" + loanid;
		String update = "UPDATE loan SET return_timestamp = return_timestamp + interval '7 days' where loan_id =" +loanid;
		try {
			conn = ConnectionProvider.getCon();
			pst = conn.prepareStatement(check);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) { //Update if loan exists
				pst = conn.prepareStatement(update);
				pst.executeUpdate();
				ConnectionProvider.saveQueryToFile(pst);
				status = 1;	
			}
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		
		return status;
	}
	
	public static int returnBooksInLoan(int loanid) {
	//Set return_date = current_date for the selected book
		int status = 0;
		PreparedStatement pst;
		String check = "SELECT * FROM loan WHERE loan_id=" + loanid;
		String query = "UPDATE loan SET return_timestamp = current_timestamp WHERE loan_id =" + loanid;	
		try {
			conn = ConnectionProvider.getCon();
			pst = conn.prepareStatement(check);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) { //Check if loan exists
				pst = conn.prepareStatement(query);
				pst.executeUpdate();
				status = 1;	
				ConnectionProvider.saveQueryToFile(pst);
				JOptionPane.showMessageDialog(null, "Book(s) in loan #" + loanid+ " were returned correctly.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		
		return status;
	}
}
