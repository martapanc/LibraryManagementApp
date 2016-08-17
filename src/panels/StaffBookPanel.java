package panels;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import SQL.*;
import entities.*;

public class StaffBookPanel {
	
	JLabel titleLab, pubLab, ISBNLab, colLab, formatLab, branchLab, authorHeader, searchBook, searchAuthor;
	JLabel updateID, deleteID, lastLab, firstLab, roleLab;
	JPanel space1, space2;
	JTextField titleField, pubField, ISBNField, colField, formatField, branchField, updateIDField, deleteIDField;
	JTextField searchBookidField, searchTitleField, searchAuthoridField, searchLastnameField, lastField, firstField, roleField;
	JButton confirmCreate, searchUpdBook, confirmBookUpdate, confirmBookDelete, searchUpdAuthor, confirmAuthorUpdate, confirmAuthorDelete;
	JLabel[] author = new JLabel[4];
	JTextField[] last, first, role;;
	Font f1 = new Font("", Font.PLAIN, 22);
	DefaultTableModel bookTableModel, authorTableModel;
	JTable bookResultTable, authorResultTable;
	JScrollPane bookScroll, authorScroll;
	
	public JPanel createBookPanel() {
		
		JPanel createBook = new JPanel();
		createBook.setPreferredSize(new Dimension(640, 600));
		space1 = new JPanel();		space1.setPreferredSize(new Dimension(630, 25)); 	createBook.add(space1);
		space2 = new JPanel();		space2.setPreferredSize(new Dimension(630, 20));
		titleLab = new JLabel("Title:"); 		titleField = new JTextField(24);	createBook.add(titleLab); 	createBook.add(titleField);
		pubLab = new JLabel("Publisher:"); 		pubField = new JTextField(22);		createBook.add(pubLab);		createBook.add(pubField);
		ISBNLab = new JLabel("ISBN:"); 			ISBNField = new JTextField(10);		createBook.add(ISBNLab);	createBook.add(ISBNField);
		branchLab = new JLabel("Branch ID:"); 	branchField = new JTextField(8);	createBook.add(branchLab);	createBook.add(branchField);
		colLab = new JLabel("Collocation:"); 	colField = new JTextField(8);		createBook.add(colLab);		createBook.add(colField);
		formatLab = new JLabel("Format:"); 		formatField = new JTextField(8);	createBook.add(formatLab);	createBook.add(formatField);
		createBook.add(space2);
		author = new JLabel[4];
		last = new JTextField[4];	first = new JTextField[4];		role = new JTextField[4];
		authorHeader = new JLabel("                     Last Name       First Name         Role                 ");
		createBook.add(authorHeader);
		for (int i=0; i<4; i++) {
			author[i] = new JLabel("Author " + (i+1));
			last[i] = new JTextField(7);
			first[i] = new JTextField(7);
			role[i] = new JTextField(7);
			createBook.add(author[i]); 	createBook.add(last[i]);	createBook.add(first[i]);	createBook.add(role[i]);
		}
		confirmCreate = new JButton("Add book");		confirmCreate.addActionListener(new CreateListener());
		createBook.add(confirmCreate);
		return createBook;
	}
	
	public JPanel updateBookPanel() {
			
		JPanel updateBookPan = new JPanel();		updateBookPan.setPreferredSize(new Dimension(700, 700));		
		JPanel searchPanel = new JPanel();		searchPanel.setPreferredSize(new Dimension(500, 100));
		searchBook = new JLabel("Search book by ID or Title:                ");	searchBook.setFont(f1);
		searchBookidField = new JTextField(4);		searchBookidField.setFont(f1);
		searchTitleField = new JTextField(12);	searchTitleField.setFont(f1);
		searchUpdBook = new JButton("Search");		searchUpdBook.setFont(f1);
		searchUpdBook.addActionListener(new BookUpdateListener());	
		searchPanel.add(searchBook);		searchPanel.add(searchBookidField);		
		searchPanel.add(searchTitleField);	searchPanel.add(searchUpdBook);
		updateBookPan.add(searchPanel);
		
		JPanel bookResultPanel = new JPanel();			
		String[] bookHeader = {"ID", "Title", "Publisher", "ISBN", "Format", "Collocation", "Branch", "Available"};
		bookTableModel = new DefaultTableModel(bookHeader, 0);		
		bookResultTable = new JTable(bookTableModel){
			private static final long serialVersionUID = 1L;
		@Override public boolean isCellEditable(int row, int column) {return false;}};

		bookResultTable.getColumnModel().getColumn(0).setPreferredWidth(40);
		bookResultTable.getColumnModel().getColumn(1).setPreferredWidth(140);
		bookResultTable.getColumnModel().getColumn(3).setPreferredWidth(40);
		bookResultTable.getColumnModel().getColumn(5).setPreferredWidth(60);
		bookResultTable.getColumnModel().getColumn(7).setPreferredWidth(10);
		bookScroll = new JScrollPane(bookResultTable);			
		bookScroll.setPreferredSize(new Dimension(680, 170));
		bookResultPanel.add(bookScroll);
		updateBookPan.add(bookResultPanel);
		
		JPanel updateBook = new JPanel();	updateBook.setPreferredSize(new Dimension(600, 290));
		updateID = new JLabel("Insert the ID of the book to update:           ");		updateID.setFont(f1);
		updateIDField = new JTextField(4);	updateIDField.setFont(f1);
		updateBook.add(updateID);		updateBook.add(updateIDField);
		
		titleLab = new JLabel("Title: "); 		titleLab.setFont(f1);
		titleField = new JTextField(24);		titleField.setFont(f1);
		updateBook.add(titleLab); 			updateBook.add(titleField);
		pubLab = new JLabel("Publisher:"); 		pubLab.setFont(f1);
		pubField = new JTextField(22);			pubField.setFont(f1);
		updateBook.add(pubLab);				updateBook.add(pubField);
		ISBNLab = new JLabel("ISBN:"); 			ISBNLab.setFont(f1);
		ISBNField = new JTextField(10);			ISBNField.setFont(f1);
		updateBook.add(ISBNLab);			updateBook.add(ISBNField);
		branchLab = new JLabel("Branch ID:"); 	branchLab.setFont(f1);
		branchField = new JTextField(8);		branchField.setFont(f1);
		updateBook.add(branchLab);			updateBook.add(branchField);
		colLab = new JLabel("Collocation:"); 	colLab.setFont(f1);
		colField = new JTextField(8);			colField.setFont(f1);
		updateBook.add(colLab);				updateBook.add(colField);
		formatLab = new JLabel("Format:"); 		formatLab.setFont(f1);
		formatField = new JTextField(8);		formatField.setFont(f1);
		updateBook.add(formatLab);			updateBook.add(formatField);
		confirmBookUpdate = new JButton ("Update");		confirmBookUpdate.setFont(f1);		
		confirmBookUpdate.addActionListener(new BookUpdateListener());
		updateBook.add(confirmBookUpdate);
		updateBookPan.add(updateBook);
		
		JPanel deleteBook = new JPanel();	deleteBook.setPreferredSize(new Dimension(600, 100));
		deleteID = new JLabel("Insert the ID of the book to delete:           ");		deleteID.setFont(f1);
		deleteIDField = new JTextField(4);	deleteIDField.setFont(f1);
		deleteBook.add(deleteID);		deleteBook.add(deleteIDField);
		confirmBookDelete = new JButton ("Delete");		confirmBookDelete.setFont(f1);	
		confirmBookDelete.addActionListener(new BookUpdateListener());
		deleteBook.add(confirmBookDelete);
		updateBookPan.add(deleteBook);
			
		return updateBookPan;
	}
	
	public JPanel updateAuthorPanel() {
		
		JPanel updateAuthorPan = new JPanel();		updateAuthorPan.setPreferredSize(new Dimension(700, 650));		
		JPanel searchPanel = new JPanel();		searchPanel.setPreferredSize(new Dimension(500, 100));
		searchAuthor = new JLabel("Search author by ID or Last name:          ");		searchAuthor.setFont(f1);
		searchAuthoridField = new JTextField(4);		searchAuthoridField.setFont(f1);
		searchLastnameField = new JTextField(12);		searchLastnameField.setFont(f1);
		searchUpdAuthor = new JButton("Search");		searchUpdAuthor.setFont(f1);
		searchUpdAuthor.addActionListener(new AuthorUpdateListener());	
		searchPanel.add(searchAuthor);			searchPanel.add(searchAuthoridField);		
		searchPanel.add(searchLastnameField);	searchPanel.add(searchUpdAuthor);
		updateAuthorPan.add(searchPanel);
		
		JPanel authorResultPanel = new JPanel();			
		String[] authorHeader = {"ID", "Last Name", "First Name", "Role"};
		authorTableModel = new DefaultTableModel(authorHeader, 0);		
		authorResultTable = new JTable(authorTableModel){
			private static final long serialVersionUID = 1L;
		@Override public boolean isCellEditable(int row, int column) {return false;}};

		authorResultTable.getColumnModel().getColumn(0).setPreferredWidth(40);
		authorResultTable.getColumnModel().getColumn(1).setPreferredWidth(140);
		authorResultTable.getColumnModel().getColumn(3).setPreferredWidth(40);
		authorScroll = new JScrollPane(authorResultTable);			
		authorScroll.setPreferredSize(new Dimension(680, 170));
		authorResultPanel.add(authorScroll);
		updateAuthorPan.add(authorResultPanel);
		
		JPanel updateAuthor = new JPanel();		updateAuthor.setPreferredSize(new Dimension(600, 230));
		updateID = new JLabel("Insert the ID of the author to update:       ");		updateID.setFont(f1);
		updateIDField = new JTextField(4);	updateIDField.setFont(f1);
		updateAuthor.add(updateID);		updateAuthor.add(updateIDField);
		
		lastLab = new JLabel("Last name: "); 		lastLab.setFont(f1);
		lastField = new JTextField(20);				lastField.setFont(f1);
		updateAuthor.add(lastLab); 				updateAuthor.add(lastField);
		firstLab = new JLabel("First name: "); 		firstLab.setFont(f1);
		firstField = new JTextField(20);			firstField.setFont(f1);
		updateAuthor.add(firstLab);				updateAuthor.add(firstField);
		roleLab = new JLabel("Role:          "); 	roleLab.setFont(f1);
		roleField = new JTextField(20);				roleField.setFont(f1);
		updateAuthor.add(roleLab);				updateAuthor.add(roleField);
		confirmAuthorUpdate = new JButton ("Update");		confirmAuthorUpdate.setFont(f1);		
		confirmAuthorUpdate.addActionListener(new AuthorUpdateListener());
		updateAuthor.add(confirmAuthorUpdate);
		updateAuthorPan.add(updateAuthor);
		
		JPanel deleteAuthor = new JPanel();		deleteAuthor.setPreferredSize(new Dimension(600, 100));
		deleteID = new JLabel("Insert the ID of the author to delete:           ");		deleteID.setFont(f1);
		deleteIDField = new JTextField(4);	deleteIDField.setFont(f1);
		deleteAuthor.add(deleteID);		deleteAuthor.add(deleteIDField);
		confirmAuthorDelete = new JButton ("Delete");		confirmAuthorDelete.setFont(f1);	
		confirmAuthorDelete.addActionListener(new AuthorUpdateListener());
		deleteAuthor.add(confirmAuthorDelete);
		updateAuthorPan.add(deleteAuthor);
			
		return updateAuthorPan;
	}
	
	public class CreateListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
				
			if (e.getSource() == confirmCreate) { 
				
				if (!last[0].getText().isEmpty()) { //Proceed if at least one author is inserted
					try {
						Book b = new Book();
						ArrayList<Author> aList = new ArrayList<Author>();
						for (int i=0; i<4; i++) {
							if (!last[i].getText().isEmpty()) { 
								Author a = new Author(last[i].getText(), first[i].getText(), role[i].getText());
								aList.add(a);	
							} 
						}					
						if (ISBNField.getText().length() != 13 || branchField.getText().length() !=3) 
							JOptionPane.showMessageDialog(null, "Please check ISBN(13) and/or Branch ID(3) fields"); 
						else 
							if (!titleField.getText().isEmpty() && !pubField.getText().isEmpty() && !colField.getText().isEmpty() && !formatField.getText().isEmpty()) {
							// Book attributes require non-null values	
								b.setTitle(titleField.getText());
								b.setPublisher(pubField.getText());
								b.setISBN(Long.parseLong(ISBNField.getText()));
								b.setCollocation(colField.getText());
								b.setFormat(formatField.getText());
								b.setBranch_id(branchField.getText());
								int status = SQLbook.createBook(b, aList);
								if (status ==1){
									titleField.setText("");		pubField.setText("");	ISBNField.setText("");
									colField.setText("");	formatField.setText("");	branchField.setText("");
									for (int i=0; i<4; i++) {
										last[i].setText("");
										first[i].setText("");
										role[i].setText("");
									}
								}
							} else JOptionPane.showMessageDialog(null, "Fields 'Title', 'Publisher', 'Collocation' and 'Format' \nmust contain a value.");
						
					} catch (NumberFormatException ex) {
						ex.printStackTrace();
						JOptionPane.showMessageDialog(null, "Field ISBN require numeric value.");
					}
				} else JOptionPane.showMessageDialog(null, "Please insert at least Author 1.");
			}	
			
		}		
	}

	public class BookUpdateListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			if (e.getSource() == searchUpdBook) {
				//Search for books from input ID and/or title
				int bookid=0;
				String title = "";
				bookTableModel.setRowCount(0);
				if (!searchBookidField.getText().isEmpty()) 
					//try {
					bookid = Integer.parseInt(searchBookidField.getText());
				if (!searchTitleField.getText().isEmpty()) 		title = searchTitleField.getText();
					ArrayList<Book> bookList = new ArrayList<Book>();
					bookList = SQLbook.searchBook(bookid, title);
					for (int i=0; i<bookList.size(); i++) {
						bookTableModel.addRow(bookList.get(i).toArrayWoAuthor());
					}	
				
			//		} catch (NumberFormatException ex) {JOptionPane.showMessageDialog(null, "ID field requires numeric value.");}
			}

			if (e.getSource() == confirmBookUpdate) {
				//Create new book object, set values from input fields, call SQL method to process the update query
				//If some fields are left empty, the update query ignores them	
				int book_id = 0;
				long ISBN = 0;
				if (!updateIDField.getText().isEmpty()) 
					try { //Proceed only if ID field is not empty and contains numeric value
						book_id = Integer.parseInt(updateIDField.getText());
						Book b = new Book();
						b.setTitle(titleField.getText());
						b.setPublisher(pubField.getText());
						if (!ISBNField.getText().isEmpty()) ISBN = Long.parseLong(ISBNField.getText());
						b.setISBN(ISBN);
						b.setCollocation(colField.getText());
						b.setFormat(formatField.getText());
						b.setBranch_id(branchField.getText());
		
						int status = SQLbook.updateBook(book_id, b);
						if (status == 1) {
							titleField.setText("");
							pubField.setText("");
							ISBNField.setText("");
							colField.setText("");
							formatField.setText("");
							colField.setText("");
						}
				
					} catch (NumberFormatException ex) {JOptionPane.showMessageDialog(null, "ID field requires numeric value.");}	
			}

			if (e.getSource() == confirmBookDelete) {
				int bookid = 0;
				if (!deleteIDField.getText().isEmpty()) 
					try { //Proceed only if ID field is not empty and contains numeric value
						bookid = Integer.parseInt(deleteIDField.getText());
						SQLbook.deleteBook(bookid);
						deleteIDField.setText("");
					} catch (NumberFormatException ex) {JOptionPane.showMessageDialog(null, "ID field requires numeric value.");}	
			}
		}
	}
	
	public class AuthorUpdateListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == searchUpdAuthor) {
				//Search for authors from input ID and/or last name
				int authorid=0;
				String last = "";
				authorTableModel.setRowCount(0);
				if (!searchAuthoridField.getText().isEmpty()) 	
					//try { //Proceed only if ID field is not empty and contains numeric value
						authorid = Integer.parseInt(searchAuthoridField.getText());
						if (!searchLastnameField.getText().isEmpty()) 	last = searchLastnameField.getText();
						ArrayList<Author> authorList = new ArrayList<Author>();
						authorList = SQLbook.searchAuthor(authorid, last);
						for (int i=0; i<authorList.size(); i++) {
							authorTableModel.addRow(authorList.get(i).toArray());
						}
					//} catch (NumberFormatException ex) {JOptionPane.showMessageDialog(null, "ID field requires numeric value.");}
				
				}

			if (e.getSource() == confirmAuthorUpdate) {
				//Create new author object, set values from input fields, call SQL method to process the update query
				//If some fields are left empty, the update query ignores them	
				int author_id = 0;
				if (!updateIDField.getText().isEmpty()) 
					try { //Proceed only if ID field is not empty and contains numeric value
						author_id = Integer.parseInt(updateIDField.getText());
						Author a = new Author();
						a.setLast_name(lastField.getText());
						a.setFirst_name(firstField.getText());
						a.setRole(roleField.getText());
						int status = SQLbook.updateAuthor(author_id, a);
						if (status == 1) {
							lastField.setText("");
							firstField.setText("");
							roleField.setText("");
						}				
					}
					catch (NumberFormatException ex) {JOptionPane.showMessageDialog(null, "ID field requires numeric value.");}
				}

			if (e.getSource() == confirmAuthorDelete) {
				int authorid = 0;
				if (!deleteIDField.getText().isEmpty()) 
					try {	//Proceed only if ID field is not empty and contains numeric value
						authorid = Integer.parseInt(deleteIDField.getText());
						SQLbook.deleteAuthor(authorid);
						deleteIDField.setText("");
					} catch (NumberFormatException ex) {JOptionPane.showMessageDialog(null, "ID field requires numeric value.");}
			}	
		}
	}
}
