package panels;

import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import SQL.*;
import entities.*;

public class StaffUserPanel {
	
	JLabel last, first, address, birth, email, branch, search,id;
	JTextField lastField, firstField, addField, birthField, emailField, branchField, searchID, searchLast, idField;
	JButton confirmCreate, confirmUpdate, confirmDelete, searchUpdate, searchDelete;
	JPanel firstLastDate, addPanel;
	DefaultTableModel tableModel;
	JTable resultTable;
	JScrollPane scroll;
	Font f1 = new Font("", Font.PLAIN, 22);
	Font f2 = new Font("", Font.PLAIN, 16);
	
	public JPanel createUserPanel() {
		
		JPanel createUser = new JPanel();
		createUser.setPreferredSize(new Dimension(700, 330));
		firstLastDate = new JPanel();		firstLastDate.setPreferredSize(new Dimension(570,130));
		last = new JLabel("Last Name: ");		lastField = new JTextField(15);		firstLastDate.add(last);		firstLastDate.add(lastField);
		first = new JLabel("First Name:");		firstField = new JTextField(15);	firstLastDate.add(first);		firstLastDate.add(firstField);
		birth = new JLabel("Date of birth: (YYYY/MM/DD)");	birthField = new JTextField(7);		firstLastDate.add(birth);	firstLastDate.add(birthField);
		createUser.add(firstLastDate);
		addPanel = new JPanel();			addPanel.setPreferredSize(new Dimension(670,115));
		address = new JLabel("Address: "); 		addField = new JTextField(26);		addPanel.add(address);		addPanel.add(addField);
		email = new JLabel("E-Mail: "); 		emailField = new JTextField(14);	addPanel.add(email);		addPanel.add(emailField);
		branch = new JLabel("Branch ID");		branchField = new JTextField(3);	addPanel.add(branch);		addPanel.add(branchField);
		createUser.add(addPanel);
		confirmCreate = new JButton("Create User");		confirmCreate.addActionListener(new ButtonListener());
		createUser.add(confirmCreate);

		return createUser;
	}
	
	
	
	public JPanel updateUserPanel() {
		
		JPanel updateUser = new JPanel();		updateUser.setPreferredSize(new Dimension(700, 600));		
		JPanel searchPanel = new JPanel();		searchPanel.setPreferredSize(new Dimension(500, 100));
		search = new JLabel("Search user by ID or Last Name:             ");	search.setFont(f1);
		searchID = new JTextField(4);		searchID.setFont(f1);
		searchLast = new JTextField(10);	searchLast.setFont(f1);
		searchUpdate = new JButton("Search");		searchUpdate.setFont(f1);
		searchUpdate.addActionListener(new ButtonListener());	
		searchPanel.add(search);		searchPanel.add(searchID);		searchPanel.add(searchLast);	searchPanel.add(searchUpdate);
		updateUser.add(searchPanel);
		
		JPanel resultPanel = new JPanel();			
		String[] header = {"ID", "Last Name", "First Name", "Date of Birth", "Address", "Email", "Member Since", "Branch"};
		tableModel = new DefaultTableModel(header, 0);		
		resultTable = new JTable(tableModel){
			private static final long serialVersionUID = 1L;
		@Override public boolean isCellEditable(int row, int column) {return false;}};

		resultTable.getColumnModel().getColumn(0).setPreferredWidth(1);
		resultTable.getColumnModel().getColumn(1).setPreferredWidth(60);
		resultTable.getColumnModel().getColumn(4).setPreferredWidth(60);
		resultTable.getColumnModel().getColumn(5).setPreferredWidth(60);
		resultTable.getColumnModel().getColumn(6).setPreferredWidth(35);
		resultTable.getColumnModel().getColumn(7).setPreferredWidth(5);
		scroll = new JScrollPane(resultTable);			
		scroll.setPreferredSize(new Dimension(680, 170));
		resultPanel.add(scroll);
		updateUser.add(resultPanel);
		
		firstLastDate = new JPanel();		firstLastDate.setPreferredSize(new Dimension(570,130));
		id = new JLabel("Insert the ID of the user to update: ");		id.setFont(f1);
		idField = new JTextField(4);	idField.setFont(f1);
		last = new JLabel("Last Name: ");		last.setFont(f1);
		firstLastDate.add(id); firstLastDate.add(idField);
		lastField = new JTextField(15);		lastField.setFont(f1);
		firstLastDate.add(last);		
		firstLastDate.add(lastField);
		first = new JLabel("First Name:");		first.setFont(f1);
		firstField = new JTextField(15);	firstField.setFont(f1);
		firstLastDate.add(first);		
		firstLastDate.add(firstField);
		updateUser.add(firstLastDate);
		
		addPanel = new JPanel();			addPanel.setPreferredSize(new Dimension(670,115));
		address = new JLabel("Address: "); 		address.setFont(f1);
		addField = new JTextField(26);		addField.setFont(f1);
		addPanel.add(address);		
		addPanel.add(addField);
		email = new JLabel("E-Mail: "); 		email.setFont(f1);
		emailField = new JTextField(14);	emailField.setFont(f1);
		addPanel.add(email);		
		addPanel.add(emailField);
		branch = new JLabel("Branch ID");		branch.setFont(f1);
		branchField = new JTextField(3);	branchField.setFont(f1);
		addPanel.add(branch);		
		addPanel.add(branchField);
		
		updateUser.add(addPanel);
		confirmUpdate = new JButton("Update User");		confirmUpdate.setFont(f1);
		confirmUpdate.addActionListener(new ButtonListener());
		updateUser.add(confirmUpdate);
			
		return updateUser;
	}
	
	public JPanel deleteUserPanel() {
		
		JPanel deleteUser = new JPanel();	deleteUser.setPreferredSize(new Dimension(700, 410));
		JPanel searchPanel = new JPanel();		searchPanel.setPreferredSize(new Dimension(500, 100));
		search = new JLabel("Search user by ID or Last Name:             ");	search.setFont(f1);
		searchID = new JTextField(4);		searchID.setFont(f1);
		searchLast = new JTextField(10);	searchLast.setFont(f1);
		searchUpdate = new JButton("Search");		searchUpdate.setFont(f1);
		searchUpdate.addActionListener(new ButtonListener());	
		searchPanel.add(search);		searchPanel.add(searchID);		searchPanel.add(searchLast);	searchPanel.add(searchUpdate);
		deleteUser.add(searchPanel);
		
		JPanel resultPanel = new JPanel();			
		String[] header = {"ID", "Last Name", "First Name", "Date of Birth", "Address", "Email", "Member Since", "Branch"};
		tableModel = new DefaultTableModel(header, 0);		
		//tableModel.addRow(header);
		resultTable = new JTable(tableModel){
			private static final long serialVersionUID = 1L;
		@Override public boolean isCellEditable(int row, int column) {return false;}};

		resultTable.getColumnModel().getColumn(0).setPreferredWidth(1);
		resultTable.getColumnModel().getColumn(1).setPreferredWidth(60);
		resultTable.getColumnModel().getColumn(4).setPreferredWidth(60);
		resultTable.getColumnModel().getColumn(5).setPreferredWidth(60);
		resultTable.getColumnModel().getColumn(6).setPreferredWidth(35);
		resultTable.getColumnModel().getColumn(7).setPreferredWidth(5);
		scroll = new JScrollPane(resultTable);			
		scroll.setPreferredSize(new Dimension(680, 170));
		resultPanel.add(scroll);
		deleteUser.add(resultPanel);
		
		JPanel deletePanel = new JPanel();		deletePanel.setPreferredSize(new Dimension(570,130));
		id = new JLabel("Insert the ID of the user to update: ");		id.setFont(f1);
		idField = new JTextField(4);	idField.setFont(f1);
		confirmDelete = new JButton("Delete"); confirmDelete.setFont(f1);
		confirmDelete.addActionListener(new ButtonListener());
		deletePanel.add(id);	deletePanel.add(idField);	deletePanel.add(confirmDelete);
		deleteUser.add(deletePanel);
		
		return deleteUser;
	}
	
	public class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
				
			if (e.getSource() == confirmCreate) {
			//Create new user object, set values from input fields, call SQL method to process the insert query
					LibraryUser user = new LibraryUser(null,null,null,null,null);
					//If some attributes are null, insert query will not be executed (null is different from "");
					if (!lastField.getText().isEmpty()) user.setLast_name(lastField.getText());		
					if (!firstField.getText().isEmpty())user.setFirst_name(firstField.getText());
					if (!addField.getText().isEmpty())user.setAddress(addField.getText());
					if (!emailField.getText().isEmpty())user.setEmail(emailField.getText());
					if (!branchField.getText().isEmpty())user.setBranch_id(branchField.getText());
					try {
						SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
						format.setLenient(false);
						Date utilDate = format.parse(birthField.getText());
						java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
						user.setDate_of_birth(sqlDate);
						int status = SQLuser.createUser(user);
						if (status ==1) {
							lastField.setText(""); 
							firstField.setText("");	
							addField.setText("");	
							birthField.setText(""); 
							emailField.setText("");	
							branchField.setText("");				
						}

					} catch (ParseException e1) {
						JOptionPane.showMessageDialog(null, "Error in parsing Date.");
						e1.printStackTrace();
					}
			}
			int userid = 0;
			
			if (e.getSource() == searchUpdate) {
			//Search for user from input ID and/or last name	
					tableModel.setRowCount(0);
					if (!searchID.getText().isEmpty()) userid = Integer.parseInt(searchID.getText());
					String last = searchLast.getText();
					ArrayList<LibraryUser> userList = new ArrayList<LibraryUser>();
					userList = SQLuser.searchUser(userid, last);
					
					for (int i=0; i<userList.size(); i++) {
						tableModel.addRow(userList.get(i).toArray());
					}
			}
			
			if (e.getSource() == confirmUpdate) {
			//Create new user object, set values from input fields, call SQL method to process the update query
			//If some fields are left empty, the update query ignores them	
					int user_id = 0;
					if (!idField.getText().isEmpty()) { // Proceed only when a value is inserted into 'User ID' field
						try {
							user_id = Integer.parseInt(idField.getText());
							LibraryUser u = new LibraryUser();
							u.setUser_id(user_id);
							u.setLast_name(lastField.getText());
							u.setFirst_name(firstField.getText());
							u.setAddress(addField.getText());
							u.setEmail(emailField.getText());
							u.setBranch_id(branchField.getText());
							int status = SQLuser.updateUser(user_id, u);
							if (status == 1) {
								lastField.setText("");
								firstField.setText("");
								addField.setText("");
								emailField.setText("");
								branchField.setText("");					
							}
						} catch (NumberFormatException ex) {JOptionPane.showMessageDialog(null, "ID field requires numeric value.");}
					}
			}
			
			
			if (e.getSource() == confirmDelete) {
					int id = 0;
					if (!idField.getText().isEmpty()) { // Proceed only when a value is inserted into 'User ID' field
						try {
							id = Integer.parseInt(idField.getText());
							int status = SQLuser.deleteUser(id);
							if (status == 1) idField.setText("");
						} catch (NumberFormatException ex) {JOptionPane.showMessageDialog(null, "ID field requires numeric value.");}
					}
			}
		}		
	}
}		
