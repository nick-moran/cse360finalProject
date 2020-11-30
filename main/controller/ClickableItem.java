/**
 * The parent class for the clickable menu options.
 * 
 * @author Shakib Ahmed, Nick Moran, Madeleine Householder
 * @version 1.0
 * @since 2020-11-14
 */
package main.controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import main.model.StateManager;

/**
 * extends the AbstractAction, which is a part of the Listener class.
 * It is responsible to creating the actions when buttons are clicked.
 * 
 * @author Shakib Ahmed, Nick Moran, Madeleine Householder
 *
 */
public abstract class ClickableItem extends AbstractAction{
	StateManager state;
	
	/**
	 * Creates the button with the text and ties the state manager to the button
	 * @param state
	 * @param text
	 */
	public ClickableItem(StateManager state, String text) {
		super(text);
		this.state = state;
	}
	
	/**
	 * To be overridden by children classes.
	 */
	public void actionPerformed(ActionEvent e) {}
	
	/**
	 * helper method that all children use to update the stateManager state.
	 * @param newState
	 */
	public void updateState(String newState) {
		this.state.updateState(newState);
	}
}
