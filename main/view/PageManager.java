/**
 * The page manager.
 * This maps the possible pages you could be on to the state
 * 
 * @author Shakib Ahmed, Nick Moran, Madeleine Householder
 * @version 1.0
 * @since 2020-11-29
 */
package main.view;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import main.model.Roster;
import main.model.StateManager;

import main.view.Pages.*;

/**
 * Page manager is a JPanel and an Observer.
 * It sees changes from the state and updates
 * the JPanel to the correct page.
 * 
 * @author Shakib Ahmed, Nick Moran, Madeleine Householder
 *
 */
public class PageManager extends JPanel implements Observer{
	private HashMap<String, JPanel>stateToPage;
	
	/**
	 * We create the hash map of states to pages.
	 * Additionally, we make the home page an observer of the
	 * the roster loader class, so it can get updates from
	 * the csv.
	 * @param rosterLoader
	 */
	public PageManager(Roster rosterLoader) {
		this.stateToPage = new HashMap<String, JPanel>();
		this.stateToPage.put("about", new AboutPage());
		HomePage homePage = new HomePage();
		rosterLoader.addObserver(homePage);
		this.stateToPage.put("home", homePage);
	}
	
	/**
	 * gets updates from the state manager.
	 * if the new state either 'home' or 'about',
	 * it updates the JPanel 
	 * 
	 * @param updater
	 * @param newState
	 */
	@Override
	public void update(Observable updater, Object newState) {
		if(newState.toString() == "home" || newState.toString() == "about") {
			updatePanel((String)newState);
		}
	}
	
	/**
	 * Updates the panel
	 * @param newState
	 */
	private void updatePanel(String newState){
		for(Map.Entry<String, JPanel> entry: this.stateToPage.entrySet()) {
			remove(entry.getValue());
		}
		add(this.stateToPage.get(newState));
		repaint();
		revalidate();
	}
	
}
