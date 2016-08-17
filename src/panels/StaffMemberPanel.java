package panels;

import java.awt.*;
import java.awt.event.*;
import java.text.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import SQL.*;
import entities.*;


public class StaffMemberPanel {
	
	JLabel last, first, birth, occupation, salary, branch, search, id;
	JTextField lastField, firstField, birthField, occField, salaryField, branchField, searchID, searchLast, idField;
	JButton confirmCreate, confirmUpdate, confirmDelete, searchUpdate;
	JPanel firstLastDate, addPanel;
	DefaultTableModel tableModel;
	JTable resultTable;
	JScrollPane scroll;
	Font f1 = new Font("", Font.PLAIN, 22);
	
	public JPanel createMemberPanel() {
		
		JPanel createMember = new JPanel();
		createMember.setPreferredSize(new Dimension(700, 330));
		firstLastDate = new JPanel();						firstLastDate.setPreferredSize(new Dimension(570,130));
		last = new JLabel("Last Name: ");					lastField = new JTextField(15);		firstLastDate.add(last);		firstLastDate.add(lastField);
		first = new JLabel("First Name:");					firstField = new JTextField(15);	firstLastDate.add(first);		firstLastDate.add(firstField);
		birth = new JLabel("Date of birth: (YYYY/MM/DD)");	birthField = new JTextField(7);		firstLastDate.add(birth);		firstLastDate.add(birthField);
		createMember.add(firstLastDate);
		addPanel = new JPanel();						addPanel.setPreferredSize(new Dimension(640,115));
		occupation = new JLabel("Occupation: "); 		occField = new JTextField(20);		addPanel.add(occupation);	addPanel.add(occField);
		salary = new JLabel("Salary: "); 				salaryField = new JTextField(8);	addPanel.add(salary);		addPanel.add(salaryField);
		branch = new JLabel("Branch ID");				branchField = new JTextField(3);	addPanel.add(branch);		addPanel.add(branchField);
		createMember.add(addPanel);
		confirmCreate = new JButton("Create Staff Member");		confirmCreate.addActionListener(new ButtonListener());
		createMember.add(confirmCreate);

		return createMember;
	}
	
	public JPanel updateMemberPanel() {
			
		JPanel updateMember = new JPanel();		updateMember.setPreferredSize(new Dimension(700, 600));		
		JPanel searchPanel = new JPanel();		searchPanel.setPreferredSize(new Dimension(550, 100));
		search = new JLabel("Search staff member by ID or Last Name:             ");	search.setFont(f1);
		searchID = new JTextField(4);		searchID.setFont(f1);
		searchLast = new JTextField(10);	searchLast.setFont(f1);
		searchUpdate = new JButton("Search");		searchUpdate.setFont(f1);
		searchUpdate.addActionListener(new ButtonListener());	
		searchPanel.add(search);		searchPanel.add(searchID);		searchPanel.add(searchLast);	searchPanel.add(searchUpdate);
		updateMember.add(searchPanel);
		
		JPanel resultPanel = new JPanel();			
		String[] header = {"ID", "Last Name", "First Name", "Date of Birth", "Occupation", "Salary", "Branch"};
		tableModel = new DefaultTableModel(header, 0);		
		//tableModel.addRow(header);
		resultTable = new JTable(tableModel){
			private static final long serialVersionUID = 1L;
		@Override public boolean isCellEditable(int row, int column) {return false;}};

		resultTable.getColumnModel().getColumn(0).setPreferredWidth(1);
		resultTable.getColumnModel().getColumn(1).setPreferredWidth(60);
		resultTable.getColumnModel().getColumn(4).setPreferredWidth(60);
		resultTable.getColumnModel().getColumn(5).setPreferredWidth(60);
		scroll = new JScrollPane(resultTable);			
		scroll.setPreferredSize(new Dimension(680, 170));
		resultPanel.add(scroll);
		updateMember.add(resultPanel);
		
		firstLastDate = new JPanel();		firstLastDate.setPreferredSize(new Dimension(570,130));
		id = new JLabel("Insert the ID of the user to update: ");		id.setFont(f1);
		idField = new JTextField(4);	idField.setFont(f1);
		firstLastDate.add(id);				firstLastDate.add(idField);
		last = new JLabel("Last Name: ");		last.setFont(f1);			
		lastField = new JTextField(15);			lastField.setFont(f1);
		firstLastDate.add(last);			firstLastDate.add(lastField);
		first = new JLabel("First Name:");		first.setFont(f1);
		firstField = new JTextField(15);		firstField.setFont(f1);
		firstLastDate.add(first);			firstLastDate.add(firstField);
		updateMember.add(firstLastDate);
		
		addPanel = new JPanel();			addPanel.setPreferredSize(new Dimension(640,115));
		occupation = new JLabel("Occupation: "); 	occupation.setFont(f1);
		occField = new JTextField(20);		occField.setFont(f1);
		addPanel.add(occupation);			addPanel.add(occField);
		salary = new JLabel("Salary: "); 		salary.setFont(f1);
		salaryField = new JTextField(8);		salaryField.setFont(f1);
		addPanel.add(salary);				addPanel.add(salaryField);
		branch = new JLabel("Branch ID");		branch.setFont(f1);
		branchField = new JTextField(3);		branchField.setFont(f1);
		addPanel.add(branch);				addPanel.add(branchField);
		updateMember.add(addPanel);
		
		confirmUpdate = new JButton("Update Staff Member");		confirmUpdate.setFont(f1);
		confirmUpdate.addActionListener(new ButtonListener());
		updateMember.add(confirmUpdate);
		
		return updateMember;
	}
	
	public JPanel deleteMemberPanel() {
		
		JPanel deleteMember = new JPanel();		deleteMember.setPreferredSize(new Dimension(700, 400));		
		JPanel searchPanel = new JPanel();		searchPanel.setPreferredSize(new Dimension(550, 100));
		search = new JLabel("Search staff member by ID or Last Name:             ");	search.setFont(f1);
		searchID = new JTextField(4);		searchID.setFont(f1);
		searchLast = new JTextField(10);	searchLast.setFont(f1);
		searchUpdate = new JButton("Search");		searchUpdate.setFont(f1);
		searchUpdate.addActionListener(new ButtonListener());	
		searchPanel.add(search);		searchPanel.add(searchID);		searchPanel.add(searchLast);	searchPanel.add(searchUpdate);
		deleteMember.add(searchPanel);
		
		JPanel resultPanel = new JPanel();			
		String[] header = {"ID", "Last Name", "First Name", "Date of Birth", "Occupation", "Salary", "Branch"};
		tableModel = new DefaultTableModel(header, 0);		
		//tableModel.addRow(header);
		resultTable = new JTable(tableModel){
			private static final long serialVersionUID = 1L;
		@Override public boolean isCellEditable(int row, int column) {return false;}};

		resultTable.getColumnModel().getColumn(0).setPreferredWidth(1);
		resultTable.getColumnModel().getColumn(1).setPreferredWidth(60);
		resultTable.getColumnModel().getColumn(4).setPreferredWidth(60);
		resultTable.getColumnModel().getColumn(5).setPreferredWidth(60);
		scroll = new JScrollPane(resultTable);			
		scroll.setPreferredSize(new Dimension(680, 170));
		resultPanel.add(scroll);
		deleteMember.add(resultPanel);
		
		JPanel deletePanel = new JPanel();		deletePanel.setPreferredSize(new Dimension(570,130));
		id = new JLabel("Insert the ID of the Staff member to delete: ");		id.setFont(f1);
		idField = new JTextField(4);	idField.setFont(f1);
		confirmDelete = new JButton("Delete"); confirmDelete.setFont(f1);
		confirmDelete.addActionListener(new ButtonListener());
		deletePanel.add(id);	deletePanel.add(idField);	deletePanel.add(confirmDelete);
		deleteMember.add(deletePanel);
			
		return deleteMember;
	}
	
	public class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
				
			if (e.getSource() == confirmCreate) {
				
					StaffMember sm = new StaffMember(null,null,null,null);   //If some attributes are null, insert query will not be executed (null is different from "");
					if (!lastField.getText().isEmpty()) sm.setLast_name(lastField.getText());
					if (!firstField.getText().isEmpty())sm.setFirst_name(firstField.getText());
					if (!occField.getText().isEmpty())sm.setOccupation(occField.getText());
					if (!branchField.getText().isEmpty())sm.setBranch_id(branchField.getText());
					try {
						sm.setSalary(Float.parseFloat(salaryField.getText()));
						SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
						format.setLenient(false);
						Date utilDate = format.parse(birthField.getText());
						java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
						sm.setDate_of_birth(sqlDate);
						int status = SQLstaff.createStaffMember(sm);
						if (status ==1) {
							lastField.setText(""); 
							firstField.setText("");	
							occField.setText("");	
							birthField.setText(""); 
							salaryField.setText("");	
							branchField.setText("");				
						}
					} catch (ParseException e1) {
						JOptionPane.showMessageDialog(null, "Error in parsing Date.");
						e1.printStackTrace();
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(null, "Error in parsing Salary.");
						ex.printStackTrace();
					}			
			}
			
			int userid = 0;
			if (e.getSource() == searchUpdate) {
				//Search for user from input ID and/or last name	
					tableModel.setRowCount(0);
					if (!searchID.getText().isEmpty()) userid = Integer.parseInt(searchID.getText());
					String last = searchLast.getText();
					ArrayList<StaffMember> mList = new ArrayList<StaffMember>();
					mList = SQLstaff.searchStaffMember(userid, last);
						
					for (int i=0; i<mList.size(); i++) {
						tableModel.addRow(mList.get(i).toArray());
					}
				}
			
			if (e.getSource() == confirmUpdate) {
			//Create new StaffMember object, set values from input fields, call SQL method to process the update query
			//If some fields are left empty, the update query ignores them	
					int staff_id = 0;
					if (!idField.getText().isEmpty()) { //Proceed only when a value is inserted into 'Staff ID' field
						try { staff_id = Integer.parseInt(idField.getText()); }
							catch (NumberFormatException ex) {JOptionPane.showMessageDialog(null, "Error in parsing Staff ID.");}
						float salary = 0;
						if (!salaryField.getText().isEmpty()) 
							try { salary = Float.parseFloat(salaryField.getText()); }
								catch (NumberFormatException ex) {JOptionPane.showMessageDialog(null, "Error in parsing Salary.");}
						StaffMember s = new StaffMember();
						s.setStaff_id(staff_id);
						s.setLast_name(lastField.getText());
						s.setFirst_name(firstField.getText());
						s.setOccupation(occField.getText());
						s.setSalary(salary);
						s.setBranch_id(branchField.getText());
						int status = SQLstaff.updateStaffMember(staff_id, s);
						if (status == 1) {
							lastField.setText("");
							firstField.setText("");
							occField.setText("");
							salaryField.setText("");
							branchField.setText("");					
						}
					}
			}
			
			if (e.getSource() == confirmDelete) {
					int id = 0;
					if (!idField.getText().isEmpty()) { //Proceed only when a value is inserted into 'Staff ID' field
						id = Integer.parseInt(idField.getText());
						int status = SQLstaff.deleteStaffMember(id);
						if (status == 1) idField.setText("");	
					}
			}	
		}		
	}
}		
