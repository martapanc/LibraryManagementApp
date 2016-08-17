package panels;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import SQL.*;
import app.LibraryManagementApp;

public class StaffPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	JFrame frame;
	JPanel welcome, dataPanel, loanPanel, bookPanel, newUserPanel, newStaffPanel;
	JLabel welcomeLabel, loanLabel, bookLabel, newUserLabel, newStaffLabel;
	JTextArea dataArea;
	JButton back, createLoan, updateLoan, returnLoanedBook, createBook, updateBook, updateAuthor, createUser, updateUser, deleteUser;
	JButton createStaff, updateStaff, deleteStaff;
	Font f1 = new Font("Lucida Handwriting", Font.BOLD, 36);
	Font f2 = new Font("", Font.PLAIN, 22);
	Font f3 = new Font("", Font.PLAIN, 18);
	Font fb = new Font("Verdana", Font.BOLD, 15);
	Dimension d1 = new Dimension (750, 60);
	Dimension d2  = new Dimension (750, 100);
	Dimension b  = new Dimension (160, 90);
	Color bg = Color.decode("#B8D7EC");
	Color hb = Color.decode("#9EB9CB");
	Color green = Color.decode("#32BF49");
	Color blue = Color.decode("#4388FF");
	Color red = Color.decode("#FF4343");
	Color yellow = Color.decode("#FFF343");
	StaffLoanPanel staffLoanPanel = new StaffLoanPanel();
	StaffBookPanel staffBookPanel = new StaffBookPanel();
	StaffUserPanel staffUserPanel = new StaffUserPanel();
	StaffMemberPanel staffMemberPanel = new StaffMemberPanel();
	
	public StaffPanel(int staff_id) {
		setBackground(bg);
		setPreferredSize(new Dimension(800,790));
		welcome = new JPanel();			welcome.setBackground(hb);
		welcome.setPreferredSize(d1);
		welcomeLabel = new JLabel("Welcome to your personal area!");
		welcomeLabel.setFont(f1);
		welcome.add(welcomeLabel);
		
		dataPanel = new JPanel(); 		dataPanel.setBackground(bg);
		dataArea = new JTextArea();		dataArea.setFont(f3);		dataArea.setEditable(false);
		// A query saves the data of the staff member whose ID was passed as parameter when the staffPanel was create
		dataArea.setText(SQLstaff.getStaffMember(staff_id).toString());
		dataPanel.setPreferredSize(new Dimension(750, 220));
		dataPanel.add(dataArea);
			
		loanPanel = new JPanel(); 		loanPanel.setBackground(red);	
		loanPanel.setPreferredSize(d2);
		loanLabel = new JLabel("Loan Operations: "); 				loanLabel.setFont(f2);
		loanPanel.add(loanLabel);
		
		createLoan = new JButton("<html>Create<br>Loan</html>", (new ImageIcon(getClass().getResource("/images/addLoan.png"))));			
		createLoan.addActionListener(new ButtonListener());			createLoan.setPreferredSize(b);			createLoan.setFont(fb);	
		updateLoan = new JButton("<html>Update<br>Loan</html>", (new ImageIcon(getClass().getResource("/images/editLoan.png"))));			
		updateLoan.addActionListener(new ButtonListener());			updateLoan.setPreferredSize(b);			updateLoan.setFont(fb);	
		returnLoanedBook = new JButton("<html>Return<br>Book</html>", (new ImageIcon(getClass().getResource("/images/returnBook.png"))));			
		returnLoanedBook.addActionListener(new ButtonListener());	returnLoanedBook.setPreferredSize(b);	returnLoanedBook.setFont(fb);
		loanPanel.add(createLoan);
		loanPanel.add(updateLoan);
		loanPanel.add(returnLoanedBook);
		
		bookPanel = new JPanel();		bookPanel.setBackground(blue);
		bookPanel.setPreferredSize(d2);
		bookLabel = new JLabel("Book Operations: ");	bookLabel.setFont(f2);
		bookPanel.add(bookLabel);	
		createBook = new JButton("<html>Create<br>Book</html>", (new ImageIcon(getClass().getResource("/images/addBook.png"))));				
		createBook.setPreferredSize(b);		createBook.setFont(fb);	
		createBook.addActionListener(new ButtonListener());
		updateBook = new JButton("<html>Update/<br>Delete<br>Book</html>", (new ImageIcon(getClass().getResource("/images/editBook.png"))));		
		updateBook.setPreferredSize(b);		updateBook.setFont(fb);	
		updateBook.addActionListener(new ButtonListener());
		updateAuthor = new JButton("<html>Update/<br>Delete<br>Author</html>", (new ImageIcon(getClass().getResource("/images/editAuthor.png"))));	
		updateAuthor.setPreferredSize(b);	updateAuthor.setFont(fb);		
		updateAuthor.addActionListener(new ButtonListener());
		bookPanel.add(createBook);
		bookPanel.add(updateBook);
		bookPanel.add(updateAuthor);
		
		newUserPanel = new JPanel();	newUserPanel.setBackground(yellow);	
		newUserPanel.setPreferredSize(d2);
		newUserLabel = new JLabel("User Operations: ");				newUserLabel.setFont(f2);
		newUserPanel.add(newUserLabel);	
		createUser = new JButton("<html>Create<br>User</html>", (new ImageIcon(getClass().getResource("/images/addUser.png"))));			
		createUser.setPreferredSize(b);		createUser.setFont(fb);	
		createUser.addActionListener(new ButtonListener());
		updateUser = new JButton("<html>Update<br>User</html>", (new ImageIcon(getClass().getResource("/images/editUser.png"))));			
		updateUser.setPreferredSize(b);		updateUser.setFont(fb);	
		updateUser.addActionListener(new ButtonListener());
		deleteUser = new JButton("<html>Delete<br>User</html>", (new ImageIcon(getClass().getResource("/images/deleteUser.png"))));			
		deleteUser.setPreferredSize(b);		deleteUser.setFont(fb);	
		deleteUser.addActionListener(new ButtonListener());
		newUserPanel.add(createUser);
		newUserPanel.add(updateUser);
		newUserPanel.add(deleteUser);
		
		
		newStaffPanel = new JPanel();	newStaffPanel.setBackground(green);	
		newStaffPanel.setPreferredSize(d2);
		newStaffLabel = new JLabel("Staff Operations: ");	newStaffLabel.setFont(f2);
		newStaffPanel.add(newStaffLabel);	
		createStaff = new JButton("<html>Create<br>Staff</html>", (new ImageIcon(getClass().getResource("/images/addStaff.png"))));		
		createStaff.setPreferredSize(b);		createStaff.setFont(fb);	
		createStaff.addActionListener(new ButtonListener());
		updateStaff = new JButton("<html>Update<br>Staff</html>", (new ImageIcon(getClass().getResource("/images/editStaff.png"))));		
		updateStaff.setPreferredSize(b);		updateStaff.setFont(fb);	
		updateStaff.addActionListener(new ButtonListener());
		deleteStaff = new JButton("<html>Delete<br>Staff</html>", (new ImageIcon(getClass().getResource("/images/deleteStaff.png"))));		
		deleteStaff.setPreferredSize(b);		deleteStaff.setFont(fb);	
		deleteStaff.addActionListener(new ButtonListener());
		newStaffPanel.add(createStaff);
		newStaffPanel.add(updateStaff);
		newStaffPanel.add(deleteStaff);
		back = new JButton("Back to home", (new ImageIcon(getClass().getResource("/images/back60.png"))));
		back.setFont(f3);
		back.addActionListener(new ButtonListener());
		
		add(welcome);
		add(dataPanel);
		add(loanPanel);
		add(bookPanel);
		add(newUserPanel);
		String occupation = SQLstaff.getStaffMember(staff_id).getOccupation();
		if (occupation.equals("Director") || occupation.equals("System Administrator")) {
		// Only the given categories can create/update/delete new staff members
			add(newStaffPanel);
		} else {
			JPanel emptyPanel = new JPanel(); 
			emptyPanel.setPreferredSize(d2);
			emptyPanel.setBackground(bg);
			add(emptyPanel);
		}
		add(back);
	}
	
	public class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
		// Show a new frame when each button is pressed	
			if (e.getSource() == createLoan) showOperation(staffLoanPanel.createLoanPanel());
			if (e.getSource() == updateLoan) showOperationFont(staffLoanPanel.updateLoanPanel());
			if (e.getSource() == returnLoanedBook) showOperationFont(staffLoanPanel.returnLoanedBookPanel());
			if (e.getSource() == createBook) showOperation(staffBookPanel.createBookPanel());
			if (e.getSource() == updateBook) showOperationFont(staffBookPanel.updateBookPanel());
			if (e.getSource() == updateAuthor) showOperationFont(staffBookPanel.updateAuthorPanel());
			if (e.getSource() == createUser) showOperation(staffUserPanel.createUserPanel());	
			if (e.getSource() == updateUser) showOperationFont(staffUserPanel.updateUserPanel());
			if (e.getSource() == deleteUser) showOperationFont(staffUserPanel.deleteUserPanel());
			if (e.getSource() == createStaff) showOperation(staffMemberPanel.createMemberPanel());
			if (e.getSource() == updateStaff) showOperationFont(staffMemberPanel.updateMemberPanel());
			if (e.getSource() == deleteStaff) showOperationFont(staffMemberPanel.deleteMemberPanel());
			if (e.getSource() == back) LibraryManagementApp.reloadFrame();
		}		
	}
	
	
	public void showOperation(JPanel panel) {
		changeFont(panel,f2);
		frame = new JFrame();
	    frame.getContentPane().add(panel);
	    frame.setLocation(200,100);
	    frame.setResizable(false);
	    frame.pack();
	    frame.setVisible(true);
	}
	
	public void showOperationFont(JPanel panel) {
		frame = new JFrame();
	    frame.getContentPane().add(panel);
	    frame.setLocation(200,100);
	    frame.setResizable(false);
	    frame.pack();
	    frame.setVisible(true);
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
