/*
* 
*/
package panels;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.*;
import SQL.SQLcatalog;
import app.LibraryManagementApp;
import entities.Book;

public class CataloguePanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	JPanel titlePanel, authorPanel, pubPanel, colPanel, branchPanel, space1, space2, resultPanel;
	JLabel catLabel, titleLabel, authorLabel, pubLabel, colLabel, branchLabel, hintLabel;
	JTextField titleField, authorField, pubField, colField, branchField;
	JButton titleSearch, authorSearch, pubSearch, colSearch, branchSearch, back;
	DefaultTableModel tableModel;
	JTable resultTable;
	JScrollPane scroll;
	Font f1 = new Font("", Font.PLAIN, 18);
	Font f2 = new Font("Lucida Handwriting", Font.BOLD, 36);
	Dimension d1 = new Dimension(100,35);
	Color bg = Color.decode("#B8D7EC");
	Color hb = Color.decode("#9EB9CB");
	
	public CataloguePanel() {
		setBackground(bg);
		setPreferredSize(new Dimension(800,790));
		
		space1 = new JPanel(); 
		space1.setPreferredSize(new Dimension (600, 60));	space1.setBackground(bg);
		catLabel = new JLabel("Search in the catalog"); 	catLabel.setFont(f2);
		space1.add(catLabel);
		space2 = new JPanel(); 	space2.setBackground(bg);
		space2.setPreferredSize(new Dimension (600, 15));		
		
		titlePanel = new JPanel();			titlePanel.setBackground(hb);
		titleLabel = new JLabel ("By title:           "); 		titleLabel.setFont(f1);
		titleField = new JTextField (30); 			titleField.setFont(f1);
		titleSearch = new JButton("Search"); 		titleSearch.setPreferredSize(d1);		titleSearch.addActionListener(new SearchListener());
		titlePanel.add(titleLabel);		titlePanel.add(titleField);		titlePanel.add(titleSearch);
		
		authorPanel = new JPanel();			authorPanel.setBackground(hb);
		authorLabel = new JLabel ("By author:       "); 		authorLabel.setFont(f1);
		authorField = new JTextField (30); 			authorField.setFont(f1);
		authorSearch = new JButton("Search"); 		authorSearch.setPreferredSize(d1);		authorSearch.addActionListener(new SearchListener());
		authorPanel.add(authorLabel);		authorPanel.add(authorField);		authorPanel.add(authorSearch);
		
		pubPanel = new JPanel();			pubPanel.setBackground(hb);
		pubLabel = new JLabel ("By publisher:   "); 		pubLabel.setFont(f1);
		pubField = new JTextField (30); 			pubField.setFont(f1);
		pubSearch = new JButton("Search"); 		pubSearch.setPreferredSize(d1);				pubSearch.addActionListener(new SearchListener());
		pubPanel.add(pubLabel);		pubPanel.add(pubField);		pubPanel.add(pubSearch);
		
		colPanel = new JPanel();			colPanel.setBackground(hb);
		colLabel = new JLabel ("By collocation: "); 		colLabel.setFont(f1);
		colField = new JTextField (30); 			colField.setFont(f1);
		colSearch = new JButton("Search"); 		colSearch.setPreferredSize(d1);				colSearch.addActionListener(new SearchListener());
		colPanel.add(colLabel);		colPanel.add(colField);		colPanel.add(colSearch);
		
		branchPanel = new JPanel();			branchPanel.setBackground(hb);
		branchLabel = new JLabel ("By branch:       "); 		branchLabel.setFont(f1);
		branchField = new JTextField (30); 			branchField.setFont(f1);
		branchSearch = new JButton("Search"); 		branchSearch.setPreferredSize(d1);		branchSearch.addActionListener(new SearchListener());
		branchPanel.add(branchLabel);		branchPanel.add(branchField);		branchPanel.add(branchSearch);
		hintLabel = new JLabel ("Ex: PAN, DEW, ERA, BOR.");
		hintLabel.setFont(new Font("", Font.ITALIC, 11));
		branchPanel.setPreferredSize(new Dimension(776,60));
		branchPanel.add(hintLabel);
		
		resultPanel = new JPanel();
		resultPanel.setPreferredSize(new Dimension(755, 355));
		resultPanel.setBorder(BorderFactory.createLineBorder(Color.black));

		String[] header = {"ID", "Title", "Publisher", "ISBN", "Format", "Collocation", "Branch", "Last Name", "First Name", "Role", "Available"};
		tableModel = new DefaultTableModel(header, 0);
		resultTable = new JTable(tableModel){
			private static final long serialVersionUID = 1L;

		@Override public boolean isCellEditable(int row, int column) {return false;}};
		resultTable.getColumnModel().getColumn(0).setPreferredWidth(40);
		resultTable.getColumnModel().getColumn(1).setPreferredWidth(140);
		resultTable.getColumnModel().getColumn(3).setPreferredWidth(40);
		resultTable.getColumnModel().getColumn(5).setPreferredWidth(60);
		resultTable.getColumnModel().getColumn(6).setPreferredWidth(35);
		resultTable.getColumnModel().getColumn(9).setPreferredWidth(60);
		resultTable.getColumnModel().getColumn(10).setPreferredWidth(25);
		scroll = new JScrollPane(resultTable);
		scroll.setPreferredSize(new Dimension(750, 345));
		resultPanel.add(scroll);
		
		back = new JButton("Back to home", (new ImageIcon(getClass().getResource("/images/back60.png"))));
		back.setFont(f1);
		back.addActionListener(new BackListener());
		add(space1);
		add(titlePanel);
		add(authorPanel);
		add(pubPanel);
		add(colPanel);
		add(branchPanel);
		add(space2);
		add(resultPanel);
        add(back);
	}
	
	public class SearchListener implements ActionListener {
		public void actionPerformed(ActionEvent e) { //Run a search when a parameter button is pressed
			
			String parameterType = "", parameter = "";
			ArrayList<Book> bookList = new ArrayList<Book>();
			tableModel.setRowCount(0);
			
			if (e.getSource() == authorSearch) {
				parameter = authorField.getText();
				bookList = SQLcatalog.searchByAuthor(parameter);
			}
			
			if(e.getSource() == titleSearch) { 
				parameterType = "title";
				parameter = titleField.getText();	
				bookList = SQLcatalog.searchByParameter(parameterType, parameter);
			}
			
			if (e.getSource() == pubSearch) {
				parameterType = "publisher";
				parameter = pubField.getText();
				bookList = SQLcatalog.searchByParameter(parameterType, parameter);
			}
			if (e.getSource() == colSearch) {
				parameterType = "collocation";
				parameter = colField.getText();
				bookList = SQLcatalog.searchByParameter(parameterType, parameter);
			}
			if (e.getSource() == branchSearch) 
			{
				parameterType = "branch_id";
				parameter = branchField.getText();
				bookList = SQLcatalog.searchByParameter(parameterType, parameter);
			}
			
			for (int i=0; i<bookList.size(); i++){ //Add results to table
				tableModel.addRow(bookList.get(i).toArray());
			titleField.setText("");
			authorField.setText("");
			pubField.setText("");
			colField.setText("");
			branchField.setText("");
			}		
		}
	}
	
	public class BackListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			LibraryManagementApp.reloadFrame();
	    }		
	}
}
