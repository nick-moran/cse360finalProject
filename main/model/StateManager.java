/**
 * This is the StateManager. It extends Observable so it updates
 * all the observers with the new state whenever there is a change.
 * 
 * @author Shakib Ahmed, Nick Moran, Madeleine Householder
 * @version 1.0
 * @since 2020-11-29
 */
package main.model;

import java.util.Observable;

/**
 * This is the StateManager class. It extends Observable so
 * it updates all observers when the state is changed. it contains
 * the class variable currentState which is a string that indicates
 * the current state of the application
 */
public class StateManager extends Observable{
	private String currentState;
	/**
	 * This is update string. When there is a change it notifies all
	 * observers with the new state that has been passed to it as a
	 * parameter
	 * @param newState represents the newState.
	 */
	public void updateState(String newState){
		currentState = newState;
		this.setChanged();
		this.notifyObservers(newState);
	}
}
