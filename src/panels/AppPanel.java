/*
* 
*/
package panels;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import SQL.SQLstaff;
import SQL.SQLuser;

public class AppPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	JPanel homePanel = new JPanel();
	UserPanel userPanel;
	StaffPanel staffPanel;
	CataloguePanel catPanel = new CataloguePanel();
	JButton search, user, staff;
	Font f1 = new Font("", Font.PLAIN, 18);
	Font f2 = new Font("", Font.PLAIN, 24);
	Color bg = Color.decode("#B8D7EC");
	Color hb = Color.decode("#9EB9CB");
	
	public AppPanel() {

		loadIntoMain();
	}

	public void loadIntoMain() {
		
		homePanel.setPreferredSize(new Dimension(800, 790));
		homePanel.setBackground(bg);
		JPanel up = new JPanel();
		up.setPreferredSize(new Dimension(600, 20));
		up.setBackground(bg);
		
		JPanel welcomeP = new JPanel();			welcomeP.setBackground(hb);
		welcomeP.setLayout(new BoxLayout(welcomeP, BoxLayout.Y_AXIS));
		welcomeP.setPreferredSize(new Dimension(660, 100));
 
		JLabel welcome1 = new JLabel("                   Welcome to the");
		welcome1.setPreferredSize(new Dimension(620, 50));		welcome1.setBackground(hb);
		welcome1.setFont(new Font("Lucida Handwriting", Font.BOLD, 30));

		JLabel welcome2 = new JLabel("  Knowhere Public Library App!  ");
		welcome2.setPreferredSize(new Dimension(660, 50));		welcome2.setBackground(hb);
		welcome2.setFont(new Font("Lucida Handwriting", Font.BOLD, 36));

		welcomeP.add(welcome1);
		welcomeP.add(welcome2);
		homePanel.add(up);
		homePanel.add(welcomeP);

		JPanel picPanel = new JPanel();		
		picPanel.setPreferredSize(new Dimension(500,280));
		picPanel.setBackground(bg);
		JLabel picLabel = new JLabel(new ImageIcon(getClass().getResource("/images/dublibrary.png")));
		picPanel.add(picLabel);
		homePanel.add(picPanel);
		
		search = new JButton("Search in the catalog!", (new ImageIcon(getClass().getResource("/images/search128.png"))));
		user = new JButton("Check your account ", (new ImageIcon(getClass().getResource("/images/usercard128.png"))));
		staff = new JButton("Staff only ", (new ImageIcon(getClass().getResource("/images/staff128.png"))));
		
		search.setPreferredSize(new Dimension(659,200));		search.setFont(new Font("", Font.PLAIN, 34));
		search.setBackground(hb);	search.setOpaque(true);		search.setBorderPainted(false);
		user.setBackground(hb); 	user.setOpaque(true); 		user.setBorderPainted(false); 		user.setFont(f2); 			
		staff.setBackground(hb); 	staff.setOpaque(true); 		staff.setBorderPainted(false); 		staff.setFont(f2);
		
		search.addActionListener(new ButtonListener());
		user.addActionListener(new ButtonListener());
		staff.addActionListener(new ButtonListener());

		homePanel.add(search);
		homePanel.add(user);
		homePanel.add(staff);

		add(homePanel);
	}

	public void reloadIntoMain() {
		removeAll();
		loadIntoMain();
	}

	public class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			JPanel loginPanel = new JPanel();		loginPanel.setPreferredSize(new Dimension(400,110));
			JLabel login, id, last;
			JTextField idField = new JTextField(7);		idField.setFont(f1);
			JTextField lastField = new JTextField(12);	lastField.setFont(f1);
			
			
			if (e.getSource() == search) { //Load catalog page
				remove(homePanel);
				add(catPanel);
			} 
			
			if (e.getSource() == user) { //Ask ID and last name and then loan user page
					id = new JLabel("User ID:                          ");		id.setFont(f1);
					last = new JLabel("Your Last name:");		last.setFont(f1);
					login = new JLabel("Login to access your personal page");	login.setFont(f1);
					loginPanel.add(login); 			
					loginPanel.add(id);		loginPanel.add(idField);
					loginPanel.add(last);	loginPanel.add(lastField);
					int result = JOptionPane.showConfirmDialog(null, loginPanel, "User login",JOptionPane.OK_CANCEL_OPTION);
					if (result == JOptionPane.OK_OPTION) {
						try {
							int user_id = Integer.parseInt(idField.getText());
							String last_name = lastField.getText();
							int status = SQLuser.userLogin(user_id, last_name);
							if (status == 1) { //If login was valid, load new user page with current user's data
								remove(homePanel);
								userPanel = new UserPanel(user_id);
								add(userPanel);
							}
						} catch (NumberFormatException ex) {
							JOptionPane.showMessageDialog(null, "ID field requires numeric value.");
						}
					}			
			} 
			
			if (e.getSource() == staff) { //Ask ID and last name, then load staff panel
					id = new JLabel("Staff ID:                          ");		id.setFont(f1);
					last = new JLabel("Your Last name:");		last.setFont(f1);
					login = new JLabel("Login to access your personal page");	login.setFont(f1);
					loginPanel.add(login); 			
					loginPanel.add(id);		loginPanel.add(idField);
					loginPanel.add(last);	loginPanel.add(lastField);
					int result = JOptionPane.showConfirmDialog(null, loginPanel, "Staff login",JOptionPane.OK_CANCEL_OPTION);
					if (result == JOptionPane.OK_OPTION) {
						try {
							int staff_id = Integer.parseInt(idField.getText());
							String last_name = lastField.getText();
							int status = SQLstaff.staffLogin(staff_id, last_name);
							if (status == 1) { //If login was valid, load new staff panel with current staff member's data
								remove(homePanel);
								staffPanel = new StaffPanel(staff_id);
								add(staffPanel);
							}
						} catch (NumberFormatException ex) {
							JOptionPane.showMessageDialog(null, "ID field requires numeric value.");
						}
					}
			}
			repaint();
			revalidate();
		}
	}
	
	public void changeFont (Component component, Font font)
	{ //Method that sets the selected font to all components in a panel
	    component.setFont (font);
	    if (component instanceof Container)
	    {
	        for (Component child : ((Container) component).getComponents())
	        {
	            changeFont (child, font);
	        }
	    }
	}
}
