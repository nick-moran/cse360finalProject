package main.view;

import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import main.model.Roster;
import main.model.StateManager;
import main.view.Pages.*;

public class PageManager extends JPanel implements Observer{
	private HashMap<String, JPanel>stateToPage;
	
	public PageManager(Roster rosterLoader) {
		this.stateToPage = new HashMap<String, JPanel>();
		this.stateToPage.put("about", new AboutPage());
		HomePage homePage = new HomePage();
		rosterLoader.addObserver(homePage);
		this.stateToPage.put("home", homePage);
	}
	
	@Override
	public void update(Observable updater, Object newState) {
		if(newState.toString() == "home" || newState.toString() == "about") {
			updatePanel((String)newState);
		}
	}
	
	private void updatePanel(String newState){
		for(Map.Entry<String, JPanel> entry: this.stateToPage.entrySet()) {
			remove(entry.getValue());
		}
		add(this.stateToPage.get(newState));
		repaint();
		revalidate();
	}
	
}
