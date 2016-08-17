package app;

import javax.swing.*;
import panels.AppPanel;

public class LibraryManagementApp {
	
	static	JFrame frame = new JFrame();
	static AppPanel ap;
	
	public static void main (String[] args) {		
		
		loadFrame();
	}
	
	static void loadFrame() {

		ap = new AppPanel();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(ap);
        frame.setLocation(300, 10);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
	}
	
	public static void reloadFrame() {	
	//When "Home" button is pressed on a secondary page, the main frame, which includes the home page, is reloaded	
		frame.remove(ap);
		loadFrame();
	}
}