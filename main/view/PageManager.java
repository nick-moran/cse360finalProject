package main.view;

import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import main.model.StateManager;
import main.view.Pages.*;

public class PageManager extends JPanel implements Observer{
	private HashMap<String, JPanel>stateToPage;
	
	public PageManager() {
		this.stateToPage = new HashMap<String, JPanel>();
		this.stateToPage.put("about", new AboutPage());
		this.stateToPage.put("home", new HomePage());
		this.stateToPage.put("invalid", new InvalidPage());
	}
	
	@Override
	public void update(Observable updater, Object newState) {
		if(this.stateToPage.containsKey((String)newState) == false) {
			System.out.println("Not a valid state");
			newState = "invalid";
		}
		updatePanel((String)newState);
		revalidate();
	}
	
	private void updatePanel(String newState){
		for(Map.Entry<String, JPanel> entry: this.stateToPage.entrySet()) {
			remove(entry.getValue());
		}
		add(this.stateToPage.get(newState));
		setVisible(true);
	}
	
}
