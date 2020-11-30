/**
 * This is the AboutPage. It simply prints out all the contributers
 * to the project. 
 * 
 * @author Shakib Ahmed, Nick Moran, Madeleine Householder
 * @version 1.0
 * @since 2020-11-29
 */
package main.view.Pages;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * This is the AboutPage class. it creates a Jlabel with the information
 * about the contributers and displays them
 */
public class AboutPage extends JPanel{
	/**
	 * This is the constructor for AboutPage. it simply lists all 
	 * contributers and puts them in a Jlabel.
	 */
	public AboutPage() {
		JLabel label = new JLabel("<html>This software was written by"
				+ "<br/>"
				+ "Madeleine Householder"
				+ "<br/>"
				+ "Shakib Ahmed"
				+ "<br/>"
				+ "Nick Moran"
				+ "</hmtl>");
		
		add(label);
	}
	
}
