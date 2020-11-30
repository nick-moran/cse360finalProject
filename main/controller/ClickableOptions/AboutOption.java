/**
 * The 'about' menu option
 * 
 * @author Shakib Ahmed, Nick Moran, Madeleine Householder
 * @version 1.0
 * @since 2020-11-29
 */
package main.controller.ClickableOptions;

import java.awt.event.ActionEvent;

import main.controller.ClickableItem;
import main.model.StateManager;

/**
 * About Option extends ClickableItem, so it is a listener
 * It sets the name and upates the state manager
 * 
 * @author Shakib Ahmed, Nick Moran, Madeleine Householder
 */
public class AboutOption extends ClickableItem{
	
	/**
	 * Takes the StateManager object
	 * so that it can map update the state when clicked.
	 * @param state
	 */
	public AboutOption(StateManager state) {
		super(state, "About");
	}
	
	/**
	 * a realization of the parent actionPerformed method, which
	 * fires when it is clicked.
	 * 
	 * it updates the state to "about"
	 * @param e
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		super.updateState("about");
	}
}
