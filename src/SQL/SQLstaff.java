package SQL;

import java.sql.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import entities.*;

public  class SQLstaff {
	
	static Connection conn = null;
	
	//public SQLstaff(){}
	
	public static int createStaffMember(StaffMember m) {
		
		PreparedStatement pst;
		String query = "INSERT INTO staff (last_name, first_name, date_of_birth, occupation, salary, branch_id) "
				+ "VALUES (?,?,?,?,?,?) RETURNING staff_id;";
		int status = 0, staff_id = 0;
		try {
			conn = ConnectionProvider.getCon();
			pst = conn.prepareStatement(query);
			pst.setString(1, m.getLast_name());
			pst.setString(2, m.getFirst_name());
			pst.setDate(3, m.getDate_of_birth());
			pst.setString(4, m.getOccupation());
			pst.setFloat(5, m.getSalary());
			pst.setString(6, m.getBranch_id());
			ResultSet rs = pst.executeQuery();	
			while(rs.next()) staff_id = rs.getInt("staff_id");
			JOptionPane.showMessageDialog(null, "Staff member # " +staff_id+ " inserted successfully!");	
			status=1;
			ConnectionProvider.saveQueryToFile(pst);
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			if (e.getMessage().contains("new row for relation \"staff\" violates check constraint \"staff_salary_check\""))
				JOptionPane.showMessageDialog(null, "Please insert a valid value into \"Salary\" field.");
			if (e.getMessage().contains("violates not-null constraint")) 
				JOptionPane.showMessageDialog(null, "Last name, First name, Occupation and Branch ID fields cannot be empty.");
			if (e.getMessage().contains("is not present in table \"library_branch\"")) 
				JOptionPane.showMessageDialog(null, "The Branch ID field must contain one of the following values: PAN, ERA, BOR, DEW.");
			} 	
		return status;
	}

	
	public static ArrayList<StaffMember> searchStaffMember(int staffid, String last) {
		
		PreparedStatement pst;
		ArrayList<StaffMember> staffList = new ArrayList<StaffMember>();
		
		try {
		//Select staff members from database and create arrayList, which is then returned to be printed on the table
			String select = "SELECT * FROM staff WHERE ";
			String whereStaff = " staff_id = " +staffid;
			String whereLast = " lower(last_name) LIKE lower('" +last+ "%')";
			String query = "";
			if (!last.equals("") && staffid == 0) 	query = select + whereLast; 
			else if (last.equals("") && staffid != 0) 	query = select + whereStaff;
					else query = select + whereStaff + " OR " + whereLast;	
			/*If the query is not created case by case (empty-full, full-empty, full-full) when an id is inserted and the Lastname field is empty 
			/ALL staff members are returned despite the staff_id was specified, since " OR last like '%'" is always true*/
			conn = ConnectionProvider.getCon();
			pst = conn.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				StaffMember member = new StaffMember();
				member.setStaff_id(rs.getInt("staff_id"));
				member.setLast_name(rs.getString("last_name"));
				member.setFirst_name(rs.getString("first_name"));
				member.setDate_of_birth(rs.getDate("date_of_birth"));
				member.setOccupation(rs.getString("occupation"));
				member.setSalary(rs.getFloat("salary"));
				member.setBranch_id(rs.getString("branch_id"));
				staffList.add(member);				
			}			
			
			conn.close();			
			
		} catch (SQLException e) {
			e.printStackTrace();
			if (e.getMessage().contains("is not present in table \"library_branch\"")) 
				JOptionPane.showMessageDialog(null, "The Branch ID field must contain one of the following values: PAN, ERA, BOR, DEW.");
					} 	
		return staffList;
	}
	
	
	public static int updateStaffMember(int staffid, StaffMember m) {
		
		int status=0;
		PreparedStatement pst;
		String check = "SELECT * FROM staff WHERE staff_id = "+staffid;
		String where = " WHERE staff_id = " + staffid;
		
		try {
		// Update selected staff member, if it exists, only with specified values
		// Multiple if statements check whether the value has been specified in the input fields.
			conn = ConnectionProvider.getCon();			
			pst = conn.prepareStatement(check);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				if (!m.getLast_name().isEmpty()) {
					String query = "UPDATE staff SET ";
					query += "last_name = '" +m.getLast_name()+ "'";
					query += where;
					pst = conn.prepareStatement(query);
					pst.executeUpdate();
					ConnectionProvider.saveQueryToFile(pst);
					status =1;
				}
				if (!m.getFirst_name().isEmpty()) {
					String query = "UPDATE staff SET ";
					query += "first_name = '" +m.getFirst_name()+ "'";
					query += where;
					pst = conn.prepareStatement(query);
					pst.executeUpdate();
					ConnectionProvider.saveQueryToFile(pst);
					status =1;
				}
				if (!m.getOccupation().isEmpty()) {
					String query = "UPDATE staff SET ";
					query += "occupation = '" +m.getOccupation()+ "'";
					query += where;
					pst = conn.prepareStatement(query);
					pst.executeUpdate();
					ConnectionProvider.saveQueryToFile(pst);
					status =1;
				}
				if (m.getSalary() != 0) {
					String query = "UPDATE staff SET ";
					query += "salary = " +m.getSalary()+ "";
					query += where;
					pst = conn.prepareStatement(query);
					pst.executeUpdate();
					ConnectionProvider.saveQueryToFile(pst);
					status =1;
				}
				if (!m.getBranch_id().isEmpty()) {
					String query = "UPDATE staff SET ";
					query += "branch_id = '" +m.getBranch_id()+ "'";
					query += where;
					pst = conn.prepareStatement(query);
					pst.executeUpdate();
					ConnectionProvider.saveQueryToFile(pst);
					status =1;
				}
				JOptionPane.showMessageDialog(null, "Staff member was updated correctly.");
			} else JOptionPane.showMessageDialog(null, "Staff member was not found.");
			
			conn.close();			
			
		} catch (SQLException e) {
			e.printStackTrace();
			if (e.getMessage().contains("is not present in table \"library_branch\"")) 
				JOptionPane.showMessageDialog(null, "The Branch ID field must contain one of the following values: PAN, ERA, BOR, DEW.");
		} 	
		
		return status;
	}
	
	
	public static int deleteStaffMember(int staffid) {
		
		int status =0;
		PreparedStatement pst;
		String check = "SELECT * FROM staff WHERE staff_id = "+staffid;
		String query = "DELETE from staff WHERE staff_id = " + staffid;
		
		try {
		// Delete selected staff member
			conn = ConnectionProvider.getCon();	
			pst = conn.prepareStatement(check);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				pst = conn.prepareStatement(query);
				pst.executeUpdate();
				status = 1;		
				JOptionPane.showMessageDialog(null, "Staff member was deleted correctly.");
				ConnectionProvider.saveQueryToFile(pst);
			} else JOptionPane.showMessageDialog(null, "Staff member was not found.");
			conn.close();			
			
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage());
		} 	
		
		return status;
	}
	
	
	public static int staffLogin(int staffid, String last){
		
		int status = 0;
		PreparedStatement pst;
		
		try {
			// Search in database if ID and last name correspond to a staff member
				conn = ConnectionProvider.getCon();			
				pst = conn.prepareStatement("Select staff_id, last_name from staff where staff_id=? and last_name=?");
				pst.setInt(1, staffid);
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
	
	
	public static StaffMember getStaffMember(int staffid){
			
		StaffMember m = new StaffMember();
		PreparedStatement pst;
		try {
			// Return a staff member with selected ID
			conn = ConnectionProvider.getCon();			
			pst = conn.prepareStatement("Select * from staff where staff_id = " + staffid);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				m.setStaff_id(rs.getInt("staff_id"));
				m.setLast_name(rs.getString("last_name"));
				m.setFirst_name(rs.getString("first_name"));
				m.setDate_of_birth(rs.getDate("date_of_birth"));
				m.setOccupation(rs.getString("occupation"));
				m.setSalary(rs.getFloat("salary"));
				m.setBranch_id(rs.getString("branch_id"));
			}
			conn.close();			

		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage());
		}

		return m;
	} 
}
