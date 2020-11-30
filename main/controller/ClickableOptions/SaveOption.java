/**
 * The 'Save' menu option
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
* SaveOption extends ClickableItem, so it is a 
* listener. it updates the state to move to
* the home page and run the the save option.
* 
* @author Shakib Ahmed, Nick Moran, Madeleine Householder
*/
public class SaveOption extends ClickableItem{
	/**
	 * Makes the name of the option Save. 
	 * and maps the state object to the parent.
	 * @param state
	 */
	public SaveOption(StateManager state) {
		super(state, "Save");
	}
	
	/**
	 * a realization of the parent actionPerformed method
	 * updates the state to change the page and then
	 * enter the save section.
	 * @param e
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		super.updateState("home");
		super.updateState("Save");
	}
}
