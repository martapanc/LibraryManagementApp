package panels;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import SQL.SQLloan;
import SQL.SQLuser;
import entities.LoanBook;


public class StaffLoanPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	JFrame frame;
	JPanel welcome, loanPanel, bookPanel, newUserPanel, newStaffPanel;
	JLabel welcomeLabel, loanLabel, bookLabel, newUserLabel, newStaffLabel, searchLoanLabel, returnLabel;
	JTextField loanuserid, searchLoanField, loanField, returnField;
	JTextField[] fields;
	JLabel[] labels;
	DefaultTableModel tableModel;
	JTable resultTable;
	JScrollPane scroll;
	JButton back, createLoan, updateLoan, deleteLoan, createBook, updateBook, deleteBook, createUser, updateUser, deleteUser;
	JButton confirmCreate, confirmUpdate, confirmDelete, check, createStaff, updateStaff, deleteStaff, searchLoan, returnBook;
	JButton plus1Week, plus2Week;
	Font f1 = new Font("Lucida Handwriting", Font.BOLD, 36);
	Font f2 = new Font("", Font.PLAIN, 22);
	Dimension d1 = new Dimension (750, 60);
	Dimension d2  = new Dimension (750, 100);
	
	public JPanel createLoanPanel() { 

		JPanel createLoan = new JPanel();
		createLoan.setPreferredSize(new Dimension(290, 430));
		JLabel idlabel;

		idlabel = new JLabel("User ID: ");
		loanuserid = new JTextField(5);
		check = new JButton ("Check user status"); check.addActionListener(new loanListener());
		confirmCreate = new JButton ("Create Loan"); confirmCreate.addActionListener(new loanListener());
		
		createLoan.add(idlabel);
		createLoan.add(loanuserid);
		createLoan.add(check);
		fields = new JTextField[6];
		labels = new JLabel[6];
		for (int i = 0; i < 6; i++) {
			fields[i] = new JTextField(4);
			labels[i] = new JLabel("Book " + (i+1) + ": ");
			fields[i].setEditable(false);
			createLoan.add(labels[i]);
			createLoan.add(fields[i]);
		}
		createLoan.add(confirmCreate);

		return createLoan;
	}

	
	public JPanel updateLoanPanel() {

		JPanel updateLoanPan = new JPanel();		updateLoanPan.setPreferredSize(new Dimension(700, 430));		
		JPanel searchPanel = new JPanel();		searchPanel.setPreferredSize(new Dimension(500, 100));
		searchLoanLabel = new JLabel("Insert User ID to see active loans: ");		searchLoanLabel.setFont(f2);
		searchLoanField = new JTextField(4);		searchLoanField.setFont(f2);
		searchLoan = new JButton("Search");		searchLoan.setFont(f2);
		searchLoan.addActionListener(new UpdateLoanListener());	
		searchPanel.add(searchLoanLabel);			searchPanel.add(searchLoanField);	
		searchPanel.add(searchLoan);
		updateLoanPan.add(searchPanel);
		
		JPanel resultPanel = new JPanel();			
		String[] header = {"Loan ID", "Book ID", "Title", "Loan Date", "Return Date", "Branch"};
		tableModel = new DefaultTableModel(header, 0);		
		resultTable = new JTable(tableModel){
			private static final long serialVersionUID = 1L;
		@Override public boolean isCellEditable(int row, int column) {return false;}};
		scroll = new JScrollPane(resultTable);	
		scroll.setPreferredSize(new Dimension(680, 170));
		resultPanel.add(scroll);
		updateLoanPan.add(resultPanel);
		
		JPanel updateReturnPan = new JPanel();		updateReturnPan.setPreferredSize(new Dimension(400, 230));
		returnLabel = new JLabel("Insert Loan ID:           ");		returnLabel.setFont(f2);
		returnField = new JTextField(4);	returnField.setFont(f2);
		updateReturnPan.add(returnLabel);		updateReturnPan.add(returnField);
		plus1Week = new JButton ("+ 1 Week");	plus1Week.setFont(f2);
		plus2Week = new JButton ("+ 2 Weeks");	plus2Week.setFont(f2);
		plus1Week.addActionListener(new UpdateLoanListener());
		plus2Week.addActionListener(new UpdateLoanListener());
		updateReturnPan.add(plus1Week);		updateReturnPan.add(plus2Week);
		updateLoanPan.add(updateReturnPan);
			
		return updateLoanPan;
	}
	
	
	public JPanel returnLoanedBookPanel() {

		JPanel returnPan = new JPanel();		returnPan.setPreferredSize(new Dimension(700, 430));		
		JPanel searchPanel = new JPanel();		searchPanel.setPreferredSize(new Dimension(500, 100));
		searchLoanLabel = new JLabel("Insert User ID to see active loans: ");		searchLoanLabel.setFont(f2);
		searchLoanField = new JTextField(4);		searchLoanField.setFont(f2);
		searchLoan = new JButton("Search");		searchLoan.setFont(f2);
		searchLoan.addActionListener(new ReturnListener());	
		searchPanel.add(searchLoanLabel);			searchPanel.add(searchLoanField);	
		searchPanel.add(searchLoan);
		returnPan.add(searchPanel);
		
		JPanel resultPanel = new JPanel();			
		String[] header = {"Loan ID", "Book ID", "Title", "Loan Date", "Return Date", "Branch"};
		tableModel = new DefaultTableModel(header, 0);		
		resultTable = new JTable(tableModel){
			private static final long serialVersionUID = 1L;
		@Override public boolean isCellEditable(int row, int column) {return false;}};
		scroll = new JScrollPane(resultTable);	
		scroll.setPreferredSize(new Dimension(680, 170));
		resultPanel.add(scroll);
		returnPan.add(resultPanel);
		
		JPanel returnBookPan = new JPanel();		returnBookPan.setPreferredSize(new Dimension(600, 230));
		returnLabel = new JLabel("Insert the ID of the loan including the books to return: ");		returnLabel.setFont(f2);
		returnField = new JTextField(4);	returnField.setFont(f2);
		returnBookPan.add(returnLabel);		returnBookPan.add(returnField);
		returnBook = new JButton("Return books");	returnBook.setFont(f2);
		returnBook.addActionListener(new ReturnListener());
		returnBookPan.add(returnBook);
		returnPan.add(returnBookPan);
			
		return returnPan;
	}

	
	public class loanListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			String id = "";
			int userid = 0;
			int remainingBooks = 0;
			
			if (e.getSource() == check) {
				try {
					for (int i = 0; i<6; i++) 
						fields[i].setEditable(false); //Book fields are set non-editable by default
					id = loanuserid.getText();
					userid = Integer.parseInt(id);
					remainingBooks = SQLloan.countUserCurrentLoans(userid);
					for (int i = 0; i<remainingBooks; i++) {//activate only necessary book fields
						fields[i].setEditable(true);
					}
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(frame, "Field User ID requires numeric value.");
				}	
			}
				
			if (e.getSource() == confirmCreate) {
				try {
					id = loanuserid.getText();
					userid = Integer.parseInt(id);
					ArrayList<Integer> idList = new ArrayList<Integer>();
					for (int i=0; i<6; i++) {
						if (!fields[i].getText().isEmpty()){
							idList.add(Integer.parseInt(fields[i].getText()));
							System.out.println(i + ": " + fields[i].getText());
						}
					}	
					if (!idList.isEmpty()) {
						int status = SQLloan.createNewLoan(userid, idList);
						if (status==1) {
							loanuserid.setText("");
							for (int i=0; i<6; i++) {
								fields[i].setText("");
								fields[i].setEditable(false);
							}
						}
					}
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(frame, "Fields Book ID require numeric value.");
				}				
			}
		}		
	}
	
	public class UpdateLoanListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			int userid = 0, loanid = 0, status = 0;
			
			
			if (e.getSource() == searchLoan) {
				tableModel.setRowCount(0);
				if (!searchLoanField.getText().isEmpty()) userid = Integer.parseInt(searchLoanField.getText());
				ArrayList<LoanBook> loanList = SQLuser.getCurrentLoans(userid);
				for (int i=0; i<loanList.size(); i++)
					tableModel.addRow(loanList.get(i).toArray());				
			}
			
			if (e.getSource() == plus1Week) {
				
				if (!returnField.getText().isEmpty()) {	//Proceed only when a loan id is inserted
					loanid = Integer.parseInt(returnField.getText());
					System.out.println(loanid);
					status = SQLloan.addOneWeek(loanid);
					if (status == 1) {
						JOptionPane.showMessageDialog(null, "One week was successfully added!");
						returnField.setText("");
					} else {
						JOptionPane.showMessageDialog(null, "Something went wrong, please retry.");
					}
				} else JOptionPane.showMessageDialog(null, "Please insert a Loan ID.");
			}
			
			if (e.getSource() == plus2Week) {
				
				if (!returnField.getText().isEmpty()) {	//Proceed only when a loan id is inserted
					try {
						loanid = Integer.parseInt(returnField.getText());
						status = SQLloan.addOneWeek(loanid);
						status += SQLloan.addOneWeek(loanid);
						if (status == 2) {
							JOptionPane.showMessageDialog(null, "Two weeks were successfully added!");
							returnField.setText("");
						} else {
							JOptionPane.showMessageDialog(null, "Something went wrong, please retry.");
						}					
					}
					catch (NumberFormatException ex) {JOptionPane.showMessageDialog(null, "Please insert a valid Loan ID.");}
					
				} else JOptionPane.showMessageDialog(null, "Please insert a Loan ID.");
			}
		}
	}
	
	public class ReturnListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			int userid = 0, loanid =0, status=0;
			
			if (e.getSource() == searchLoan) {
				
				tableModel.setRowCount(0);
				if (!searchLoanField.getText().isEmpty()) userid = Integer.parseInt(searchLoanField.getText());
				ArrayList<LoanBook> loanList = SQLuser.getCurrentLoans(userid);
				for (int i=0; i<loanList.size(); i++)
					tableModel.addRow(loanList.get(i).toArray());
			}
			
			if (e.getSource() == returnBook) {
				
				if (!returnField.getText().isEmpty()) { //Proceed only when a loan id is inserted
					try { 
						loanid = Integer.parseInt(returnField.getText()); 
						status = SQLloan.returnBooksInLoan(loanid);
						if (status ==1) returnField.setText("");
					} catch (NumberFormatException ex) {}	
				}	else JOptionPane.showMessageDialog(null, "Please insert a valid Loan ID.");
			}
		}
	}
	
	
	public static void changeFont ( Component component, Font font ){
		component.setFont ( font );
	    if ( component instanceof Container ){
	        for ( Component child : ( ( Container ) component ).getComponents () ){
	            changeFont ( child, font );
	        }
	    }
	}
}
