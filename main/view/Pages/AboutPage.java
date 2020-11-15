package main.view.Pages;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class AboutPage extends JPanel{
	public AboutPage() {
		JLabel label = new JLabel("<html>This software was written by"
				+ "<br/>"
				+ "Name1"
				+ "<br/>"
				+ "Name2"
				+ "<br/>"
				+ "Nick Moran"
				+ "</hmtl>");
		
		add(label);
	}
	
}
