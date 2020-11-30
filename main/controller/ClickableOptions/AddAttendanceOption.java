/**
 * The 'Add attendance' menu option
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
 * AddAttendanceOption extends ClickableItem, so it is a 
 * listener. it updates the state to move to
 * the home page and then run the add attendance option.
 * 
 * @author Shakib Ahmed, Nick Moran, Madeleine Householder
 */
public class AddAttendanceOption extends ClickableItem{
	/**
	 * Makes the name of the option add attendance. 
	 * and maps the state object to the parent.
	 * @param state
	 */
	public AddAttendanceOption(StateManager state) {
		super(state, "Add attendance");
	}
	
	/**
	 * a realization of the parent actionPerformed method
	 * updates the state to change the page and then
	 * enter the add attendance section.
	 * 
	 * @param e
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		super.updateState("home");
		super.updateState("Add Attendance");
	}
}
