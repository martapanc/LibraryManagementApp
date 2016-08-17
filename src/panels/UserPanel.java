package panels;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import SQL.SQLuser;
import app.LibraryManagementApp;
import entities.LoanBook;

public class UserPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	JPanel welcome, dataPanel, currentPanel, pastPanel;
	JLabel welcomeLabel;
	JTextArea dataArea, currentArea, pastArea;
	DefaultTableModel currentTableModel = new DefaultTableModel();
	DefaultTableModel pastTableModel = new DefaultTableModel();
	JTable currentTable, pastTable;
	JScrollPane currentScroll, pastScroll;
	Font f1 = new Font("Lucida Handwriting", Font.BOLD, 36);
	Font f2 = new Font("", Font.PLAIN, 18);
	Dimension d1 = new Dimension (750, 60);
	Dimension d2  = new Dimension (750, 180);
	Dimension d3  = new Dimension (740, 140);
	Dimension d4  = new Dimension (750, 220);
	Dimension d5  = new Dimension (740, 180);
	Color bg = Color.decode("#B8D7EC");
	Color hb = Color.decode("#9EB9CB");
	Color green = Color.decode("#32BF49");
	Color blue = Color.decode("#3636C1");
	
	public UserPanel(int user_id) {
		
		setBackground(bg);
		setPreferredSize(new Dimension(800,790));
		welcome = new JPanel();		
		welcome.setPreferredSize(d1);		welcome.setBackground(hb);
		welcomeLabel = new JLabel("Welcome to your personal area!");
		welcomeLabel.setFont(f1);
		
		dataPanel = new JPanel(); 		dataPanel.setBackground(bg);		
		dataArea = new JTextArea();		dataArea.setFont(f2);		dataArea.setEditable(false);
		// A query saves the data of the user whose ID was passed as parameter when the userPanel was create
		dataArea.setText(SQLuser.getUser(user_id).toString());
		dataPanel.setPreferredSize(new Dimension(750, 220));
		dataPanel.add(dataArea);
		
		currentPanel = new JPanel();	currentPanel.setBackground(blue);
		currentArea = new JTextArea("  Books currently on loan:  ");	currentArea.setFont(f2);	currentArea.setEditable(false);
		currentPanel.setPreferredSize(d2);
		currentPanel.add(currentArea);
		
		
		String[] header = {"Loan ID", "Book ID", "Title", "Loan Date", "Return Date", "Branch"};
		currentTableModel = new DefaultTableModel(header, 0);		
		currentTable = new JTable(currentTableModel){
			private static final long serialVersionUID = 1L;
		@Override public boolean isCellEditable(int row, int column) {return false;}};
		currentTable.getColumnModel().getColumn(2).setPreferredWidth(150);
		ArrayList<LoanBook> currentBooks = SQLuser.getCurrentLoans(user_id);
		for (int i=0; i<currentBooks.size(); i++) {
			currentTableModel.addRow(currentBooks.get(i).toArray());
		}
		currentScroll = new JScrollPane(currentTable);			currentScroll.setPreferredSize(d3);
		currentPanel.add(currentScroll);
		add(currentPanel);
		
		pastPanel = new JPanel();	pastPanel.setBackground(green);
		pastArea = new JTextArea("  Past movements:  "); 	pastArea.setFont(f2);	pastArea.setEditable(false);
		pastPanel.setPreferredSize(d4);
		pastPanel.add(pastArea);
		pastTableModel = new DefaultTableModel(header, 0);		
		pastTable = new JTable(pastTableModel){
			private static final long serialVersionUID = 1L;
		@Override public boolean isCellEditable(int row, int column) {return false;}};
		pastTable.getColumnModel().getColumn(2).setPreferredWidth(150);
		ArrayList<LoanBook> pastBooks = SQLuser.getPastLoans(user_id);
		for (int i=0; i<pastBooks.size(); i++) {
			pastTableModel.addRow(pastBooks.get(i).toArray());
		}
		pastScroll = new JScrollPane(pastTable);			pastScroll.setPreferredSize(d5);
		pastPanel.add(pastScroll);
		add(pastPanel);
		
		JButton back = new JButton("Back to home", (new ImageIcon(getClass().getResource("/images/back60.png"))));
		back.setFont(f2);
		back.addActionListener(new BackListener());
		welcome.add(welcomeLabel);
		add(welcome);
		add(dataPanel);
		add(currentPanel);
		add(pastPanel);
		add(back);	
	}
	
	
	public class BackListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			LibraryManagementApp.reloadFrame();
	    }		
	}
}
