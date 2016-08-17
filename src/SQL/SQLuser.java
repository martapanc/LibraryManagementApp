package SQL;

import java.sql.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import entities.*;

public  class SQLuser {
	
	static Connection conn = null;
	
	//public SQLuser(){}
	
	public static int createUser(LibraryUser user) {
	// Create a new user and return his 	
		PreparedStatement pst;
		String query = "INSERT INTO library_user (last_name, first_name, date_of_birth, email, address, branch_id) "
				+ "VALUES (?,?,?,?,?,?) RETURNING user_id";
		int status = 0, user_id = 0;
		try {
			conn = ConnectionProvider.getCon();
			pst = conn.prepareStatement(query);
			pst.setString(1, user.getLast_name());
			pst.setString(2, user.getFirst_name());
			pst.setDate(3, user.getDate_of_birth());
			pst.setString(4, user.getEmail());
			pst.setString(5, user.getAddress());
			pst.setString(6, user.getBranch_id());
			ResultSet rs = pst.executeQuery();		
			while(rs.next()) user_id = rs.getInt("user_id");
			JOptionPane.showMessageDialog(null, "User #" +user_id+ " inserted successfully!");	
			status=1;
			ConnectionProvider.saveQueryToFile(pst);
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			if (e.getMessage().contains("is not present in table \"library_branch\"")) 
				JOptionPane.showMessageDialog(null, "The Branch ID field must contain one of the following values: PAN, ERA, BOR, DEW.");
			if (e.getMessage().contains("violates not-null constraint"))
				JOptionPane.showMessageDialog(null, "Last name, First name, Address and Branch ID fields cannot be empty.");
		} 	
		return status;
	}
	
	
	public static ArrayList<LibraryUser> searchUser(int ID, String last) {
		
		PreparedStatement pst;
		ArrayList<LibraryUser> userList = new ArrayList<LibraryUser>();
		
		try {
		//Select users from database and create arrayList, which is then returned to be printed on the table
			String select = "SELECT * FROM library_user WHERE ";
			String whereUser = " user_id = " +ID;
			String whereLast = " lower(last_name) LIKE lower('" +last+ "%')";
			String query = "";
			if (!last.equals("") && ID == 0) 	query = select + whereLast; 
			else if (last.equals("") && ID != 0) 	query = select + whereUser;
					else query = select + whereUser + " OR " + whereLast;	
			/*If the query is not created case by case (empty-full, full-empty, full-full) when an id is inserted and the Lastname field is empty 
			/ALL users are returned despite the author_id was specified, since " OR last like '%'" is always true*/
			conn = ConnectionProvider.getCon();
			pst = conn.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				LibraryUser user = new LibraryUser();
				user.setUser_id(rs.getInt("user_id"));
				user.setLast_name(rs.getString("last_name"));
				user.setFirst_name(rs.getString("first_name"));
				user.setDate_of_birth(rs.getDate("date_of_birth"));
				user.setAddress(rs.getString("address"));
				user.setEmail(rs.getString("email"));
				user.setMember_since(rs.getDate("member_since"));
				user.setBranch_id(rs.getString("branch_id"));
				userList.add(user);				
			}			
			
			conn.close();			
			
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage());
		} 	
		
		return userList;	
	}
	
	
	public static int updateUser (int userid, LibraryUser user) {
		int status=0;
		PreparedStatement pst;
		String check = "SELECT * FROM library_user WHERE user_id = "+userid;
		String where = " WHERE user_id = " + userid;
		
		try {
		// Update selected user, if it exists, only with specified values
		// Multiple if statements check whether the value has been specified in the input fields.
			conn = ConnectionProvider.getCon();			
			pst = conn.prepareStatement(check);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {			
				if (!user.getLast_name().isEmpty()) {
					String query = "UPDATE library_user SET ";
					query += "last_name = '" +user.getLast_name()+ "'";
					query += where;
					pst = conn.prepareStatement(query);
					pst.executeUpdate();
					ConnectionProvider.saveQueryToFile(pst);
					status =1;
				}
				if (!user.getFirst_name().isEmpty()) {
					String query = "UPDATE library_user SET ";
					query += "first_name = '" +user.getFirst_name()+ "'";
					query += where;
					pst = conn.prepareStatement(query);
					pst.executeUpdate();
					ConnectionProvider.saveQueryToFile(pst);
					status =1;
				}
				if (!user.getAddress().isEmpty()) {
					String query = "UPDATE library_user SET ";
					query += "address = '" +user.getAddress()+ "'";
					query += where;
					pst = conn.prepareStatement(query);
					pst.executeUpdate();
					ConnectionProvider.saveQueryToFile(pst);
					status =1;
				}
				if (!user.getEmail().isEmpty()) {
					String query = "UPDATE library_user SET ";
					query += "email = '" +user.getEmail()+ "'";
					query += where;
					pst = conn.prepareStatement(query);
					pst.executeUpdate();
					ConnectionProvider.saveQueryToFile(pst);
					status =1;
				}
				if (!user.getBranch_id().isEmpty()) {
					String query = "UPDATE library_user SET ";
					query += "branch_id = '" +user.getBranch_id()+ "'";
					query += where;
					pst = conn.prepareStatement(query);
					pst.executeUpdate();
					ConnectionProvider.saveQueryToFile(pst);
					status =1;
				}
				JOptionPane.showMessageDialog(null, "User was updated correctly.");
			} else 	JOptionPane.showMessageDialog(null, "User was not found.");
			conn.close();			
			
		} catch (SQLException e) {
			e.printStackTrace();
			if (e.getMessage().contains("violates foreign key constraint \"library_user_branch_id_fkey\"")) 
				JOptionPane.showMessageDialog(null, "The Branch ID field must contain one of the following values: PAN, ERA, BOR, DEW.");
		} 	
		
		return status;
	}
	
	
	public static int deleteUser(int userid) {
		
		int status =0;
		PreparedStatement pst;
		String check = "SELECT * FROM library_user WHERE user_id = "+userid;
		String query = "DELETE from library_user WHERE user_id = " + userid;
		
		try {
		// Delete selected user, if it exists
			conn = ConnectionProvider.getCon();	
			pst = conn.prepareStatement(check); 
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				pst = conn.prepareStatement(query);
				pst.executeUpdate();
				status = 1;	
				ConnectionProvider.saveQueryToFile(pst);
				JOptionPane.showMessageDialog(null, "User was deleted correctly.");
			} else JOptionPane.showMessageDialog(null, "Selected user was not found.");
			conn.close();			
			
		} catch (SQLException e) {
			e.printStackTrace();
			if (e.getMessage().contains("violates foreign key constraint \"loan_user_id_fkey\" on table \"loan\"")) 
				JOptionPane.showMessageDialog(null, "Selected user cannot be deleted until all books \nhe/she has on loan have been returned.");
		} 	
		
		return status;
	}
	
	
	public static int userLogin(int userid, String last){
		
		int status = 0;
		PreparedStatement pst;
		
		try {
			// Search in database if ID and last name correspond to a user
				conn = ConnectionProvider.getCon();			
				pst = conn.prepareStatement("Select user_id, last_name from library_user where user_id=? and last_name=?");
				pst.setInt(1, userid);
				pst.setString(2, last);
				ResultSet rs = pst.executeQuery();
				if (rs.next()) {
					status = 1;
					JOptionPane.showMessageDialog(null, "Valid login credentials.");
				}
				else JOptionPane.showMessageDialog(null, "Invalid login credentials. Please retry.");
				conn.close();			
				
			} catch (SQLException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		
		return status;
	}
	
	
	public static LibraryUser getUser(int userid){
			
			LibraryUser user = new LibraryUser();
			PreparedStatement pst;
			try {
				// Return a user with selected ID
					conn = ConnectionProvider.getCon();			
					pst = conn.prepareStatement("Select * from library_user where user_id = " + userid);
					ResultSet rs = pst.executeQuery();
					while (rs.next()) {
						user.setUser_id(rs.getInt("user_id"));
						user.setLast_name(rs.getString("last_name"));
						user.setFirst_name(rs.getString("first_name"));
						user.setDate_of_birth(rs.getDate("date_of_birth"));
						user.setAddress(rs.getString("address"));
						user.setEmail(rs.getString("email"));
						user.setMember_since(rs.getDate("member_since"));
						user.setBranch_id(rs.getString("branch_id"));
					}
					
					conn.close();			
					
				} catch (SQLException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			
			return user;
	}

	
	public static ArrayList<LoanBook> getCurrentLoans(int userid) {
		ArrayList<LoanBook> loanList = new ArrayList<LoanBook>();
		PreparedStatement pst;
		String query = "SELECT * FROM loan NATURAL JOIN loan_book NATURAL JOIN book "
				+ "WHERE user_id = " +userid+ " AND return_timestamp > current_timestamp";
		try {
		// Return a the books a user currently has on loan 
				conn = ConnectionProvider.getCon();			
				pst = conn.prepareStatement(query);
				ResultSet rs = pst.executeQuery();
				while (rs.next()) {
					LoanBook b = new LoanBook();
					b.setBook_id(rs.getInt("book_id"));
					b.setLoan_id(rs.getInt("loan_id"));
					b.setTitle(rs.getString("title"));
					b.setLoan_date(rs.getTimestamp("loan_timestamp"));
					b.setReturn_date(rs.getTimestamp("return_timestamp"));
					b.setBranch_id(rs.getString("branch_id"));
					loanList.add(b);
				}
				
				conn.close();			
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
		return loanList;
	}

	
	public static ArrayList<LoanBook> getPastLoans(int userid) {
		ArrayList<LoanBook> loanList = new ArrayList<LoanBook>();
		PreparedStatement pst;
		String query = "SELECT * FROM loan NATURAL JOIN loan_book NATURAL JOIN book "
				+ "WHERE user_id = " +userid
				+ "AND return_timestamp < current_timestamp";
		try {
		// Return a the books a user currently had borrowed in the past
				conn = ConnectionProvider.getCon();			
				pst = conn.prepareStatement(query);
				ResultSet rs = pst.executeQuery();
				while (rs.next()) {
					LoanBook b = new LoanBook();
					b.setBook_id(rs.getInt("book_id"));
					b.setLoan_id(rs.getInt("loan_id"));
					b.setTitle(rs.getString("title"));
					b.setLoan_date(rs.getTimestamp("loan_timestamp"));
					b.setReturn_date(rs.getTimestamp("return_timestamp"));
					b.setBranch_id(rs.getString("branch_id"));
					loanList.add(b);
				}
				
				conn.close();			
				
			} catch (SQLException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		
		return loanList;
	}	
}
